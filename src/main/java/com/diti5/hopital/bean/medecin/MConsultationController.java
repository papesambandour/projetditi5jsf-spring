package com.diti5.hopital.bean.medecin;


import com.diti5.hopital.dao.ConsultationDAO;
import com.diti5.hopital.dao.PatientDAO;
import com.diti5.hopital.dao.ServiceDOA;
import com.diti5.hopital.dao.UtilisateurDAO;
import com.diti5.hopital.model.Consultation;
import com.diti5.hopital.model.Patient;
import com.diti5.hopital.model.Service;
import com.diti5.hopital.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class MConsultationController {
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
  private List<Patient> patients_ ;
  private List<Service> services ;
  private List<Consultation> consultations ;
  private List<Consultation> consultationsRecherches ;
  private Date dateRecherche ;
  @PostConstruct
  public void init(){
      consultation = new Consultation();
      consultation.setUtilisateur(new Utilisateur());
      consultation.setService(new Service());
      consultation.setPatient(new Patient());
      consultations = consultationDAO.findAll();
      List<Consultation> list = consultationDAO.findByUtilisateur(getUser());
      List<Consultation> onelis = new ArrayList<>();
      if(list.size() == 0){
          patients = new ArrayList<>();
      }else {
          onelis.add(list.get(0));
          patients = patientDAO.findByListeConsultations(onelis);
      }
      services = serviceDOA.findAll();
      patients_ = patientDAO.findAll();
  }

    public void exportFile(Consultation consultation) throws IOException {

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
        return "/medecin/consultation/index?faces-redirect=true&success=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
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
        return "/medecin/consultation/index?faces-redirect=true&success=true";
    }catch (Exception e)
    {
      System.out.println("________"+e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String edit(Consultation consultation){
    try {
       //init();
      this.consultation = consultation ;
      return "/medecin/consultation/edit";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String goRechereche(){
    try {
        dateRecherche = new Date();
       this.consultationsRecherches = new ArrayList<>();
      return "/medecin/consultation/recherche";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String goRecherecheP(){
    try {
        dateRecherche = new Date();
       this.consultationsRecherches = new ArrayList<>();
      return "/medecin/consultation/rechercheByPatient";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String goRecherechePJ(){
    try {
        dateRecherche = new Date();
       this.consultationsRecherches = new ArrayList<>();
      return "/medecin/consultation/rechercheByPatientAndDay";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String Rechereche(){
    try {
      //  init();
      this.consultationsRecherches = consultationDAO.findByDateConsultationAndUtilisateur(this.dateRecherche,getUser());
      return "/medecin/consultation/recherche";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String RecherecheP(){
    try {
      this.consultationsRecherches = consultationDAO.findByPatientAndUtilisateur(patientDAO.findById(consultation.getPatient().getId()).get(),getUser());
      return "/medecin/consultation/rechercheByPatient";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String RecherechePJ(){
    try {
      this.consultationsRecherches = consultationDAO.findByDateConsultationAndPatientAndUtilisateur
              (dateRecherche,patientDAO.findById(consultation.getPatient().getId()).get(),getUser());
      return "/medecin/consultation/rechercheByPatientAndDay";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
    }
  }
 public String delete(Consultation consultation){
    try {
      consultationDAO.delete(consultation);
     // init();
      return "/medecin/consultation/index?faces-redirect=tru&success=true";
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
      return "/medecin/consultation/index?faces-redirect=true&error=true";
    }
  }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public List<Patient> getPatients() {
        List<Consultation> list = consultationDAO.findByUtilisateur(getUser());
        List<Consultation> onelis = new ArrayList<>();
        if(list.size() == 0){
            patients = new ArrayList<>();
        }else {
            onelis.add(list.get(0));
            patients = patientDAO.findByListeConsultations(onelis);
        }
        return patients;
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
        return consultationDAO.findByUtilisateur(getUser());
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public Date getDateRecherche() {
        return dateRecherche;
    }

    public void setDateRecherche(Date dateRecherche) {
        this.dateRecherche = dateRecherche;
    }

    public List<Consultation> getConsultationsRecherches() {
        return consultationsRecherches;
    }

    public void setConsultationsRecherches(List<Consultation> consultationsRecherches) {
        this.consultationsRecherches = consultationsRecherches;
    }

    public List<Patient> getPatients_() {
        return patientDAO.findAll();
    }

    public void setPatients_(List<Patient> patients_) {
        this.patients_ = patients_;
    }

    public  Utilisateur getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object o = auth.getName();
        String p=o.toString();
        return utilisateurDAO.findByUsername(p);
    }


}
