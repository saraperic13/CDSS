package com.ftn.cdss.service;

import com.ftn.cdss.model.Diagnosis;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.model.Symptom;
import com.ftn.cdss.model.rules.PossibleDisease;
import com.ftn.cdss.repository.MedicineDao;
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

    private final KieSession kieSession;

    @Autowired
    public DiagnosticService(MedicalChartService medicalChartService, DiseaseService diseaseService,
                             DoctorService doctorService, MedicineDao medicineDao, KieSession kieSession) {
        this.medicalChartService = medicalChartService;
        this.diseaseService = diseaseService;
        this.doctorService = doctorService;
        this.medicineDao = medicineDao;
        this.kieSession = kieSession;
    }

    public Disease diagnose(List<Symptom> symptomList, Long chartId) {

        final MedicalChart medicalChart = medicalChartService.findOne(chartId);
        final PossibleDisease possibleDisease = new PossibleDisease();

        for (Symptom s : symptomList) {
            kieSession.insert(s);
        }
        for (Diagnosis d : medicalChart.getDiagnosis()) {
            kieSession.insert(d);
        }
        kieSession.insert(possibleDisease);

        kieSession.getAgenda().getAgendaGroup("symptoms").setFocus();
        kieSession.fireAllRules();

        kieSession.destroy();

        return diseaseService.findByName(possibleDisease.getName());
    }
}
