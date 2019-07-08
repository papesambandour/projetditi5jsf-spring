package com.diti5.hopital.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Service {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(length=50)
	private String libelle;
	@OneToMany(mappedBy="service",cascade = {
			CascadeType.MERGE})
	//@JsonManagedReference
	//@JsonIgnore
	private List<Utilisateur> utilisateur ;
	public Service() {}
	public Service(int id, String libelle) {
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
	
}
