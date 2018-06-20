package com.ftn.cdss.repository;

import com.ftn.cdss.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisDao extends JpaRepository<Diagnosis, Long> {

    List<Diagnosis> findAllByActiveIsTrue();

    List<Diagnosis> findAllByActiveIsTrueAndMedicalChartId(Long chartId);
}
