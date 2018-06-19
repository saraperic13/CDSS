package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
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

    public Medicine create(Medicine medicine) {
        return medicineDao.save(medicine);
    }

    public Medicine update(Medicine medicine) {
        findOne(medicine.getId());
        return medicineDao.save(medicine);
    }

    public void delete(Long id) {
        final Medicine medicine = findOne(id);
        medicineDao.delete(medicine);
    }

    public Medicine findOne(Long id) {
        return medicineDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Medicine not found!"));
    }
}
