package com.ftn.cdss.service;

import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.model.rules.ReportPatients;
import com.ftn.cdss.repository.DiagnosisDao;
import com.ftn.cdss.repository.DiseaseDao;
import com.ftn.cdss.repository.MedicalChartDao;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final MedicalChartDao medicalChartDao;

    private final DiagnosisDao diagnosisDao;

    private final DiseaseDao diseaseDao;

    private final KieSession kieSession;

    @Autowired
    public ReportService(MedicalChartDao medicalChartDao, DiagnosisDao diagnosisDao,
                         DiseaseDao diseaseDao, KieSession kieSession) {
        this.medicalChartDao = medicalChartDao;
        this.diagnosisDao = diagnosisDao;
        this.diseaseDao = diseaseDao;
        this.kieSession = kieSession;
    }

    public List<MedicalChart> getChronic() {

        final ReportPatients reportPatients = insertNeeded();
        kieSession.getAgenda().getAgendaGroup("chronic_disease").setFocus();
        kieSession.fireAllRules();

        kieSession.destroy();

        return reportPatients.getReportChronicPatients();
    }

    public List<MedicalChart> getAddicts() {

        final ReportPatients reportPatients = insertNeeded();
        kieSession.getAgenda().getAgendaGroup("addicts").setFocus();
        kieSession.fireAllRules();

        kieSession.destroy();

        return reportPatients.getReportAddicts();
    }

    public List<MedicalChart> getLowImmuneSystem() {

        final ReportPatients reportPatients = insertNeeded();
        kieSession.getAgenda().getAgendaGroup("addicts").setFocus();
        kieSession.fireAllRules();

        kieSession.destroy();

        return reportPatients.getReportLowImmuneSystem();
    }

    private ReportPatients insertNeeded() {
        final ReportPatients reportPatients = new ReportPatients();
        reportPatients.setDiagnosisList(diagnosisDao.findAllByActiveIsTrue());

        kieSession.insert(reportPatients);

        final List<Disease> diseases = diseaseDao.findAllByActiveIsTrue();
        diseases.forEach(kieSession::insert);

        final List<MedicalChart> medicalCharts = medicalChartDao.findAll();
        medicalCharts.forEach(kieSession::insert);

        return reportPatients;
    }
}
