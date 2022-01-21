package com.jjeanjacques.controlefinanceiro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DESPESAS")
public class Despesas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;

}
