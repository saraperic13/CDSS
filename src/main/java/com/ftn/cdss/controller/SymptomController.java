package com.ftn.cdss.controller;

import com.ftn.cdss.controller.converter.SymptomsConverter;
import com.ftn.cdss.controller.dto.SymptomDto;
import com.ftn.cdss.model.Symptom;
import com.ftn.cdss.service.SymptomService;
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
@RequestMapping("/api/symptoms")
public class SymptomController {

    private final SymptomService symptomService;

    @Autowired
    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @PreAuthorize("hasAuthority('readDiseases')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {

        final List<SymptomDto> symptomDtos = symptomService.getAll()
                .stream().map(SymptomsConverter::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(symptomDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudDiseases')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody @Valid SymptomDto symptomDto) {

        Symptom symptom = SymptomsConverter.fromDto(symptomDto);
        symptom = this.symptomService.create(symptom);
        return new ResponseEntity<>(SymptomsConverter.toDto(symptom), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('crudDiseases')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid SymptomDto symptomDto) {

        Symptom symptom = SymptomsConverter.fromDto(symptomDto);
        symptom = this.symptomService.update(symptom);
        return new ResponseEntity<>(SymptomsConverter.toDto(symptom), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudDiseases')")
    @DeleteMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        symptomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
