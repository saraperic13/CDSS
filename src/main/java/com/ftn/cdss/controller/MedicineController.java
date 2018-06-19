package com.ftn.cdss.controller;

import com.ftn.cdss.controller.converter.MedicineConverter;
import com.ftn.cdss.controller.dto.MedicineDto;
import com.ftn.cdss.model.Medicine;
import com.ftn.cdss.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PreAuthorize("hasAuthority('readDrug')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {

        final List<MedicineDto> medicineDtos =
                medicineService.getAll().stream().map(MedicineConverter::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(medicineDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudDrug')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody @Valid MedicineDto medicineDto) {

        Medicine medicine = MedicineConverter.fromDto(medicineDto);
        medicine = this.medicineService.create(medicine);
        return new ResponseEntity<>(MedicineConverter.toDto(medicine), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('crudDrug')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid MedicineDto medicineDto) {

        Medicine medicine = MedicineConverter.fromDto(medicineDto);
        medicine = this.medicineService.update(medicine);
        return new ResponseEntity<>(MedicineConverter.toDto(medicine), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('crudDrug')")
    @DeleteMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        medicineService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
