package com.tata.test.clients.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class SaveClientDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "El genero es obligatorio")
    private String gender;
    @NotNull(message = "La edad es obligatoria")
    @Positive(message = "La edad debe ser mayor cero")
    @Range(min = 18, max = 100, message = "La edad debe estar entre 18 y 99 años")
    private Integer age;
    @NotBlank(message = "La identificación es obligatoria")
    private String identification;
    @NotBlank(message = "La dirección es obligatoria")
    private String address;
    @NotBlank(message = "El número de teléfono es obligatorio")
    private String phoneNumber;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

}
