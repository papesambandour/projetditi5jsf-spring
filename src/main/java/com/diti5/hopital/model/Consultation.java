package com.diti5.hopital.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Consultation {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private Date dateConsultation;
	@Column(length=65535, columnDefinition="TEXT")
	@Type(type="text")
	private String commentaire;
	private String prescription;
	@Column(length = 10)
	private String state;
	@ManyToOne (cascade = {
			CascadeType.MERGE
	},fetch=FetchType.EAGER)

	private Patient patient;
	@ManyToOne (cascade = {
			CascadeType.MERGE
	},fetch=FetchType.EAGER)

	private Service service;
	@ManyToOne (cascade = {
			CascadeType.MERGE
	},fetch=FetchType.EAGER)
	private Utilisateur utilisateur;
	public Consultation() {}
	public Consultation(int id, Date dateConsultation, String commentaire, String prescripion, Patient patient,
			Service service) {
		super();
		this.id = id;
		this.dateConsultation = dateConsultation;
		this.commentaire = commentaire;
		this.prescription = prescripion;
		this.patient = patient;
		this.service = service;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateConsultation() {
		return dateConsultation;
	}
	public void setDateConsultation(Date dateConsultation) {
		this.dateConsultation = dateConsultation;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getPrescription() {
		return prescription;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
