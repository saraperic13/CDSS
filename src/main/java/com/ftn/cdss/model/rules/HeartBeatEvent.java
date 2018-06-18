package com.ftn.cdss.model.rules;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("10m")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HeartBeatEvent implements Serializable {

    private Integer ssn;
}

