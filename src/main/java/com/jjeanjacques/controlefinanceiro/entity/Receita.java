package com.jjeanjacques.controlefinanceiro.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Receita")
public class Receita extends Recurso {

    @Builder
    public Receita(Long id, String descricao, BigDecimal valor, LocalDateTime data) {
        super(id, descricao, valor, data);
    }

}
