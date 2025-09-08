package com.argusoft.medicalTracker.controller;
import com.argusoft.medicalTracker.dto.DoctorResponseDto;
import com.argusoft.medicalTracker.dto.HospitalResponseDto;
import com.argusoft.medicalTracker.entity.Hospital;
import com.argusoft.medicalTracker.repository.HospitalRepository;
import com.argusoft.medicalTracker.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<List<HospitalResponseDto>> getAllHospital(){
        List<HospitalResponseDto>hospitals = hospitalService.getAllHospital();
        return ResponseEntity.ok(hospitals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponseDto> getHospitalById(@PathVariable Long id){
        HospitalResponseDto hospital =  hospitalService.getHospitalById(id);
        return ResponseEntity.ok(hospital);
    }
    @GetMapping("/searchByHospitalName/{name}")
    public ResponseEntity<List<HospitalResponseDto>> getHospitalByName(@PathVariable String name){
        List<HospitalResponseDto> hospital =  hospitalService.getHospitalByName(name);
        return ResponseEntity.ok(hospital);
    }

    @GetMapping("/searchByHospitalAddress/{address}")
    public ResponseEntity<List<HospitalResponseDto>> getHospitalByAddress(@PathVariable String address){
        List<HospitalResponseDto> hospital =  hospitalService.getHospitalByAddress(address);
        return ResponseEntity.ok(hospital);
    }

    @PostMapping("/saveHospital")
    public Hospital saveHospital(@RequestBody Hospital hospital){
        return hospitalService.saveHospital(hospital);
    }
    @DeleteMapping("/deleteHospital/{id}")
    public ResponseEntity<String> deleteHospital(@PathVariable Long id){
        if ( hospitalService.deleteHospital(id)) {
            return ResponseEntity.ok("Hospital deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Hospital not found with ID " + id);
        }
    }
}
