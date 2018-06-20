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
public class AllergyDto {

    private Long id;

    private Long ingredientId;

    private String ingredientName;

    private Long medicineId;

    private String medicineName;
}
