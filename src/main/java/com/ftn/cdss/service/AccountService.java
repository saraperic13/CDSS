package com.ftn.cdss.service;

import com.ftn.cdss.model.auth.Account;
import com.ftn.cdss.repository.AccountDao;
import com.ftn.cdss.repository.RoleDao;
import com.ftn.cdss.security.TokenUtils;
import com.ftn.cdss.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountDao accountDao;

    private final RoleDao roleDao;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final TokenUtils tokenUtils;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountDao accountDao, RoleDao roleDao, AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService, TokenUtils tokenUtils) {
        this.accountDao = accountDao;
        this.roleDao = roleDao;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public String login(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(username);
            return tokenUtils.generateToken(details);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void initAccounts() {

        if (accountDao.findAll().isEmpty()) {
            final Account admin = new Account();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("#adminjecaR1"));
            admin.getRoles().add(roleDao.getOne(1L));
            accountDao.save(admin);

            final Account doctor = new Account();
            doctor.setUsername("drAca");
            doctor.setPassword(passwordEncoder.encode("#acajecaR1"));
            doctor.getRoles().add(roleDao.getOne(2L));
            accountDao.save(doctor);
        }
    }
}
