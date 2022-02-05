package com.jjeanjacques.controlefinanceiro.fixtures;

import com.jjeanjacques.controlefinanceiro.entity.Despesa;
import com.jjeanjacques.controlefinanceiro.enums.Categoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DespesaFixture {

    public static Despesa get() {
        return Despesa.builder()
                .categoria(Categoria.OUTRAS)
                .data(LocalDateTime.now())
                .valor(BigDecimal.TEN)
                .descricao("Despesa 1")
                .build();
    }

    public static Despesa get(String descricao) {
        return Despesa.builder()
                .categoria(Categoria.OUTRAS)
                .data(LocalDateTime.now())
                .valor(BigDecimal.TEN)
                .descricao(descricao)
                .build();
    }

}
