package com.ftn.cdss.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorDto {

    private Long id;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Size(min = 1)
    private String surname;

    private String licenceId;

    @NotNull
    private String username;

    @NotNull
    private String password;
}

