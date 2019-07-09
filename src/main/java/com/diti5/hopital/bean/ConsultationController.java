package com.diti5.hopital.bean;


import com.diti5.hopital.dao.RoleDAO;
import com.diti5.hopital.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ConsultationController {
  @Autowired
  private RoleDAO roleDAO;
  private Role role ;
  private List<Role> roles ; //
  @PostConstruct
  public void init(){
    role = new Role();
    roles = roleDAO.findAll();
  }

  public String save(){
    try {
      roleDAO.save(role);
      return "/admin/role/index?faces-redirect=true&succes=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/role/index?faces-redirect=true&error=true";
    }
  }
 public String edit(Role role){
    try {
      this.role = role ;
      return "/admin/role/edit";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/role/index?faces-redirect=true&error=true";
    }
  }
 public String delete(Role role){
    try {
      roleDAO.delete(role);
      init();
      return "/admin/role/index?faces-redirect=tru&success=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/consultation/index?faces-redirect=true&error=true";
    }
  }


}
