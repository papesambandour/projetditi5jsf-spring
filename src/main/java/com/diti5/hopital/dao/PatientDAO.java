package com.diti5.hopital.dao;



import com.diti5.hopital.model.Consultation;
import com.diti5.hopital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDAO extends JpaRepository<Patient, Integer> {
    List<Patient> findByListeConsultations(List<Consultation> consultations);
}
