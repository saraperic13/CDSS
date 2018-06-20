package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.Allergy;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.repository.AllergyDao;
import com.ftn.cdss.repository.MedicalChartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicalChartService {

    private final MedicalChartDao medicalChartDao;

    private final AllergyDao allergyDao;

    @Autowired
    public MedicalChartService(MedicalChartDao medicalChartDao, AllergyDao allergyDao) {
        this.medicalChartDao = medicalChartDao;
        this.allergyDao = allergyDao;
    }

    public MedicalChart create(MedicalChart medicalChart) {

        Set<Allergy> allergies = medicalChart.getAllergies().stream()
                .map(allergyDao::save).collect(Collectors.toSet());

        medicalChart.setAllergies(allergies);
        return medicalChartDao.save(medicalChart);
    }

    public MedicalChart update(MedicalChart medicalChart) {
        findOne(medicalChart.getId());

        Set<Allergy> allergies = medicalChart.getAllergies().stream()
                .map(allergyDao::save).collect(Collectors.toSet());

        medicalChart.setAllergies(allergies);
        return medicalChartDao.save(medicalChart);
    }

    public void delete(Long id) {
        final MedicalChart medicalChart = findOne(id);
        medicalChartDao.delete(medicalChart);
    }

    public List<MedicalChart> getAll() {
        return medicalChartDao.findAll();
    }

    public MedicalChart findOne(Long id) {
        return medicalChartDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Medical chart not found!"));
    }
}
