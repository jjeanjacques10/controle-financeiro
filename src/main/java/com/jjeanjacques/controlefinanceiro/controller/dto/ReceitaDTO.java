package com.jjeanjacques.controlefinanceiro.controller.dto;

import com.jjeanjacques.controlefinanceiro.entity.Receita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDTO {

    private Long id;

    @NotNull(message = "Descricao cannot be null")
    private String descricao;
    @NotNull(message = "Valor cannot be null")
    private BigDecimal valor;
    @NotNull(message = "Data cannot be null")
    private LocalDateTime data;

    public ReceitaDTO(Receita receita) {
        this.id = receita.getId();
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor();
        this.data = receita.getData();
    }

}
