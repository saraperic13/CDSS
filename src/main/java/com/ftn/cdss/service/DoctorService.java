package com.ftn.cdss.service;

import com.ftn.cdss.model.Doctor;
import com.ftn.cdss.repository.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorService(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    public List<Doctor> getAll(){
        return this.doctorDao.findAll();
    }
}
