package com.ftn.cdss.repository;

import com.ftn.cdss.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientDao extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByActiveIsTrue();
}
