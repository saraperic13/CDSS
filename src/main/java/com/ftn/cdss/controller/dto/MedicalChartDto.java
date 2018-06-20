package com.ftn.cdss.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalChartDto {

    private Long id;

    @NotNull
    private Integer ssn;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private List<Long> diagnosis;

    private List<AllergyDto> allergies;
}
