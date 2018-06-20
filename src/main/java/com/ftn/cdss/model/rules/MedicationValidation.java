package com.ftn.cdss.model.rules;

import com.ftn.cdss.model.Allergy;
import com.ftn.cdss.model.Medicine;
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
public class MedicationValidation {

    private Long chartId;

    private Boolean valid;

    private List<Medicine> medicines = new ArrayList<>();

    private List<Allergy> allergies = new ArrayList<>();
}
