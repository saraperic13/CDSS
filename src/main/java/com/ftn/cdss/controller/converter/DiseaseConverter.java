package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.DiseaseDto;
import com.ftn.cdss.controller.dto.SymptomDto;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.DiseaseType;
import com.ftn.cdss.model.Symptom;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiseaseConverter {

    public static DiseaseDto toDto(Disease disease) {

        final DiseaseDto diseaseDto = new DiseaseDto();
        diseaseDto.setId(disease.getId());
        diseaseDto.setName(disease.getName());

        final List<SymptomDto> commonSymptoms = disease.getCommonSymptoms().stream().map(symptom
                -> new SymptomDto(symptom.getId(), symptom.getName(), symptom.getValue())).collect(Collectors.toList());

        final List<SymptomDto> specificSymptoms = disease.getSpecificSymptoms().stream().map(symptom
                -> new SymptomDto(symptom.getId(), symptom.getName(), symptom.getValue())).collect(Collectors.toList());

        diseaseDto.setCommonSymptoms(commonSymptoms);
        diseaseDto.setSpecificSymptoms(specificSymptoms);
        diseaseDto.setType(disease.getDiseaseType().ordinal() + 1);

        return diseaseDto;
    }

    public static Disease fromDto(DiseaseDto diseaseDto) {

        final Disease disease = new Disease();
        disease.setId(diseaseDto.getId());
        disease.setName(diseaseDto.getName());

        final List<Symptom> commonSymptoms = SymptomsConverter.fromDtos(diseaseDto.getCommonSymptoms());
        final List<Symptom> specificSymptoms = SymptomsConverter.fromDtos(diseaseDto.getSpecificSymptoms());

        final Set<Symptom> symptomSet = new HashSet<>();
        symptomSet.addAll(commonSymptoms);
        final Set<Symptom> symptomSet1 = new HashSet<>();
        symptomSet1.addAll(specificSymptoms);

        disease.setCommonSymptoms(symptomSet);
        disease.setSpecificSymptoms(symptomSet1);
        disease.setDiseaseType(DiseaseType.values()[diseaseDto.getType()]);

        return disease;
    }
}
