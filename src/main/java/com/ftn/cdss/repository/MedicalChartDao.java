package com.ftn.cdss.repository;

import com.ftn.cdss.model.MedicalChart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalChartDao extends JpaRepository<MedicalChart, Long> {
}
