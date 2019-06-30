package com.diti5.hopital.dao;



import com.diti5.hopital.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationDAO extends JpaRepository<Consultation, Integer> {
	
}
