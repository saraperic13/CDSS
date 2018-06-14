package com.ftn.cdss.repository;

import com.ftn.cdss.model.Diagnosis;
import com.ftn.cdss.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisDao extends JpaRepository<Diagnosis, Long> {
}
