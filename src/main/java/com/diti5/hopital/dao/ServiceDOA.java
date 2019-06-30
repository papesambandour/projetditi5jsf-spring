package com.diti5.hopital.dao;



import com.diti5.hopital.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDOA extends JpaRepository<Service, Integer> {
}
