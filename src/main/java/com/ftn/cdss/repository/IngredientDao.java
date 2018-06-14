package com.ftn.cdss.repository;

import com.ftn.cdss.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientDao extends JpaRepository<Ingredient, Long> {
}
