package com.ftn.cdss.model.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("30m")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrineEvent implements Serializable {

    private Long ssn;

    private Double change;

}
