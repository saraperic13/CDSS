package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.DoctorDto;
import com.ftn.cdss.model.Doctor;

public class DoctorConverter {

    public static DoctorDto toDto(Doctor doctor) {

        final DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setSurname(doctor.getSurname());
        doctorDto.setLicenceId(doctor.getLicenceId());

        return doctorDto;
    }

    public static Doctor fromDto(DoctorDto doctorDto) {

        final Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setId(doctorDto.getId());
        doctor.setSurname(doctorDto.getSurname());
        doctor.setLicenceId(doctorDto.getLicenceId());

        return doctor;
    }
}
