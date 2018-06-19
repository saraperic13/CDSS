package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.Doctor;
import com.ftn.cdss.model.auth.Account;
import com.ftn.cdss.repository.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorDao doctorDao;

    private final AccountService accountService;

    @Autowired
    public DoctorService(DoctorDao doctorDao, AccountService accountService) {
        this.doctorDao = doctorDao;
        this.accountService = accountService;
    }

    public List<Doctor> getAll() {
        return this.doctorDao.findAll();
    }

    public Doctor create(Doctor doctor, String username, String password) {

        Account account = accountService.createDoctor(username, password);
        doctor.setAccount(account);
        return doctorDao.save(doctor);
    }

    public Doctor update(Doctor doctor) {
        final Doctor doctorDb = findOne(doctor.getId());
        doctorDb.setLicenceId(doctor.getLicenceId());
        doctorDb.setSurname(doctor.getSurname());
        doctorDb.setName(doctor.getName());
        return doctorDao.save(doctorDb);
    }

    public void delete(Long id) {
        final Doctor doctor = findOne(id);
        doctorDao.delete(doctor);
    }

    public Doctor findOne(Long id) {
        return doctorDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Doctor not found!"));
    }

    public Doctor findByUsername(String username) {
        return doctorDao.findByAccount_Username(username).orElseThrow(() ->
                new EntityNotFoundException("Doctor not found!"));
    }
}
