package com.jjeanjacques.controlefinanceiro.service;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Receita;

import java.util.List;

public interface ReceitaService {

    Receita createReceita(ReceitaDTO receita);

    List<Receita> findAll(String descricao);

    Receita findById(Long idReceita);

    void updateReceita(Long id, ReceitaDTO receita);

    void delete(Receita receita);
}
