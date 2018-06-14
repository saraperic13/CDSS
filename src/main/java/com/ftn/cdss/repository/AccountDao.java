package com.ftn.cdss.repository;

import com.ftn.cdss.model.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
