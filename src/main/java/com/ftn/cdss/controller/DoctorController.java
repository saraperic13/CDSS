package com.ftn.cdss.controller;

import com.ftn.cdss.controller.converter.DoctorConverter;
import com.ftn.cdss.controller.dto.DoctorDto;
import com.ftn.cdss.model.Doctor;
import com.ftn.cdss.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PreAuthorize("hasAuthority('crudDoctor')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody @Valid DoctorDto doctorDto) {

        Doctor doctor = DoctorConverter.fromDto(doctorDto);
        doctor = this.doctorService.create(doctor, doctorDto.getUsername(), doctorDto.getPassword());
        return new ResponseEntity<>(DoctorConverter.toDto(doctor), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('crudDoctor')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody DoctorDto doctorDto) {

        Doctor doctor = DoctorConverter.fromDto(doctorDto);
        doctor = this.doctorService.update(doctor);
        return new ResponseEntity<>(DoctorConverter.toDto(doctor), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('crudDoctor')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {

        final List<DoctorDto> doctorDtos = new ArrayList<>();
        for (Doctor doctor : doctorService.getAll()) {
            doctorDtos.add(DoctorConverter.toDto(doctor));
        }
        return new ResponseEntity<>(doctorDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudDoctor')")
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOne(@PathVariable Long id) {

        final Doctor doctor = doctorService.findOne(id);
        return new ResponseEntity<>(DoctorConverter.toDto(doctor), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudDoctor')")
    @DeleteMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        doctorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
