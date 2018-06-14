package com.ftn.cdss.repository;

import com.ftn.cdss.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<Doctor, Long> {
}
