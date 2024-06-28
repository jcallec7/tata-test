package com.tata.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimientos_seq")
    @SequenceGenerator(name = "movimientos_seq", sequenceName = "movimientos_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private LocalDateTime date;

    @Column(name = "tipo_movimiento")
    private String movementType;

    @Column(name = "valor")
    private BigDecimal value;

    @Column(name = "saldo")
    private BigDecimal balance;
}
