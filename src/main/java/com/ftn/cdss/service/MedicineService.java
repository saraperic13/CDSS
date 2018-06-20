package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.Ingredient;
import com.ftn.cdss.model.Medicine;
import com.ftn.cdss.repository.IngredientDao;
import com.ftn.cdss.repository.MedicineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicineService {

    private final MedicineDao medicineDao;

    private final IngredientDao ingredientDao;

    @Autowired
    public MedicineService(MedicineDao medicineDao, IngredientDao ingredientDao) {
        this.medicineDao = medicineDao;
        this.ingredientDao = ingredientDao;
    }

    public List<Medicine> getAll() {
        return medicineDao.findAll();
    }

    public Medicine create(Medicine medicine) {

        Set<Ingredient> ingredients = medicine.getIngredients().stream()
                .map(ingredient -> ingredientDao.findById(ingredient.getId()).get()).collect(Collectors.toSet());
        medicine.setIngredients(ingredients);
        return medicineDao.save(medicine);
    }

    public Medicine update(Medicine medicine) {
        findOne(medicine.getId());
        return medicineDao.save(medicine);
    }

    public void delete(Long id) {
        final Medicine medicine = findOne(id);
        medicineDao.delete(medicine);
    }

    public Medicine findOne(Long id) {
        return medicineDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Medicine not found!"));
    }

    public List<Medicine> findByIngredient(Ingredient ingredient) {
        return medicineDao.findByIngredientsIsContaining(ingredient);
    }
}
