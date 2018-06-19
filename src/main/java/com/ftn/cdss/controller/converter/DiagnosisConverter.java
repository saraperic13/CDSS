package com.ftn.cdss.controller.converter;

import com.ftn.cdss.controller.dto.DiagnosisDto;
import com.ftn.cdss.controller.dto.MedicineDto;
import com.ftn.cdss.model.Diagnosis;
import com.ftn.cdss.model.Disease;
import com.ftn.cdss.model.Medicine;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiagnosisConverter {

    public static DiagnosisDto toDto(Diagnosis diagnosis) {

        final DiagnosisDto diagnosisDto = new DiagnosisDto();
        diagnosisDto.setId(diagnosis.getId());
        diagnosisDto.setDoctorName(diagnosis.getDoctor().getName() + " " + diagnosis.getDoctor().getSurname());
        diagnosisDto.setDate(diagnosis.getDate());
        diagnosisDto.setDiseaseId(diagnosis.getDisease().getId());
        diagnosisDto.setDiseaseName(diagnosis.getDisease().getName());
        diagnosisDto.setSymptomsInput(diagnosis.getSymptomsInput());

        final List<MedicineDto> medicineDtos = diagnosis.getMedicines()
                .stream().map(medicine -> new MedicineDto(
                        medicine.getId(), medicine.getName(), medicine.getType())).collect(Collectors.toList());

        diagnosisDto.setMedicines(medicineDtos);
        return diagnosisDto;
    }

    public static Diagnosis fromDto(DiagnosisDto diagnosisDto) {

        final Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(diagnosisDto.getId());
        diagnosis.setDate(diagnosisDto.getDate());

        final Disease disease = new Disease();
        disease.setId(diagnosisDto.getDiseaseId());
        diagnosis.setDisease(disease);

        diagnosis.setSymptomsInput(diagnosisDto.getSymptomsInput());
        diagnosis.setDate(new Date());

        if (!(diagnosisDto.getMedicines() != null && diagnosisDto.getMedicines().size() > 0)) {
            return diagnosis;
        }
        final Set<Medicine> medicines = diagnosisDto.getMedicines()
                .stream().map(MedicineConverter::fromDto).collect(Collectors.toSet());

        diagnosis.setMedicines(medicines);
        return diagnosis;
    }

}
