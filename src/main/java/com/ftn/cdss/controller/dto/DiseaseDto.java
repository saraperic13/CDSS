package com.ftn.cdss.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DiseaseDto {

    private Long id;

    private String name;

    private SymptomsDto symptoms;
}
