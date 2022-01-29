package com.jjeanjacques.controlefinanceiro.controller.dto;

import com.jjeanjacques.controlefinanceiro.entity.Despesa;
import com.jjeanjacques.controlefinanceiro.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DespesaDTO {

    private Long id;

    @NotNull(message = "Descricao cannot be null")
    private String descricao;
    @NotNull(message = "Valor cannot be null")
    private BigDecimal valor;
    @NotNull(message = "Data cannot be null")
    private LocalDateTime data;
    private Categoria categoria;

    public DespesaDTO(Despesa despesa) {
        this.id = despesa.getId();
        this.descricao = despesa.getDescricao();
        this.valor = despesa.getValor();
        this.data = despesa.getData();
        this.categoria = despesa.getCategoria();
    }

}
