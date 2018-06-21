package com.ftn.cdss.controller;

import com.ftn.cdss.controller.converter.DiseaseConverter;
import com.ftn.cdss.controller.dto.DiseaseDto;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.service.DiseaseService;
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
@RequestMapping("/api/diseases")
public class DiseaseController {

    private final DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PreAuthorize("hasAuthority('readDiseases')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {

        final List<DiseaseDto> diseaseDtos = diseaseService.getAll()
                .stream().map(DiseaseConverter::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(diseaseDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudDiseases')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody @Valid DiseaseDto diseaseDto) {

        Disease disease = DiseaseConverter.fromDto(diseaseDto);
        disease = this.diseaseService.create(disease);
        return new ResponseEntity<>(DiseaseConverter.toDto(disease), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('crudDiseases')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid DiseaseDto diseaseDto) {

        Disease disease = DiseaseConverter.fromDto(diseaseDto);
        disease = this.diseaseService.update(disease);
        return new ResponseEntity<>(DiseaseConverter.toDto(disease), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudDiseases')")
    @DeleteMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        diseaseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
