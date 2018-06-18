package com.ftn.cdss.model.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("10m")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcceleratedHeartbeatEvent {

    private Integer ssn;
}
