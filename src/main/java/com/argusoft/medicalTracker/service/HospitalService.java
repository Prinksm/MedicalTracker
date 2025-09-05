package com.argusoft.medicalTracker.service;

import com.argusoft.medicalTracker.entity.Hospital;
import com.argusoft.medicalTracker.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Hospital> getAllHospital(){
        return hospitalRepository.findAll();
    }

    public Hospital saveHospital(Hospital hospital){
        return hospitalRepository.save(hospital);
    }

    public void deleteHospital(Long id){
        hospitalRepository.deleteById(id);
    }
}
