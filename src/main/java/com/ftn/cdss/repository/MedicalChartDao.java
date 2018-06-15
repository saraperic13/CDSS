package com.ftn.cdss.repository;

import com.ftn.cdss.model.MedicalChart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalChartDao extends JpaRepository<MedicalChart, Long> {

    @Override
    Optional<MedicalChart> findById(Long id);
}
