package com.argusoft.medicalTracker.controller;
import com.argusoft.medicalTracker.dto.DiagnosisDto;
import com.argusoft.medicalTracker.dto.DiagnosisResponseDto;
import com.argusoft.medicalTracker.entity.Diagnosis;
import com.argusoft.medicalTracker.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {
    @Autowired
    private DiagnosisService diagnosisService;

    @GetMapping
    public ResponseEntity<List<DiagnosisResponseDto>> getAllDiagnosis(){
        List<DiagnosisResponseDto>diagnosis = diagnosisService.getAllDiagnosis();
        return ResponseEntity.ok(diagnosis) ;
    }

    @PostMapping("/saveDiagnosis")
    public ResponseEntity<String> saveDiagnosis(@RequestBody DiagnosisDto diagnosis){
        return ResponseEntity.status(201).body(diagnosisService.saveDiagnosis(diagnosis));
    }

    @GetMapping("/getDiagnosisByPatient/{id}")
    public ResponseEntity<List<DiagnosisResponseDto>> getDiagnosisByPatientId(@PathVariable Long id){
        List<DiagnosisResponseDto>diagnosis = diagnosisService.getDiagnosisByPatientId(id);
        return ResponseEntity.ok(diagnosis) ;
    }
}
