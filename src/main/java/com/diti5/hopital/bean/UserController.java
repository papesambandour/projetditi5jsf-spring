package com.diti5.hopital.bean;

import com.diti5.hopital.dao.RoleDAO;
import com.diti5.hopital.dao.ServiceDOA;
import com.diti5.hopital.dao.UtilisateurDAO;
import com.diti5.hopital.model.Role;
import com.diti5.hopital.model.Service;
import com.diti5.hopital.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class UserController implements Serializable  {
    public UserController(){

    }
    @PostConstruct
    private void initUser()
    {
        user = new Utilisateur();
        user.setService(new Service());
        user.setListeRoles(new ArrayList<Role>());
        listRolesId = new ArrayList<Integer>();
    }
    private Utilisateur user ;
    private List<Utilisateur> users ;
    private List<Service> services ;
    private List<Role> roles;
    private List<Integer> listRolesId ;
    @Autowired
    private UtilisateurDAO utilisateurDAO;
    @Autowired
    private ServiceDOA serviceDOA;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private BCryptPasswordEncoder encoder ;

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public List<Utilisateur> getUsers() {
        users = utilisateurDAO.findAll();
        return users;
    }
    public void setUsers(List<Utilisateur> users) {
        this.users = users;
    }



    public List<Service> getServices() {
        services = serviceDOA.findAll();
        return services;
    }

    public void setServices(List<Service> services){
        this.services = services;
    }


    public List<Role> getRoles() {
        roles = roleDAO.findAll();
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Integer> getListRolesId() {
        return listRolesId;
    }

    public void setListRolesId(List<Integer> listRolesId) {
        this.listRolesId = listRolesId;
    }

    public String deleteUser(Utilisateur user){
        try {
            utilisateurDAO.delete(user);
            this.user = new Utilisateur();
            return "/admin/utilisateur/index?faces-redirect=true&error=true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/utilisateur/index?faces-redirect=true&error=true";
    }

    public String editUser(Utilisateur user){
        try {
            this.user = user ;
            System.out.println("ID_USER:"+user.getId());
            listRolesId = new ArrayList<>();
            for (Role r: user.getListeRoles()
                 ) {
                listRolesId.add(r.getId());
            }
            return "/admin/utilisateur/edit";
        }catch (Exception e){
            e.printStackTrace();
        }
          return "/admin/utilisateur/index?faces-redirect=true&error=true";
    }
    public String detailsUser(Utilisateur user){
        try {
            this.user = user ;

            return "/admin/utilisateur/index?faces-redirect=true&error=true";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/utilisateur/index?faces-redirect=true&error=true";
    }

    public String updateUser(){
        try {

            user.setService(serviceDOA.findById(user.getService().getId()).get());
            List<Role> tmpRole = new ArrayList<Role>();
            for (Integer idRole: listRolesId
            ) {
                tmpRole.add(roleDAO.findById(idRole).get());
            }
            user.setListeRoles(tmpRole);
            System.out.println("ID_USER"+user.getId());
            utilisateurDAO.save(user);

            initUser();
            return "/admin/utilisateur/index?faces-redirect=true&success=true";
        }catch (Exception e){
           System.out.println("ERRO__"+e.getMessage());
        }
        return "/admin/utilisateur/index?faces-redirect=true&error=true";
    }


    public String addUser() {
        try {
            //Service s = serviceRepository.findOne(user.getService().getId());

            user.setService(serviceDOA.findById(user.getService().getId()).get());
            List<Role> tmpRole = new ArrayList<Role>();
            for (Integer idRole: listRolesId
                 ) {
                tmpRole.add(roleDAO.findById(idRole).get());
            }
            user.setListeRoles(tmpRole);
            user.setPassword(encoder.encode(user.getPassword()));
           // user.setUsername((user.getNom()+user.getMatricule()).toLowerCase());
            utilisateurDAO.save(user);
            System.out.println("Success!!!");
            user = new Utilisateur() ;
            System.out.println("aaaaa"+user.getNom());
            return "/admin/utilisateur/index?faces-redirect=true&error=true";
        }
        catch (Exception e){
            System.out.println("aaa"+  e.getMessage());
        }
        return "/admin/utilisateur/index?faces-redirect=true&error=true";
    }





}
