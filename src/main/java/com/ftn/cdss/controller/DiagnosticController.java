package com.ftn.cdss.controller;

import com.ftn.cdss.controller.converter.DiagnosisConverter;
import com.ftn.cdss.controller.converter.DiseaseConverter;
import com.ftn.cdss.controller.converter.MedicineConverter;
import com.ftn.cdss.controller.converter.SymptomsConverter;
import com.ftn.cdss.controller.dto.DiagnosisDto;
import com.ftn.cdss.controller.dto.DiseaseSymptomsDto;
import com.ftn.cdss.controller.dto.MedicineDto;
import com.ftn.cdss.controller.dto.SymptomDto;
import com.ftn.cdss.model.Diagnosis;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.Medicine;
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
import java.util.stream.Collectors;

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
    @PostMapping(value = "/calculate/{chartId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity calculate(@RequestBody @Valid List<SymptomDto> symptomsDto, @PathVariable Long chartId) {

        List<Symptom> symptomList = SymptomsConverter.fromDto(symptomsDto);
        final Disease disease = this.diagnosticService.calculate(symptomList, chartId);
        return new ResponseEntity<>(DiseaseConverter.toDto(disease), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('diagnose')")
    @PostMapping(value = "/validate_medicines/{chartId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity validateMedicines(@RequestBody List<MedicineDto> medicineDtos, @PathVariable Long chartId) {

        List<Medicine> medicines = medicineDtos.stream().map(MedicineConverter::fromDto).collect(Collectors.toList());
        final Boolean result = this.diagnosticService.validateMedicines(medicines, chartId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('diagnose')")
    @PostMapping(value = "/prescribe/{diagnosisId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity prescribeMedication(@RequestBody List<MedicineDto> medicineDtos,
                                              @PathVariable Long diagnosisId) {

        final List<Medicine> medicines =
                medicineDtos.stream().map(MedicineConverter::fromDto).collect(Collectors.toList());
        final Diagnosis diagnosis = this.diagnosticService.prescribeMedication(medicines, diagnosisId);

        return new ResponseEntity<>(DiagnosisConverter.toDto(diagnosis), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('diagnose')")
    @PostMapping(value = "/{chartId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity diagnose(@RequestBody @Valid DiagnosisDto diagnosisDto, @PathVariable Long chartId) {

        Diagnosis diagnosis = DiagnosisConverter.fromDto(diagnosisDto);
        diagnosis = this.diagnosticService.diagnose(diagnosis, chartId);
        return new ResponseEntity<>(DiagnosisConverter.toDto(diagnosis), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('diagnose')")
    @PostMapping(value = "/disease_symptoms",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity diseaseSymptom(@RequestBody @Valid DiseaseSymptomsDto diseaseSymptomsDto) {

        diseaseSymptomsDto = this.diagnosticService.getDiseaseSymptoms(diseaseSymptomsDto);
        return new ResponseEntity<>(diseaseSymptomsDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('diagnose')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {

        final List<DiagnosisDto> medicalChartDtos = diagnosticService.getAllActive()
                .stream().map(DiagnosisConverter::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(medicalChartDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('diagnose')")
    @GetMapping(value = "/{chartId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllForChart(@PathVariable Long chartId) {

        final List<DiagnosisDto> medicalChartDtos = diagnosticService.getAllActiveForChart(chartId)
                .stream().map(DiagnosisConverter::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(medicalChartDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('diagnose')")
    @DeleteMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        diagnosticService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
