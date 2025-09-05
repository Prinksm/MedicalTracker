package com.argusoft.medicalTracker.service;

import com.argusoft.medicalTracker.dto.DiagnosisDto;
import com.argusoft.medicalTracker.entity.Diagnosis;
import com.argusoft.medicalTracker.entity.Doctor;
import com.argusoft.medicalTracker.entity.Patient;
import com.argusoft.medicalTracker.repository.DiagnosisRepository;
import com.argusoft.medicalTracker.repository.DoctorRepository;
import com.argusoft.medicalTracker.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public List<Diagnosis> getAllDiagnosis() {
        return diagnosisRepository.findAll();
    }

    public List<Diagnosis> getDiagnosisByPatientId(Long id){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null){
            throw new RuntimeException("Patient id does not exist");
        }else{
            return diagnosisRepository.findByPatient(patient);
        }
    }

    public Diagnosis saveDiagnosis(DiagnosisDto req) {
        Doctor doctor = doctorRepository.findById(req.getDoctorId()).orElse(null);
        Patient patient = patientRepository.findById(req.getPatientId()).orElse(null);
        if(patient == null || doctor == null){
            throw new RuntimeException("Id is not found");
        }else{
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setDescription(req.getDescription());
            diagnosis.setDoctor(doctor);
            diagnosis.setPatient(patient);
            return diagnosisRepository.save(diagnosis);
        }

    }
}
