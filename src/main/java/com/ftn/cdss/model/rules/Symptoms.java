package com.ftn.cdss.model.rules;

import com.ftn.cdss.model.Symptom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Symptoms {

    private List<String> symptoms;
}
