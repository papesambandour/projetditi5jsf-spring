package com.diti5.hopital.dao;



import com.diti5.hopital.model.Consultation;
import com.diti5.hopital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationDAO extends JpaRepository<Consultation, Integer> {
	public List<Consultation> findByPatient(Patient patient);
}
