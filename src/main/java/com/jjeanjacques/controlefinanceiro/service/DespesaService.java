package com.jjeanjacques.controlefinanceiro.service;

import com.jjeanjacques.controlefinanceiro.controller.dto.DespesaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Despesa;

import java.util.List;

public interface DespesaService {

    Despesa create(DespesaDTO receita);

    List<Despesa> findAll();

    Despesa findById(Long idDespesa);

    void update(Long id, DespesaDTO receita);

    void delete(Despesa receita);
}
