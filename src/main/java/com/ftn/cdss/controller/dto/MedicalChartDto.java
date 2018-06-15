package com.ftn.cdss.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalChartDto {

    private Long id;

    private Integer ssn;

    private String name;

    private String surname;

    private List<Long> diagnosis;

    private List<Long> allergies;
}
