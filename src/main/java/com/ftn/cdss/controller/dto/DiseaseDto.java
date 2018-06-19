package com.ftn.cdss.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DiseaseDto {

    private Long id;

    private String name;

    private int type;

    private List<SymptomDto> commonSymptoms;

    private List<SymptomDto> specificSymptoms;
}
