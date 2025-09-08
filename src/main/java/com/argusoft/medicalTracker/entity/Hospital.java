package com.argusoft.medicalTracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();
}
