package com.diti5.hopital.bean.medecin;


import com.diti5.hopital.dao.UtilisateurDAO;
import com.diti5.hopital.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MProfilController {
  @Autowired
  private UtilisateurDAO utilisateurDAO;
  @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder ;
  private Utilisateur utilisateur;
  private String newPassword ;
  private String confirmPassword ;
  @PostConstruct
  public void init(){


      utilisateur  = utilisateur = getUserAuth();
      confirmPassword = "";
      newPassword = "";
  }
  private Utilisateur getUserAuth(){
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      Object o = auth.getName();
      String p=o.toString();
      return utilisateurDAO.findByUsername(p);

  }

  public String updateProfil(){
    try {
        Utilisateur userTpm = getUserAuth();
        userTpm.setNom(utilisateur.getNom());
        userTpm.setPrenom(utilisateur.getPrenom());
        utilisateurDAO.save(userTpm);
        init();
      return "/medecin/profil/index?faces-redirect=true&profil=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/profil/index?faces-redirect=true&error=true";
    }
  } public String updatePassword(){
    try {
        Utilisateur userTpm = getUserAuth();
        if(!newPassword.isEmpty() ){
            if(!newPassword.equals(confirmPassword)){
                return "/medecin/profil/index?faces-redirect=true&confirm=true";
            }
        }
        userTpm.setPassword(bCryptPasswordEncoder.encode(newPassword));
        utilisateurDAO.save(userTpm);
        init();
      return "/medecin/profil/index?faces-redirect=true&passe=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/profil/index?faces-redirect=true&error=true";
    }
  }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
