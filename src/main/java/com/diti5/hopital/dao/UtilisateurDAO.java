package com.diti5.hopital.dao;



import com.diti5.hopital.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDAO extends JpaRepository<Utilisateur, Integer> {
	public Utilisateur findByUsername(String username);
	public Utilisateur findByMatriculeOrUsername(String matricule, String username);
//	@Query("select u from Utilisateur ")
//	public Utilisateur hhh();
}
