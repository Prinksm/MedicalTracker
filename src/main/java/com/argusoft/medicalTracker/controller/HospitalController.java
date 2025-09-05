package com.argusoft.medicalTracker.controller;
import com.argusoft.medicalTracker.entity.Hospital;
import com.argusoft.medicalTracker.repository.HospitalRepository;
import com.argusoft.medicalTracker.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public List<Hospital> getAllHospital(){
        return hospitalService.getAllHospital();
    }

    @PostMapping("/saveHospital")
    public Hospital saveHospital(@RequestBody Hospital hospital){
        return hospitalService.saveHospital(hospital);
    }
    @DeleteMapping("/deleteHospital/{id}")
    public void deleteHospital(@PathVariable Long id){
        hospitalService.deleteHospital(id);
    }
}
