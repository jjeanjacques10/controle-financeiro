package com.jjeanjacques.controlefinanceiro.service;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;

import java.util.List;

public interface ReceitaService {

    ReceitaDTO createReceita(ReceitaDTO receita);

    List<ReceitaDTO> findAll(String descricao);

    ReceitaDTO findById(Long idReceita);

    void updateReceita(Long id, ReceitaDTO receita);

    void delete(Long id);

    List<ReceitaDTO> findByMonth(int ano, int mes);
}
