package com.ftn.cdss.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseSymptomsDto {

    private Long id;

    private String name;

    private List<String> commonSymptoms= new ArrayList<>();
    private List<String> specificSymptoms= new ArrayList<>();
}
