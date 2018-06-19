package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.SymptomDto;
import com.ftn.cdss.controller.dto.SymptomsDto;
import com.ftn.cdss.model.Symptom;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SymptomsConverter {

    public static List<Symptom> fromDto(List<SymptomDto> symptomsDto) {

        final List<Symptom> symptomList = new ArrayList<>();
        Symptom symp;
        for (SymptomDto symptomDto : symptomsDto) {
            symp = new Symptom();
            symp.setName(symptomDto.getName().trim());
            symp.setValue(symptomDto.getValue());
            symptomList.add(symp);
        }

        return symptomList;
    }

//    public static List<Symptom> fromDto(SymptomsDto symptomsDto) {
//
//        final List<Symptom> symptomList = new ArrayList<>();
//        Symptom symp;
//        for (String symptom : symptomsDto.getSymptoms()) {
//            symp = new Symptom();
//            symp.setName(symptom.trim());
//            symptomList.add(symp);
//        }
//
//        return symptomList;
//    }

    public static SymptomsDto toDto(Set<Symptom> symptoms) {
        final SymptomsDto symptomsDto = new SymptomsDto();
        final List<String> symps = new ArrayList<>();
        for (Symptom symptom : symptoms) {
            symps.add(symptom.getName());
        }
        symptomsDto.setSymptoms(symps);
        return symptomsDto;
    }
}
