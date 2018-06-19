package com.ftn.cdss.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diseases")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Long id;

    private String name;

    private DiseaseType diseaseType;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "disease_common_symptoms",
            joinColumns = @JoinColumn(name = "disease_id", referencedColumnName = "disease_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id", referencedColumnName = "symptom_id"))
    private Set<Symptom> commonSymptoms = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "disease_specific_symptoms",
            joinColumns = @JoinColumn(name = "disease_id", referencedColumnName = "disease_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id", referencedColumnName = "symptom_id"))
    private Set<Symptom> specificSymptoms = new HashSet<>();
}
