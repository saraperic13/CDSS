package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.AllergyDto;
import com.ftn.cdss.model.Allergy;

public class AllergyConverter {

    public static AllergyDto toDto(Allergy allergy) {

        final AllergyDto allergyDto = new AllergyDto();
        allergyDto.setId(allergy.getId());
        allergyDto.setName(allergy.getName());

        return allergyDto;
    }

    public static Allergy fromDto(AllergyDto allergyDto) {

        final Allergy allergy = new Allergy();
        allergy.setId(allergyDto.getId());
        allergy.setName(allergyDto.getName());

        return allergy;
    }
}
