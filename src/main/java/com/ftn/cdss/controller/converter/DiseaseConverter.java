package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.DiseaseDto;
import com.ftn.cdss.controller.dto.SymptomDto;
import com.ftn.cdss.model.Disease;

import java.util.List;
import java.util.stream.Collectors;

public class DiseaseConverter {

    public static DiseaseDto toDto(Disease disease) {

        final DiseaseDto diseaseDto = new DiseaseDto();
        diseaseDto.setId(disease.getId());
        diseaseDto.setName(disease.getName());

        final List<SymptomDto> commonSymptoms = disease.getCommonSymptoms().stream()
                .map(symptom -> new SymptomDto(symptom.getName(), symptom.getValue())).collect(Collectors.toList());

        final List<SymptomDto> specificSymptoms = disease.getSpecificSymptoms().stream()
                .map(symptom -> new SymptomDto(symptom.getName(), symptom.getValue())).collect(Collectors.toList());

        diseaseDto.setCommonSymptoms(commonSymptoms);
        diseaseDto.setSpecificSymptoms(specificSymptoms);
        diseaseDto.setType(disease.getDiseaseType().ordinal() + 1);

        return diseaseDto;
    }
}
