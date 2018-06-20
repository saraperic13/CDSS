package com.ftn.cdss.model.rules;

import com.ftn.cdss.model.Diagnosis;
import com.ftn.cdss.model.MedicalChart;
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
public class ReportPatients {

    private List<MedicalChart> reportChronicPatients = new ArrayList<>();

    private List<MedicalChart> reportAddicts = new ArrayList<>();

    private List<MedicalChart> reportLowImmuneSystem = new ArrayList<>();

    private List<Diagnosis> diagnosisList;
}
