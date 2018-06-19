package com.ftn.cdss.controller;

import com.ftn.cdss.controller.converter.DiseaseConverter;
import com.ftn.cdss.controller.dto.DiseaseDto;
import com.ftn.cdss.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
