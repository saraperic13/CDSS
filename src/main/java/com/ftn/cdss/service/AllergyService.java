package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.Allergy;
import com.ftn.cdss.repository.AllergyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergyService {

    private AllergyDao allergyDao;

    @Autowired
    public AllergyService(AllergyDao allergyDao) {
        this.allergyDao = allergyDao;
    }

    public Allergy create(Allergy allergy) {
        return this.allergyDao.save(allergy);
    }

    public Allergy update(Allergy allergy) {
        findById(allergy.getId());
        return this.allergyDao.save(allergy);
    }

    public void delete(Long id) {
        final Allergy allergy = findById(id);
        this.allergyDao.delete(allergy);
    }

    public Allergy findById(Long id) {
        return allergyDao.findById(id).orElseThrow
                (() -> new EntityNotFoundException("Allergy with id " + id.toString() + " not found!"));
    }
}
