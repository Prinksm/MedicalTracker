package com.argusoft.medicalTracker.controller;
import com.argusoft.medicalTracker.dto.DiagnosisDto;
import com.argusoft.medicalTracker.entity.Diagnosis;
import com.argusoft.medicalTracker.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {
    @Autowired
    private DiagnosisService diagnosisService;

    public List<Diagnosis> getAllDiagnosis(){
        return diagnosisService.getAllDiagnosis();
    }

    @PostMapping("/saveDiagnosis")
    public Diagnosis saveDiagnosis(@RequestBody DiagnosisDto diagnosis){
        return diagnosisService.saveDiagnosis(diagnosis);
    }

    @PostMapping("/getDiagnosisByPatient")
    public List<Diagnosis> getDiagnosisByPatientId(@PathVariable Long id){
        return diagnosisService.getDiagnosisByPatientId(id);
    }
}
