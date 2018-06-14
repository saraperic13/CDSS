package com.ftn.cdss.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

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
}
