package com.jjeanjacques.controlefinanceiro.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ResumoDTO {

    @JsonProperty("total_receitas")
    private BigDecimal totalReceitas;

    @JsonProperty("total_despesas")
    private BigDecimal totalDespesas;

    @JsonProperty("saldo_final")
    private BigDecimal saldoFinal;

    @JsonProperty("categorias")
    private List<CategoriaResumoDTO> categoriaResumo;

}
