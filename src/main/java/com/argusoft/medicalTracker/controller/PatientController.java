package com.argusoft.medicalTracker.controller;

import com.argusoft.medicalTracker.dto.PatientDto;
import com.argusoft.medicalTracker.entity.Patient;
import com.argusoft.medicalTracker.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("/savePatient")
    public Patient savePatient(@RequestBody PatientDto patient){
        return patientService.savePatient(patient);
    }

    @PostMapping("/deletePatient/{id}")
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
    }
}
