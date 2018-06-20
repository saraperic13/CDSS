package com.ftn.cdss.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "diagnosis")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id")
    private Long id;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "medical_chart_id", name = "medical_chart_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MedicalChart medicalChart;

    @OneToOne
    private Doctor doctor;

    @OneToOne
    private Disease disease;

    private String symptomsInput;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "diagnosis_medicines",
            joinColumns = @JoinColumn(name = "diagnosis_id", referencedColumnName = "diagnosis_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "medicine_id"))
    Set<Medicine> medicines = new HashSet<>();

    private boolean active = true;

    public boolean containsType(MedicineType medicineType){
        for(Medicine m : medicines){
            if(m.getType() == medicineType){
                return true;
            }
        }
        return false;
    }
}
