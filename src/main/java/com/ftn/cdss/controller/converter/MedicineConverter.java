package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.MedicineDto;
import com.ftn.cdss.model.Medicine;
import com.ftn.cdss.model.MedicineType;

import java.util.ArrayList;
import java.util.HashSet;

public class MedicineConverter {

    public static Medicine fromDto(MedicineDto medicineDto) {

        final Medicine medicine = new Medicine();
        medicine.setId(medicineDto.getId());
        medicine.setName(medicineDto.getName());
        medicine.setType(MedicineType.values()[medicineDto.getType()]);
        medicine.setIngredients(new HashSet<>(medicineDto.getIngredients()));
        return medicine;
    }

    public static MedicineDto toDto(Medicine medicine) {

        final MedicineDto medicineDto = new MedicineDto();
        medicineDto.setId(medicine.getId());
        medicineDto.setName(medicine.getName());
        medicineDto.setType(medicine.getType().ordinal());
        medicineDto.setIngredients(new ArrayList<>(medicine.getIngredients()));
        return medicineDto;
    }

}
