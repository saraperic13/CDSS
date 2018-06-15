package com.ftn.cdss.service;

import com.ftn.cdss.model.auth.Account;
import com.ftn.cdss.repository.AccountDao;
import com.ftn.cdss.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountDao accountDao;

    private final RoleDao roleDao;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountDao accountDao, RoleDao roleDao) {
        this.accountDao = accountDao;
        this.roleDao = roleDao;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public void initAccounts() {

        if (accountDao.findAll().isEmpty()) {
            final Account admin = new Account();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("#adminjecaR1"));
            admin.getRoles().add(roleDao.getOne(1L));
            accountDao.save(admin);
        }
    }
}
