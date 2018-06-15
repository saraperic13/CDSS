package com.ftn.cdss.controller;

import com.ftn.cdss.controller.dto.LoginDto;
import com.ftn.cdss.controller.dto.TokenDto;
import com.ftn.cdss.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {

        final String token = accountService.login(loginDto.getUsername(), loginDto.getPassword());
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final TokenDto tokenDto = new TokenDto(token);
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }
}
