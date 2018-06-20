package com.ftn.cdss.controller.dto;

import com.ftn.cdss.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineDto {

    private Long id;

    private String name;

    private int type;

    private List<Ingredient> ingredients;
}
