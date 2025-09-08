package com.argusoft.medicalTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    private Patient patient;

}
