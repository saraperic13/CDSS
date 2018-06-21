package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.Symptom;
import com.ftn.cdss.repository.SymptomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomService {

    private final SymptomDao symptomDao;

    @Autowired
    public SymptomService(SymptomDao symptomDao) {
        this.symptomDao = symptomDao;
    }

    public List<Symptom> getAll() {
        return this.symptomDao.findAllByActiveIsTrue();
    }

    public Symptom findOne(Long id) {
        if (id == null) {
            throw new EntityNotFoundException("Symptom not found!");
        }
        return symptomDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Symptom not found!"));
    }

    public Symptom create(Symptom symptom) {
        return symptomDao.save(symptom);
    }

    public Symptom update(Symptom symptom) {
        findOne(symptom.getId());
        return symptomDao.save(symptom);
    }

    public void delete(Long id) {
        final Symptom symptom = findOne(id);
        symptom.setActive(false);
        symptomDao.save(symptom);
    }
}
