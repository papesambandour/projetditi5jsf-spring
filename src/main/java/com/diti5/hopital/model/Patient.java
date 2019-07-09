package com.diti5.hopital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(length=10)
	private String numDossier;
	@Column(length=100)
	private String prenom;
	@Column(length=50)
	private String nom;
	@Column(length=10)
	private String tel;
	private Date dateNaiss;
	@OneToMany(mappedBy="patient")
	//@JsonManagedReference
	@JsonIgnore
	private List<Consultation> listeConsultations;
	public Patient() {}
	public Patient(int id, String numDossier, String prenom, String nom, String tel, Date dateNaiss) {
		super();
		this.id = id;
		this.numDossier = numDossier;
		this.prenom = prenom;
		this.nom = nom;
		this.tel = tel;
		this.dateNaiss = dateNaiss;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumDossier() {
		return numDossier;
	}
	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public List<Consultation> getListeConsultations() {
		return listeConsultations;
	}
	public void setListeConsultations(List<Consultation> listeConsultations) {
		this.listeConsultations = listeConsultations;
	}
	
}
