package com.ftn.cdss.controller;

import com.ftn.cdss.controller.converter.MedicalChartConverter;
import com.ftn.cdss.controller.dto.MedicalChartDto;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/reports/")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PreAuthorize("hasAuthority('readDiseases')")
    @GetMapping(value = "chronic",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getChronic() {

        final List<MedicalChartDto> medicalChartDtos = new ArrayList<>();
        for (MedicalChart medicalChart : reportService.getChronic()) {
            medicalChartDtos.add(MedicalChartConverter.toDto(medicalChart));
        }
        return new ResponseEntity<>(medicalChartDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('readDiseases')")
    @GetMapping(value = "addicts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAddicts() {

        final List<MedicalChartDto> medicalChartDtos = new ArrayList<>();
        for (MedicalChart medicalChart : reportService.getAddicts()) {
            medicalChartDtos.add(MedicalChartConverter.toDto(medicalChart));
        }
        return new ResponseEntity<>(medicalChartDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('readDiseases')")
    @GetMapping(value = "low_immune_system",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getLowImmuneSystem() {

        final List<MedicalChartDto> medicalChartDtos = new ArrayList<>();
        for (MedicalChart medicalChart : reportService.getLowImmuneSystem()) {
            medicalChartDtos.add(MedicalChartConverter.toDto(medicalChart));
        }
        return new ResponseEntity<>(medicalChartDtos, HttpStatus.OK);
    }

}
