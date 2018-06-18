package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.DiseaseDto;
import com.ftn.cdss.controller.dto.SymptomsDto;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.Symptom;

import java.util.List;
import java.util.stream.Collectors;

public class DiseaseConverter {

    public static DiseaseDto toDto(Disease disease) {

        final DiseaseDto diseaseDto = new DiseaseDto();
        diseaseDto.setId(disease.getId());
        diseaseDto.setName(disease.getName());

        final List<String> symptoms = disease.getSymptoms()
                .stream().map(Symptom::getName).collect(Collectors.toList());

        diseaseDto.setSymptoms(new SymptomsDto(symptoms));

        return diseaseDto;
    }
}
