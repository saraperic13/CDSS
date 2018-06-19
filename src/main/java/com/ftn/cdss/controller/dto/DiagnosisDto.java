package com.ftn.cdss.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DiagnosisDto {

    private Long id;

    private Date date;

    private String doctorName;

    private String diseaseName;

    private Long diseaseId;

    private String symptomsInput;

    List<MedicineDto> medicines;
}
