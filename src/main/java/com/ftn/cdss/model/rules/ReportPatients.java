package com.ftn.cdss.model.rules;

import com.ftn.cdss.model.Diagnosis;
import com.ftn.cdss.model.MedicalChart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportPatients {

    private List<MedicalChart> reportChronicPatients;

    private List<MedicalChart> reportAddicts;

    private List<MedicalChart> reportLowImmuneSystem;

    private List<Diagnosis> diagnosisList;
}
