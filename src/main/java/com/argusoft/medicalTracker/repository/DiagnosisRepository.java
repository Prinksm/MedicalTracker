package com.argusoft.medicalTracker.repository;

import com.argusoft.medicalTracker.entity.Diagnosis;
import com.argusoft.medicalTracker.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
    List<Diagnosis> findByPatient(Patient patient);
}
