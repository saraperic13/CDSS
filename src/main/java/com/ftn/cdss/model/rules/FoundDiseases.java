package com.ftn.cdss.model.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoundDiseases implements Serializable{

    private List<FoundDisease> diseases;
}
