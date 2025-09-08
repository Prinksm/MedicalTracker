package com.argusoft.medicalTracker.controller;

import com.argusoft.medicalTracker.dto.DoctorResponseDto;
import com.argusoft.medicalTracker.dto.PatientDto;
import com.argusoft.medicalTracker.dto.PatientResponseDto;
import com.argusoft.medicalTracker.entity.Patient;
import com.argusoft.medicalTracker.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>>getAllPatients(){
        List<PatientResponseDto>patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients) ;
    }

    @PostMapping("/savePatient")
    public ResponseEntity<String> savePatient(@RequestBody PatientDto patient){
        return ResponseEntity.status(201).body(patientService.savePatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id){
        PatientResponseDto patient =  patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    @GetMapping("/searchByPatientName/{name}")
    public ResponseEntity<List<PatientResponseDto>> getPatientByName(@PathVariable String name){
        List<PatientResponseDto> patient=  patientService.getPatientByName(name);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/searchByDoctorName/{doctorName}")
    public ResponseEntity<List<PatientResponseDto>> getPatientByDoctorName(@PathVariable String doctorName){
        List<PatientResponseDto> patient =  patientService.getPatientByDoctorName(doctorName);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        if (patientService.deletePatient(id)) {
            return ResponseEntity.ok("Patient deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Patient not found with ID " + id);
        }
    }
}
