package com.ftn.cdss.controller;

import com.ftn.cdss.controller.converter.DiseaseConverter;
import com.ftn.cdss.controller.converter.MedicalChartConverter;
import com.ftn.cdss.controller.converter.SymptomsConverter;
import com.ftn.cdss.controller.dto.MedicalChartDto;
import com.ftn.cdss.controller.dto.SymptomsDto;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.model.Symptom;
import com.ftn.cdss.service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/diagnostic_process")
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    @Autowired
    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    @PreAuthorize("hasAuthority('diagnose')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity diagnose(@RequestBody @Valid SymptomsDto symptomsDto) {

        List<Symptom> symptomList = SymptomsConverter.fromDto(symptomsDto);
        final Disease disease = this.diagnosticService.diagnose(symptomList);
        return new ResponseEntity<>(DiseaseConverter.toDto(disease), HttpStatus.CREATED);
    }
}
