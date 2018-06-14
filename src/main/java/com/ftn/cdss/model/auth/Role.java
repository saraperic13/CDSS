package com.ftn.cdss.model.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    private RoleType roleType;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();
}
