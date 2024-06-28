package com.tata.test.transactions.entity;

import com.tata.test.clients.entity.Clients;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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
    private BigDecimal initialBalance;

    @Column(name = "estado", nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transactions> transactions;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Clients client;

}
