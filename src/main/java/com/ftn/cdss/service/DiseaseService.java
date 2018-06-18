package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.repository.DiseaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {

    private final DiseaseDao diseaseDao;

    @Autowired
    public DiseaseService(DiseaseDao diseaseDao) {
        this.diseaseDao = diseaseDao;
    }

    public List<Disease> getAll() {
        return this.diseaseDao.findAll();
    }

    public Disease findOne(Long id) {
        if(id == null){
            throw new EntityNotFoundException("Medical chart not found!");
        }
        return diseaseDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Medical chart not found!"));
    }
}
