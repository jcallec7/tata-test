package com.tata.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuentas_seq")
    @SequenceGenerator(name = "cuentas_seq", sequenceName = "cuentas_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_cuenta")
    private String accountNumber;

    @Column(name = "tipo_cuenta")
    private String accountType;

    @Column(name = "saldo_inicial")
    private Double initialBalance;

    @Column(name = "estado", nullable = false, columnDefinition = "boolean default true")
    private String status;
}