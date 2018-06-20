package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.*;
import com.ftn.cdss.model.rules.MedicationValidation;
import com.ftn.cdss.model.rules.PossibleDisease;
import com.ftn.cdss.repository.DiagnosisDao;
import com.ftn.cdss.repository.MedicineDao;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DiagnosticService {

    private final MedicalChartService medicalChartService;

    private final DiseaseService diseaseService;

    private final DoctorService doctorService;

    private final MedicineService medicineService;

    private final DiagnosisDao diagnosisDao;

    private final KieSession kieSession;

    @Autowired
    public DiagnosticService(MedicalChartService medicalChartService, DiseaseService diseaseService,
                             DoctorService doctorService, MedicineService medicineService,
                             DiagnosisDao diagnosisDao, KieSession kieSession) {
        this.medicalChartService = medicalChartService;
        this.diseaseService = diseaseService;
        this.doctorService = doctorService;
        this.medicineService = medicineService;
        this.diagnosisDao = diagnosisDao;
        this.kieSession = kieSession;
    }

    public Disease calculate(List<Symptom> symptomList, Long chartId) {

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

    public Diagnosis diagnose(Diagnosis diagnosis, Long chartId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final Doctor doctor = doctorService.findByUsername(username);
        diagnosis.setDoctor(doctor);

        MedicalChart medicalChart = medicalChartService.findOne(chartId);

        diagnosis.setMedicalChart(medicalChart);

        diagnosis = diagnosisDao.save(diagnosis);

        medicalChart.getDiagnosis().add(diagnosis);

        medicalChartService.update(medicalChart);
        return diagnosis;
    }

    public List<Diagnosis> getAllActive() {
        return diagnosisDao.findAllByActiveIsTrue();
    }

    public Diagnosis findById(Long id) {
        return this.diagnosisDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Diagnosis not found!"));
    }

    public void delete(Long id) {
        final Diagnosis diagnosis = findById(id);
        diagnosis.setActive(false);

        diagnosisDao.save(diagnosis);
    }

    public List<Diagnosis> getAllActiveForChart(Long chartId) {
        return diagnosisDao.findAllByActiveIsTrueAndMedicalChartId(chartId);
    }

    public Boolean validateMedicines(List<Medicine> medicines, Long chartId) {

        medicines =  medicines.stream().map(medicine -> medicineService.findOne(medicine.getId()))
                .collect(Collectors.toList());

        final MedicalChart medicalChart = medicalChartService.findOne(chartId);

        final MedicationValidation medicationValidation = new MedicationValidation();
        medicationValidation.getAllergies().addAll(medicalChart.getAllergies());
        medicationValidation.getMedicines().addAll(medicines);
        medicationValidation.setChartId(chartId);
        medicationValidation.setValid(true);

        kieSession.insert(medicationValidation);

        kieSession.getAgenda().getAgendaGroup("medicines").setFocus();
        kieSession.fireAllRules();

        kieSession.destroy();

        return medicationValidation.getValid();
    }
}
