package com.argusoft.medicalTracker.service;

import com.argusoft.medicalTracker.dto.PatientDto;
import com.argusoft.medicalTracker.entity.Doctor;
import com.argusoft.medicalTracker.entity.Patient;
import com.argusoft.medicalTracker.repository.DoctorRepository;
import com.argusoft.medicalTracker.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Patient> getAllPatients(){
       return patientRepository.findAll();
    }

    public Patient savePatient(PatientDto req){
        Doctor doctor = doctorRepository.findById(req.getDoctorId()).orElse(null);
        if(doctor == null){
            throw new RuntimeException("Doctor with id "+req.getDoctorId()+"is not found");
        }
        else{
            Patient patient = new Patient();
            patient.setName(req.getName());
            patient.setAge(req.getAge());
            patient.setGender(req.getGender());
            patient.setDoctor(doctor);
            return patientRepository.save(patient);
        }
    }

    public void deletePatient(Long id){
        patientRepository.deleteById(id);
    }
}
