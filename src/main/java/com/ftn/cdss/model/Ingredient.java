package com.ftn.cdss.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    private String name;

    private boolean active = true;

    @ManyToMany
    @JoinTable(name = "ingredients_medicine",
            joinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "medicine_id"))
    private Set<Medicine> medicines = new HashSet<>();
}
