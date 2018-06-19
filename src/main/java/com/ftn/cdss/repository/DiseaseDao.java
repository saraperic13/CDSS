package com.ftn.cdss.repository;

import com.ftn.cdss.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiseaseDao extends JpaRepository<Disease, Long> {

    Optional<Disease> findByName(String name);
}
