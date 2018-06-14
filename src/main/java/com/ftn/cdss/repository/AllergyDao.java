package com.ftn.cdss.repository;

import com.ftn.cdss.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyDao extends JpaRepository<Allergy, Long> {
}
