package com.ftn.cdss.repository;

import com.ftn.cdss.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
