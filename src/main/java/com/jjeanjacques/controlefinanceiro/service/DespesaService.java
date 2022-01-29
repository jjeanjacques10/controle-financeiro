package com.jjeanjacques.controlefinanceiro.service;

import com.jjeanjacques.controlefinanceiro.controller.dto.DespesaDTO;

import java.util.List;

public interface DespesaService {

    DespesaDTO create(DespesaDTO receita);

    List<DespesaDTO> findAll(String descricao);

    DespesaDTO findById(Long idDespesa);

    void update(Long id, DespesaDTO receita);

    void delete(Long receita);
}
