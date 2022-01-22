package com.jjeanjacques.controlefinanceiro.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReceitaDTO {

    private Long id;

    @NotNull(message = "Descricao cannot be null")
    private String descricao;
    @NotNull(message = "Valor cannot be null")
    private BigDecimal valor;
    @NotNull(message = "Data cannot be null")
    private LocalDateTime data;

}
