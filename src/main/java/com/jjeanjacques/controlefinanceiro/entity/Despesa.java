package com.jjeanjacques.controlefinanceiro.entity;

import com.jjeanjacques.controlefinanceiro.enums.Categoria;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "despesa")
public class Despesa extends Recurso {

    private Categoria categoria;

    @Builder
    public Despesa(Long id, String descricao, BigDecimal valor, LocalDateTime data, Categoria categoria) {
        super(id, descricao, valor, data);
        this.categoria = categoria;
    }
}
