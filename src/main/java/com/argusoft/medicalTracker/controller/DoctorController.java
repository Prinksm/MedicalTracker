package com.argusoft.medicalTracker.controller;

import com.argusoft.medicalTracker.dto.DoctorDto;
import com.argusoft.medicalTracker.dto.DoctorResponseDto;
import com.argusoft.medicalTracker.dto.HospitalResponseDto;
import com.argusoft.medicalTracker.dto.PatientResponseDto;
import com.argusoft.medicalTracker.entity.Doctor;
import com.argusoft.medicalTracker.repository.DoctorRepository;
import com.argusoft.medicalTracker.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    public DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors(){
        List<DoctorResponseDto>doctors =  doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id){
        DoctorResponseDto doctor =  doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }
    @GetMapping("/searchByDoctorName/{name}")
    public ResponseEntity<List<DoctorResponseDto>> getDoctorByName(@PathVariable String name){
        List<DoctorResponseDto> doctor =  doctorService.getDoctorByName(name);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/searchByDoctorSpecialisation/{specialisation}")
    public ResponseEntity<List<DoctorResponseDto>> getDoctorBySpecialisation(@PathVariable String specialisation){
        List<DoctorResponseDto> doctor =  doctorService.getDoctorBySpecialisation(specialisation);
        return ResponseEntity.ok(doctor);
    }

    @PostMapping("/saveDoctor")
    public ResponseEntity<String> saveDoctor(@RequestBody DoctorDto doctor){
        return ResponseEntity.status(201).body(doctorService.saveDoctor(doctor));
    }

    @DeleteMapping("/deleteDoctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
        if (doctorService.deleteDoctor(id)) {
            return ResponseEntity.ok("Doctor deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doctor not found with ID " + id);
        }
    }
}
