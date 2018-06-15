package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.repository.MedicalChartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalChartService {

    private final MedicalChartDao medicalChartDao;

    @Autowired
    public MedicalChartService(MedicalChartDao medicalChartDao) {
        this.medicalChartDao = medicalChartDao;
    }

    public MedicalChart create(MedicalChart medicalChart) {
        return medicalChartDao.save(medicalChart);
    }

    public MedicalChart update(MedicalChart medicalChart) {
        findOne(medicalChart.getId());
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
