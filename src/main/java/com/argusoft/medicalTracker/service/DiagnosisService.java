package com.argusoft.medicalTracker.service;

import com.argusoft.medicalTracker.dto.DiagnosisDto;
import com.argusoft.medicalTracker.dto.DiagnosisResponseDto;
import com.argusoft.medicalTracker.dto.DoctorResponseDto;
import com.argusoft.medicalTracker.entity.Diagnosis;
import com.argusoft.medicalTracker.entity.Doctor;
import com.argusoft.medicalTracker.entity.Patient;
import com.argusoft.medicalTracker.repository.DiagnosisRepository;
import com.argusoft.medicalTracker.repository.DoctorRepository;
import com.argusoft.medicalTracker.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<DiagnosisResponseDto> getAllDiagnosis() {
        return diagnosisRepository.findAll()
                .stream()
                .map(Diagnosis-> modelMapper.map(Diagnosis, DiagnosisResponseDto.class))
                .toList();
    }

    public List<DiagnosisResponseDto> getDiagnosisByPatientId(Long id){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null){
            throw new RuntimeException("Patient id does not exist");
        }else{
            return diagnosisRepository.findByPatient(patient)
                    .stream()
                    .map(Diagnosis-> modelMapper.map(Diagnosis, DiagnosisResponseDto.class))
                    .toList();

        }
    }

    public String saveDiagnosis(DiagnosisDto req) {
//        Doctor doctor = doctorRepository.findById(req.getDoctorId()).orElse(null);
        Patient patient = patientRepository.findById(req.getPatientId()).orElse(null);
        if(patient == null){
            throw new RuntimeException("Patient Id is not found");
        }else{
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setDescription(req.getDescription());
//            diagnosis.setDoctor(doctor);
            diagnosis.setPatient(patient);
            diagnosisRepository.save(diagnosis);
            return "Diagnosis Saved Sucessfully";
        }

    }
}
