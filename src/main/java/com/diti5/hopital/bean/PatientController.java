package com.diti5.hopital.bean;


import com.diti5.hopital.dao.*;
import com.diti5.hopital.model.Consultation;
import com.diti5.hopital.model.Patient;
import com.diti5.hopital.model.Role;
import com.diti5.hopital.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class PatientController {
  @Autowired
  private PatientDAO patientDAO;
  @Autowired
  private ServiceDOA serviceDOA;
  @Autowired
  private   UtilisateurDAO utilisateurDAO;
  @Autowired
  private ConsultationDAO consultationDAO;
  private Patient patient ;
  private Consultation consultation ;
  private List<Patient> patients ; //
  private List<Consultation> consultations ;
  private List<Consultation> consultationsAntecedant ;

  @PostConstruct
  public void init(){
    patient = new Patient();
    patients = patientDAO.findAll();
    consultations = new ArrayList<>();
    consultation = new Consultation();
  }

  public String save(){
    try {
      patientDAO.save(patient);
      return "/admin/patient/index?faces-redirect=true&succes=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/patient/index?faces-redirect=true&error=true";
    }
  }
 public String consultationP(Patient patient){
    try {
      this.consultations = consultationDAO.findByPatient(patient) ;
      this.patient = patient;
      return "/admin/patient/consultation";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/patient/index?faces-redirect=true&error=true";
    }
  }
 public String antecedant(Patient patient){
    try {
      this.consultationsAntecedant = consultationDAO.findByPatient(patient) ;
      this.patient = patient;
      return "/admin/patient/antecedant";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/patient/index?faces-redirect=true&error=true";
    }
  }
   /* public String editConsultatio(Consultation consultation){
        try {
            this.consultation = consultation ;
            return "/admin/patient/editcons";
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "/admin/patient/editcons?faces-redirect=true&error=true";
        }
    }
    public String deleteConsultation(Consultation consultation){
        try {
            consultationDAO.delete(consultation);
            return "/admin/patient/index?faces-redirect=true&succes=true";
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "/admin/patient/index?faces-redirect=true&error=true";
        }
    }
    public String updateCons(){
        try {
            Consultation tmpConsult = consultationDAO.findById(consultation.getId()).get();
            tmpConsult.setService(serviceDOA.findById(consultation.getService().getId()).get());
            tmpConsult.setPatient(patientDAO.findById(consultation.getPatient().getId()).get());
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object o = auth.getName();
            String p=o.toString();
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
    }*/
 public String edit(Patient patient){
    try {
      this.patient = patient ;
      return "/admin/patient/edit";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/patient/index?faces-redirect=true&error=true";
    }
  }
 public String delete(Patient patient){
    try {
      patientDAO.delete(patient);
      init();
      return "/admin/patient/index?faces-redirect=tru&success=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/admin/patient/index?faces-redirect=true&error=true";
    }
  }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public List<Consultation> getConsultationsAntecedant() {
        return consultationsAntecedant;
    }

    public void setConsultationsAntecedant(List<Consultation> consultationsAntecedant) {
        this.consultationsAntecedant = consultationsAntecedant;
    }
}
