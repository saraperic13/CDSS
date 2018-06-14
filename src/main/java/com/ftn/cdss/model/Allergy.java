package com.ftn.cdss.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="allergies")
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "allergic_to_medicines",
            joinColumns = @JoinColumn(name = "allergy_id", referencedColumnName = "allergy_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "medicine_id"))
    private Set<Medicine> medicines = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "allergic_to_ingredients",
            joinColumns = @JoinColumn(name = "allergy_id", referencedColumnName = "allergy_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();
}
