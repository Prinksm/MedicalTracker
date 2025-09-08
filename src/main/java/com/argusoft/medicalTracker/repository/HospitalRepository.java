package com.argusoft.medicalTracker.repository;

import com.argusoft.medicalTracker.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    List<Hospital> findByNameContainingIgnoreCase(String name);
    List<Hospital> findByAddressContainingIgnoreCase(String address);
}
