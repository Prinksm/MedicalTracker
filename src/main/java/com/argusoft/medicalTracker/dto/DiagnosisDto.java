package com.argusoft.medicalTracker.dto;

import lombok.Data;

@Data
public class DiagnosisDto {
    private String description;
    private Long patientId;
}
