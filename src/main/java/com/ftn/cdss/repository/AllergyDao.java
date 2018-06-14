package com.ftn.cdss.repository;

import com.ftn.cdss.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AllergyDao extends JpaRepository<Allergy, Long> {

    @Override
    Optional<Allergy> findById(Long aLong);
}
