package com.ftn.cdss.controller;

import com.ftn.cdss.controller.dto.RuleDto;
import com.ftn.cdss.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/rules")
public class RuleController {

    private final RuleService ruleService;

    @Autowired
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PreAuthorize("hasAuthority('crudDiseases')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody RuleDto ruleDto) {
        ruleService.saveRule(ruleDto.getDefinition());
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
