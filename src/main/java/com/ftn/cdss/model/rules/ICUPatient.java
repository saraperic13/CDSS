package com.ftn.cdss.model.rules;

import com.ftn.cdss.model.MedicalChart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ICUPatient {

    private Integer ssn;

    private MedicalChart medicalChart;

    private double bloodOxygenLevel;

}
