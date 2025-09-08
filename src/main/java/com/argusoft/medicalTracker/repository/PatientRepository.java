package com.argusoft.medicalTracker.repository;

import com.argusoft.medicalTracker.entity.Doctor;
import com.argusoft.medicalTracker.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByNameContainingIgnoreCase(String name);
    List<Patient> findByDoctorNameContainingIgnoreCase(String doctorName);
}
