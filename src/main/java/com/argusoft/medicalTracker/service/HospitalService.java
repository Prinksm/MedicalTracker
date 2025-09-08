package com.argusoft.medicalTracker.service;

import com.argusoft.medicalTracker.dto.DoctorResponseDto;
import com.argusoft.medicalTracker.dto.HospitalResponseDto;
import com.argusoft.medicalTracker.entity.Hospital;
import com.argusoft.medicalTracker.repository.HospitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<HospitalResponseDto> getAllHospital(){
        return hospitalRepository.findAll()
                .stream()
                .map(Hospital-> modelMapper.map(Hospital , HospitalResponseDto.class))
                .toList();
    }

    public HospitalResponseDto getHospitalById(Long id){
        return hospitalRepository.findById(id)
                .map(Hospital-> modelMapper.map(Hospital , HospitalResponseDto.class)).orElseThrow();
    }

    public List<HospitalResponseDto> getHospitalByName(String name){
        return hospitalRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(Hospital-> modelMapper.map(Hospital , HospitalResponseDto.class))
                .toList();
    }

    public List<HospitalResponseDto> getHospitalByAddress(String address){
        return hospitalRepository.findByAddressContainingIgnoreCase(address)
                .stream()
                .map(Hospital-> modelMapper.map(Hospital , HospitalResponseDto.class))
                .toList();
    }


    public Hospital saveHospital(Hospital hospital){
        return hospitalRepository.save(hospital);
    }

    public boolean deleteHospital(Long id){
        if (hospitalRepository.existsById(id)) {
            hospitalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
