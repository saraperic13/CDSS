package com.ftn.cdss.repository;

import com.ftn.cdss.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineDao extends JpaRepository<Medicine, Long> {
}
