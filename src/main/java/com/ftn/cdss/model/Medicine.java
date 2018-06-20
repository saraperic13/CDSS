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
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Long id;

    private String name;

    private MedicineType type;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "medicine_ingredients",
            joinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();
}
