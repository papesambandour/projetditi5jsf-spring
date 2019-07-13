package com.diti5.hopital.bean;


import com.diti5.hopital.dao.*;
import com.diti5.hopital.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ConsultationController {
  @Autowired
  private ConsultationDAO consultationDAO;
  @Autowired
  private PatientDAO patientDAO ;
  @Autowired
  private ServiceDOA serviceDOA ;
  @Autowired
  private UtilisateurDAO utilisateurDAO ;

  private Consultation consultation;
  private List<Patient> patients ;
  private List<Service> services ;
  private List<Consultation> consultations ;
  @PostConstruct
  public void init(){
      consultation = new Consultation();
      consultation.setUtilisateur(new Utilisateur());
      consultation.setService(new Service());
      consultation.setPatient(new Patient());
      consultations = consultationDAO.findAll();
      patients = patientDAO.findAll();
      services = serviceDOA.findAll();
  }

  public String update(){
    try {
        Consultation tmpConsult = consultationDAO.findById(consultation.getId()).get();
        tmpConsult.setService(serviceDOA.findById(consultation.getService().getId()).get());
        tmpConsult.setPatient(patientDAO.findById(consultation.getPatient().getId()).get());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object o = auth.getName();
        String p=o.toString();
        Utilisateur u = utilisateurDAO.findByUsername(p);
        tmpConsult.setUtilisateur(u);
        tmpConsult.setDateConsultation(consultation.getDateConsultation());
        tmpConsult.setCommentaire(consultation.getCommentaire());
        tmpConsult.setPrescription(consultation.getPrescription());
        tmpConsult.setState(consultation.getState());
        consultationDAO.save(tmpConsult);
        init();
        return "/admin/consultation/index?faces-redirect=true&success=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/consultation/index?faces-redirect=true&error=true";
    }
  }
  public String save(){
    try {
        consultation.setService(serviceDOA.findById(consultation.getService().getId()).get());
        consultation.setPatient(patientDAO.findById(consultation.getPatient().getId()).get());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object o = auth.getName();
        String p=o.toString();
        Utilisateur u = utilisateurDAO.findByUsername(p);
        consultation.setUtilisateur(u);
        consultationDAO.save(consultation);
        init();
        return "/admin/consultation/index?faces-redirect=true&success=true";
    }catch (Exception e)
    {
      System.out.println("________"+e.getMessage());
      return "/admin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String edit(Consultation consultation){
    try {
      this.consultation = consultation ;
      return "/admin/consultation/edit";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String delete(Consultation consultation){
    try {
      consultationDAO.delete(consultation);
      init();
      return "/admin/consultation/index?faces-redirect=tru&success=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/consultation/index?faces-redirect=true&error=true";
    }
  }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public List<Patient> getPatients() {
        return patientDAO.findAll();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Service> getServices() {
        return serviceDOA.findAll();
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Consultation> getConsultations() {
        return consultationDAO.findAll();
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
