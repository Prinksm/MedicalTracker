package com.argusoft.medicalTracker.service;

import com.argusoft.medicalTracker.dto.DoctorResponseDto;
import com.argusoft.medicalTracker.dto.PatientDto;
import com.argusoft.medicalTracker.dto.PatientResponseDto;
import com.argusoft.medicalTracker.entity.Doctor;
import com.argusoft.medicalTracker.entity.Patient;
import com.argusoft.medicalTracker.repository.DoctorRepository;
import com.argusoft.medicalTracker.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<PatientResponseDto> getAllPatients(){
        return patientRepository.findAll()
                .stream()
                .map(Patient-> modelMapper.map(Patient , PatientResponseDto.class))
                .toList();
    }

    public String savePatient(PatientDto req){
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
            patientRepository.save(patient);
            return "Patient Saved Successfully";
        }
    }

    public PatientResponseDto getPatientById(Long id){
        return patientRepository.findById(id)
                .map(Patient-> modelMapper.map(Patient , PatientResponseDto.class)).orElseThrow();
    }

    public List<PatientResponseDto> getPatientByName(String name){
        return patientRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(Patient-> modelMapper.map(Patient , PatientResponseDto.class))
                .toList();
    }

    public List<PatientResponseDto> getPatientByDoctorName(String doctorName){
        return patientRepository.findByDoctorNameContainingIgnoreCase(doctorName)
                .stream()
                .map(Patient-> modelMapper.map(Patient , PatientResponseDto.class))
                .toList();
    }

    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
