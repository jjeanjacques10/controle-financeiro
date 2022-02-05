package com.jjeanjacques.controlefinanceiro.entity;

import com.jjeanjacques.controlefinanceiro.enums.Categoria;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Despesa")
public class Despesa extends Recurso {

    //@Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Builder
    public Despesa(Long id, String descricao, BigDecimal valor, LocalDateTime data, Categoria categoria) {
        super(id, descricao, valor, data);
        this.categoria = categoria;
    }
}
