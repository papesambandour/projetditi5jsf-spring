package com.diti5.hopital.dao;



import com.diti5.hopital.model.Consultation;
import com.diti5.hopital.model.Patient;
import com.diti5.hopital.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsultationDAO extends JpaRepository<Consultation, Integer> {
	public List<Consultation> findByPatient(Patient patient);
	public List<Consultation> findByPatientAndUtilisateur(Patient patient,Utilisateur utilisateur);

	public List<Consultation> findByDateConsultation(Date date);
	public List<Consultation> findByDateConsultationAndUtilisateur(Date date,Utilisateur utilisateur);

	public List<Consultation> findByDateConsultationAndPatient(Date date,Patient patient);
	public List<Consultation> findByDateConsultationAndPatientAndUtilisateur(Date date,Patient patient,Utilisateur utilisateur);
	//f
	public List<Consultation> findByUtilisateur(Utilisateur utilisateur);
}
