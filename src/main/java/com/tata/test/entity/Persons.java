package com.tata.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Persons")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personas_seq")
    @SequenceGenerator(name = "personas_seq", sequenceName = "personas_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "genero")
    private String gender;

    @Column(name = "edad")
    private Integer age;

    @Column(name = "identificacion")
    private String identification;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefono")
    private String phoneNumber;

}
