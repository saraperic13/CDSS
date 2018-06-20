package com.ftn.cdss.controller;

import com.ftn.cdss.model.Ingredient;
import com.ftn.cdss.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PreAuthorize("hasAuthority('crudDrug')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody @Valid Ingredient ingredient) {
        ingredient = this.ingredientService.create(ingredient);
        return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('crudDrug')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Ingredient ingredient) {

        ingredient = this.ingredientService.update(ingredient);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('readDrug')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        return new ResponseEntity<>(ingredientService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('readDrug')")
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOne(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientService.findOne(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudDrug')")
    @DeleteMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        ingredientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
