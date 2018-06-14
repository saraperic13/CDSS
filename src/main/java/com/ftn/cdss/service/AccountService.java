package com.ftn.cdss.service;

import com.ftn.cdss.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
