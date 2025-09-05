package com.argusoft.medicalTracker.controller;

import com.argusoft.medicalTracker.dto.DoctorDto;
import com.argusoft.medicalTracker.entity.Doctor;
import com.argusoft.medicalTracker.repository.DoctorRepository;
import com.argusoft.medicalTracker.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    public DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @PostMapping("/saveDoctor")
    public Doctor saveDoctor(@RequestBody DoctorDto doctor){
        return doctorService.saveDoctor(doctor);
    }

    @PostMapping("/deleteDoctor/{id}")
    public void deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
    }
}
