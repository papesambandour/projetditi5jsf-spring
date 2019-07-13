package com.diti5.hopital.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(length=50)
	private String libelle;
	@ManyToMany(mappedBy = "listeRoles",cascade = {
			CascadeType.PERSIST,CascadeType.MERGE
	},fetch=FetchType.LAZY)
	//@JsonManagedReference
	@JsonIgnore
	private List<Utilisateur> listeUtilisateurs;

	public Role() {}
	public Role(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	@JsonBackReference

	public List<Utilisateur> getListeUtilisateurs() {
		return listeUtilisateurs;
	}
	public void setListeUtilisateurs(List<Utilisateur> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

}
