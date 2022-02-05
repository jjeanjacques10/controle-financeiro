package com.jjeanjacques.controlefinanceiro.controller.dto;

import com.jjeanjacques.controlefinanceiro.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DespesaCategoriaResumoDTO {

    private Categoria categoria;
    private BigDecimal total;

}
