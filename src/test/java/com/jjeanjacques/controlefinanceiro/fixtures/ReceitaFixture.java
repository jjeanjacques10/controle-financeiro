package com.jjeanjacques.controlefinanceiro.fixtures;

import com.jjeanjacques.controlefinanceiro.entity.Receita;
import com.jjeanjacques.controlefinanceiro.enums.Categoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceitaFixture {

    public static Receita get() {
        return Receita.builder()
                .data(LocalDateTime.now())
                .valor(BigDecimal.TEN)
                .descricao("Receita 1")
                .build();
    }

    public static Receita get(String descricao) {
        return Receita.builder()
                .data(LocalDateTime.now())
                .valor(BigDecimal.TEN)
                .descricao(descricao)
                .build();
    }

}
