package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.AllergyDto;
import com.ftn.cdss.controller.dto.MedicalChartDto;
import com.ftn.cdss.model.Allergy;
import com.ftn.cdss.model.Diagnosis;
import com.ftn.cdss.model.MedicalChart;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicalChartConverter {

    public static MedicalChartDto toDto(MedicalChart medicalChart) {

        final MedicalChartDto medicalChartDto = new MedicalChartDto();
        medicalChartDto.setId(medicalChart.getId());
        medicalChartDto.setName(medicalChart.getName());
        medicalChartDto.setSsn(medicalChart.getSsn());
        medicalChartDto.setSurname(medicalChart.getSurname());
        final List<AllergyDto> allergies = medicalChart.getAllergies()
                .stream().map(AllergyConverter::toDto).collect(Collectors.toList());
        final List<Long> diagnosis = medicalChart.getDiagnosis()
                .stream().map(Diagnosis::getId).collect(Collectors.toList());
        medicalChartDto.setAllergies(allergies);
        medicalChartDto.setDiagnosis(diagnosis);

        return medicalChartDto;
    }

    public static MedicalChart fromDto(MedicalChartDto medicalChartDto) {

        final MedicalChart medicalChart = new MedicalChart();
        medicalChart.setId(medicalChartDto.getId());
        medicalChart.setName(medicalChartDto.getName());
        medicalChart.setSsn(medicalChartDto.getSsn());
        medicalChart.setSurname(medicalChartDto.getSurname());

        Set<Allergy> allergies = medicalChartDto.getAllergies()
                .stream().map(AllergyConverter::fromDto).collect(Collectors.toSet());
        medicalChart.setAllergies(allergies);

        return medicalChart;
    }
}
