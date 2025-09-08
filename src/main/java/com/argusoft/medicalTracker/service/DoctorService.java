package com.argusoft.medicalTracker.service;

import com.argusoft.medicalTracker.dto.DoctorDto;
import com.argusoft.medicalTracker.dto.DoctorResponseDto;
import com.argusoft.medicalTracker.entity.Doctor;
import com.argusoft.medicalTracker.entity.Hospital;
import com.argusoft.medicalTracker.repository.DoctorRepository;
import com.argusoft.medicalTracker.repository.HospitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<DoctorResponseDto> getAllDoctors(){
        return doctorRepository.findAll()
                .stream()
                .map(Doctor-> modelMapper.map(Doctor , DoctorResponseDto.class))
                .collect(Collectors.toList());
    }

    public String saveDoctor(DoctorDto req){
        Hospital hospital = hospitalRepository.findById(req.getHospitalId()).orElse(null);
        if(hospital == null){
            throw new RuntimeException("Hospital with id "+req.getHospitalId()+"is not found");
        }else{
            Doctor doctor = new Doctor();
            doctor.setName(req.getName());
            doctor.setSpecialization(req.getSpecialisation());
            doctor.setHospital(hospital);
            doctorRepository.save(doctor);
            return "Doctor saved successfully !";
        }
    }
    public DoctorResponseDto getDoctorById(Long id){
        return doctorRepository.findById(id)
                .map(Doctor-> modelMapper.map(Doctor , DoctorResponseDto.class)).orElseThrow();
    }

    public List<DoctorResponseDto> getDoctorByName(String name){
        return doctorRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(Doctor-> modelMapper.map(Doctor , DoctorResponseDto.class))
                .toList();
    }

    public List<DoctorResponseDto> getDoctorBySpecialisation(String specialisation){
        return doctorRepository.findBySpecializationContainingIgnoreCase(specialisation)
                .stream()
                .map(Doctor-> modelMapper.map(Doctor , DoctorResponseDto.class))
                .toList();
    }


    public boolean deleteDoctor(Long id){
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
