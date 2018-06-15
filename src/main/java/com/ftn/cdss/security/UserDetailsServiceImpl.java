package com.ftn.cdss.security;

import com.ftn.cdss.model.auth.Account;
import com.ftn.cdss.model.auth.Role;
import com.ftn.cdss.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountDao.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : account.getRoles()) {
            grantedAuthorities.addAll(role.getPermissions().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                    .collect(Collectors.toList()));
        }

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                grantedAuthorities);

    }

}

