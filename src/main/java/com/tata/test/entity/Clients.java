package com.tata.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Clients")
public class Clients extends Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_seq")
    @SequenceGenerator(name = "clientes_seq", sequenceName = "clientes_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "contrasenia")
    private String password;

    @Column(name = "estado", nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;

}
