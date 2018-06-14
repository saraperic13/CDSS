package com.ftn.cdss.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medical_chart")
public class MedicalChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_chart_id")
    private Long id;

    private Integer ssn;

    private String name;

    private String surname;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(name="medical_chart_diagnosis",
            joinColumns = @JoinColumn(name = "medical_chart_id", referencedColumnName = "medical_chart_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id", referencedColumnName = "diagnosis_id"))
    Set<Diagnosis> diagnosis = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medical_chart_allergies",
            joinColumns = @JoinColumn(name = "medical_chart_id", referencedColumnName = "medical_chart_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id", referencedColumnName = "allergy_id"))
    private Set<Allergy> allergies = new HashSet<>();

}
