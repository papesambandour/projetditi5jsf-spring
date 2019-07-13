package com.diti5.hopital.model;



public class Profil {
    public int id;
    public String username  ;
    public String passwordNew;
    public String passwordNewConfirm;
    public String prenom   ;
    public String nom  ;
    public String matricule ;
    public Profil(){}
    public Profil(int id, String username, String passwordNew, String passwordNewConfirm, String prenom, String nom, String matricule) {
        this.id = id;
        this.username = username;
        this.passwordNew = passwordNew;
        this.passwordNewConfirm = passwordNewConfirm;
        this.prenom = prenom;
        this.nom = nom;
        this.matricule = matricule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getPasswordNewConfirm() {
        return passwordNewConfirm;
    }

    public void setPasswordNewConfirm(String passwordNewConfirm) {
        this.passwordNewConfirm = passwordNewConfirm;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
