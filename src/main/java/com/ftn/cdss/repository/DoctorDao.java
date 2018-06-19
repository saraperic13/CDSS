package com.ftn.cdss.repository;

import com.ftn.cdss.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorDao extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByAccount_Username(String username);
}
