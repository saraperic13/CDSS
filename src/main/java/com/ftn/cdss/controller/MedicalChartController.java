package com.ftn.cdss.controller;

import com.ftn.cdss.controller.converter.MedicalChartConverter;
import com.ftn.cdss.controller.dto.MedicalChartDto;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.service.MedicalChartService;
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
@RequestMapping("/api/medical_charts")
public class MedicalChartController {

    private final MedicalChartService medicalChartService;

    @Autowired
    public MedicalChartController(MedicalChartService medicalChartService) {
        this.medicalChartService = medicalChartService;
    }

    @PreAuthorize("hasAuthority('crudMedicalChart')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody @Valid MedicalChartDto medicalChartDto) {

        MedicalChart medicalChart = MedicalChartConverter.fromDto(medicalChartDto);
        medicalChart = this.medicalChartService.create(medicalChart);
        return new ResponseEntity<>(MedicalChartConverter.toDto(medicalChart), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('crudMedicalChart')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid MedicalChartDto medicalChartDto) {

        MedicalChart medicalChart = MedicalChartConverter.fromDto(medicalChartDto);
        medicalChart = this.medicalChartService.update(medicalChart);
        return new ResponseEntity<>(MedicalChartConverter.toDto(medicalChart), HttpStatus.CREATED);
    }


//    @PreAuthorize("hasAuthority('crudMedicalChart')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {

        final List<MedicalChartDto> medicalChartDtos = new ArrayList<>();
        for (MedicalChart medicalChart : medicalChartService.getAll()) {
            medicalChartDtos.add(MedicalChartConverter.toDto(medicalChart));
        }
        return new ResponseEntity<>(medicalChartDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudMedicalChart')")
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOne(@PathVariable Long id) {

        final MedicalChart medicalChart = medicalChartService.findOne(id);
        return new ResponseEntity<>(MedicalChartConverter.toDto(medicalChart), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudMedicalChart')")
    @DeleteMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        medicalChartService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
