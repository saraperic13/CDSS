package com.ftn.cdss.service;

import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.model.rules.PossibleDisease;
import com.ftn.cdss.model.Symptom;
import com.ftn.cdss.model.rules.Symptoms;
import com.ftn.cdss.repository.MedicineDao;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosticService {

    private final MedicalChartService medicalChartService;

    private final DiseaseService diseaseService;

    private final DoctorService doctorService;

    private final MedicineDao medicineDao;

    private final KieContainer kieContainer;

    @Autowired
    public DiagnosticService(MedicalChartService medicalChartService, DiseaseService diseaseService,
                             DoctorService doctorService, MedicineDao medicineDao, KieContainer kieContainer) {
        this.medicalChartService = medicalChartService;
        this.diseaseService = diseaseService;
        this.doctorService = doctorService;
        this.medicineDao = medicineDao;

        this.kieContainer = kieContainer;
    }

    public Disease diagnose(List<Symptom> symptomList, Long chartId) {

        final MedicalChart medicalChart = medicalChartService.findOne(chartId);

        final KieSession kieSession = kieContainer.newKieSession();
        final List<Disease> diseases = diseaseService.getAll();

        final PossibleDisease possibleDisease = new PossibleDisease();

        kieSession.insert(diseases);

        for(Symptom s : symptomList){
            kieSession.insert(s);
        }
        kieSession.insert(medicalChart);
        kieSession.insert(possibleDisease);

        kieSession.fireAllRules();

        kieSession.destroy();

        final Disease disease = diseaseService.findOne(possibleDisease.getDiseaseId());
        return disease;
    }
}
