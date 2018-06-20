package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.Ingredient;
import com.ftn.cdss.model.Medicine;
import com.ftn.cdss.repository.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientDao ingredientDao;

    private final MedicineService medicineService;

    @Autowired
    public IngredientService(IngredientDao ingredientDao, MedicineService medicineService) {
        this.ingredientDao = ingredientDao;
        this.medicineService = medicineService;
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientDao.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        findOne(ingredient.getId());
        return ingredientDao.save(ingredient);
    }

    public void delete(Long id) {
        final Ingredient ingredient = findOne(id);
        final List<Medicine> medicines = medicineService.findByIngredient(ingredient);
        medicines.forEach(medicine -> medicine.getIngredients().remove(ingredient));
        medicines.forEach(medicineService::update);

        ingredient.setActive(false);
        ingredientDao.save(ingredient);
    }

    public List<Ingredient> getAll() {
        return ingredientDao.findAllByActiveIsTrue();
    }

    public Ingredient findOne(Long id) {
        return ingredientDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Ingredient not found!"));
    }
}
