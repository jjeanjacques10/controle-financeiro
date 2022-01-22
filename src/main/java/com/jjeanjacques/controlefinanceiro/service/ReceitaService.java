package com.jjeanjacques.controlefinanceiro.service;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Receita;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ReceitaService {

    Receita createReceita(ReceitaDTO receita);

    List<Receita> findAll();

    Receita findById(Long idReceita);
}
