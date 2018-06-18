package com.ftn.cdss.model.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoundDisease {

    private Long diseaseId;
    private String diseaseName;
    private Integer numOfSymptoms;
    private Integer numOfFulfilled;

}
