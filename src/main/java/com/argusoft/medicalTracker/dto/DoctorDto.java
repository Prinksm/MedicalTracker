package com.argusoft.medicalTracker.dto;

import lombok.Data;

@Data
public class DoctorDto {
    private String name;
    private String specialisation;
    private Long HospitalId;
}
