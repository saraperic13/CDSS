package com.ftn.cdss.repository;

import com.ftn.cdss.model.Ingredient;
import com.ftn.cdss.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineDao extends JpaRepository<Medicine, Long> {

    List<Medicine> findByIngredientsIsContaining(Ingredient ingredient);

    List<Medicine> findAllByActiveIsTrue();
}
