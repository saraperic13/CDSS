package com.ftn.cdss.repository;

import com.ftn.cdss.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseDao extends JpaRepository<Disease, Long> {
}
