package com.argusoft.medicalTracker.dto;

import lombok.Data;

@Data
public class PatientDto {
    private String name;
    private int age;
    private String gender;
    private Long doctorId;
}
