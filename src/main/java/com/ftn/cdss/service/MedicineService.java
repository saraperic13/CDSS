package com.ftn.cdss.service;

import com.ftn.cdss.model.Medicine;
import com.ftn.cdss.repository.MedicineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineDao medicineDao;

    @Autowired
    public MedicineService(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    public List<Medicine> getAll() {
        return medicineDao.findAll();
    }
}
