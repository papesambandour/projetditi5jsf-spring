package com.diti5.hopital.bean;


import com.diti5.hopital.dao.ConsultationDAO;
import com.diti5.hopital.dao.PatientDAO;
import com.diti5.hopital.dao.RoleDAO;
import com.diti5.hopital.model.Consultation;
import com.diti5.hopital.model.Patient;
import com.diti5.hopital.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class PatientController {
  @Autowired
  private PatientDAO patientDAO;
  @Autowired
  private ConsultationDAO consultationDAO;
  private Patient patient ;
  private Consultation consultation ;
  private List<Patient> patients ; //
    private List<Consultation> consultations ;
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
 public String consultation(Patient patient){
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
}
