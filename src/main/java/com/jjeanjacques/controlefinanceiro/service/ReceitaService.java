package com.jjeanjacques.controlefinanceiro.service;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Receita;

public interface ReceitaService {

    Receita createReceita(ReceitaDTO receita);

}
