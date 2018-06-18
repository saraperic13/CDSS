package com.ftn.cdss.service;

import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.Symptom;
import com.ftn.cdss.repository.DiseaseDao;
import com.ftn.cdss.repository.DoctorDao;
import com.ftn.cdss.repository.MedicalChartDao;
import com.ftn.cdss.repository.MedicineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosticService {

    private final MedicalChartDao medicalChartDao;

    private final DiseaseDao diseaseDao;

    private final DoctorDao doctorDao;

    private final MedicineDao medicineDao;

    @Autowired
    public DiagnosticService(MedicalChartDao medicalChartDao, DiseaseDao diseaseDao,
                             DoctorDao doctorDao, MedicineDao medicineDao) {
        this.medicalChartDao = medicalChartDao;
        this.diseaseDao = diseaseDao;
        this.doctorDao = doctorDao;
        this.medicineDao = medicineDao;
    }

    public Disease diagnose(List<Symptom> symptomList){

        return new Disease();
    }
}
