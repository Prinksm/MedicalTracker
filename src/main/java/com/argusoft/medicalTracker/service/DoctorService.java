package com.argusoft.medicalTracker.service;

import com.argusoft.medicalTracker.dto.DoctorDto;
import com.argusoft.medicalTracker.entity.Doctor;
import com.argusoft.medicalTracker.entity.Hospital;
import com.argusoft.medicalTracker.repository.DoctorRepository;
import com.argusoft.medicalTracker.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor saveDoctor(DoctorDto req){
        Hospital hospital = hospitalRepository.findById(req.getHospitalId()).orElse(null);
        if(hospital == null){
            throw new RuntimeException("Hospital with id "+req.getHospitalId()+"is not found");
        }else{
            Doctor doctor = new Doctor();
            doctor.setName(req.getName());
            doctor.setSpecialization(req.getSpecialisation());
            doctor.setHospital(hospital);
            return doctorRepository.save(doctor);
        }
    }

    public void deleteDoctor(Long id){
        doctorRepository.deleteById(id);
    }
}
