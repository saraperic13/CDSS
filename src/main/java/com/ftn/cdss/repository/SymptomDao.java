package com.ftn.cdss.repository;

import com.ftn.cdss.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymptomDao extends JpaRepository<Symptom, Long> {

    List<Symptom> findAllByActiveIsTrue();
}
