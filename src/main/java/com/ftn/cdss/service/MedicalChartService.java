package com.ftn.cdss.service;

import com.ftn.cdss.exception.EntityNotFoundException;
import com.ftn.cdss.model.Allergy;
import com.ftn.cdss.model.Ingredient;
import com.ftn.cdss.model.MedicalChart;
import com.ftn.cdss.model.Medicine;
import com.ftn.cdss.repository.AllergyDao;
import com.ftn.cdss.repository.IngredientDao;
import com.ftn.cdss.repository.MedicalChartDao;
import com.ftn.cdss.repository.MedicineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicalChartService {

    private final MedicalChartDao medicalChartDao;

    private final IngredientDao ingredientDao;

    private final MedicineDao medicineDao;

    private final AllergyDao allergyDao;

    @Autowired
    public MedicalChartService(MedicalChartDao medicalChartDao,
                               IngredientDao ingredientDao, MedicineDao medicineDao, AllergyDao allergyDao) {
        this.medicalChartDao = medicalChartDao;
        this.ingredientDao = ingredientDao;
        this.medicineDao = medicineDao;
        this.allergyDao = allergyDao;
    }

    public MedicalChart create(MedicalChart medicalChart) {

        Set<Allergy> allergies = medicalChart.getAllergies();
        for(Allergy allergy: allergies){
            if(allergy.getMedicine().getId()!= null){
                final Medicine medicine = medicineDao.findById(allergy.getMedicine().getId())
                        .orElseThrow(()-> new EntityNotFoundException("Medicine not found"));

                allergy.setMedicine(medicine);

            }else if(allergy.getIngredient().getId()!= null){
                final Ingredient ingredient = ingredientDao.findById(allergy.getMedicine().getId())
                        .orElseThrow(()-> new EntityNotFoundException("Ingredient not found"));

                allergy.setIngredient(ingredient);
            }
        }

        allergyDao.saveAll(allergies);

        return medicalChartDao.save(medicalChart);
    }

    public MedicalChart update(MedicalChart medicalChart) {
        findOne(medicalChart.getId());
        return medicalChartDao.save(medicalChart);
    }

    public void delete(Long id) {
        final MedicalChart medicalChart = findOne(id);
        medicalChartDao.delete(medicalChart);
    }

    public List<MedicalChart> getAll() {
        return medicalChartDao.findAll();
    }

    public MedicalChart findOne(Long id) {
        return medicalChartDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Medical chart not found!"));
    }
}
