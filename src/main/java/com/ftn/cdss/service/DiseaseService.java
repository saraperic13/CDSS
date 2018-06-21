package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.Symptom;
import com.ftn.cdss.repository.DiseaseDao;
import com.ftn.cdss.repository.SymptomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DiseaseService {

    private final DiseaseDao diseaseDao;

    private final SymptomDao symptomDao;

    @Autowired
    public DiseaseService(DiseaseDao diseaseDao, SymptomDao symptomDao) {
        this.diseaseDao = diseaseDao;
        this.symptomDao = symptomDao;
    }

    public List<Disease> getAll() {
        return this.diseaseDao.findAllByActiveIsTrue();
    }

    public Disease findOne(Long id) {
        if (id == null) {
            throw new EntityNotFoundException("Disease not found!");
        }
        return diseaseDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Disease not found!"));
    }

    public Disease findByName(String name) {
        if (name == null) {
            throw new EntityNotFoundException("Disease not found!");
        }
        return diseaseDao.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Disease not found!"));
    }

    public Disease create(Disease disease) {

        Set<Symptom> symptoms = disease.getCommonSymptoms().stream()
                .map(symptom -> symptomDao.findById(symptom.getId()).get()).collect(Collectors.toSet());
        disease.setCommonSymptoms(symptoms);

        Set<Symptom> specificSymptoms = disease.getSpecificSymptoms().stream()
                .map(symptom -> symptomDao.findById(symptom.getId()).get()).collect(Collectors.toSet());
        disease.setSpecificSymptoms(specificSymptoms);

        return diseaseDao.save(disease);
    }

    public Disease update(Disease disease) {
        findOne(disease.getId());

        Set<Symptom> symptoms = disease.getCommonSymptoms().stream()
                .map(symptom -> symptomDao.findById(symptom.getId()).get()).collect(Collectors.toSet());
        disease.setCommonSymptoms(symptoms);

        Set<Symptom> specificSymptoms = disease.getSpecificSymptoms().stream()
                .map(symptom -> symptomDao.findById(symptom.getId()).get()).collect(Collectors.toSet());
        disease.setSpecificSymptoms(specificSymptoms);

        return diseaseDao.save(disease);
    }

    public void delete(Long id) {
        final Disease disease = findOne(id);
        disease.setActive(false);
        diseaseDao.save(disease);
    }
}
