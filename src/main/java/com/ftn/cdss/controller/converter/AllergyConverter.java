package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.AllergyDto;
import com.ftn.cdss.model.Allergy;
import com.ftn.cdss.model.Ingredient;
import com.ftn.cdss.model.Medicine;

public class AllergyConverter {

    public static AllergyDto toDto(Allergy allergy) {

        final AllergyDto allergyDto = new AllergyDto();
        allergyDto.setId(allergy.getId());
        allergyDto.setIngredientId(allergy.getIngredient().getId());
        allergyDto.setIngredientName(allergy.getIngredient().getName());
        return allergyDto;
    }

    public static Allergy fromDto(AllergyDto allergyDto) {

        final Allergy allergy = new Allergy();
        allergy.setId(allergyDto.getId());

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(allergy.getIngredient().getId());

        final Medicine medicine = new Medicine();
        medicine.setId(allergy.getMedicine().getId());

        allergy.setIngredient(ingredient);
        allergy.setMedicine(medicine);

        return allergy;
    }
}
