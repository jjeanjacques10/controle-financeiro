package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Receita;
import com.jjeanjacques.controlefinanceiro.repository.ReceitaRepository;
import com.jjeanjacques.controlefinanceiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Override
    public Receita createReceita(ReceitaDTO receita) {
        isUniqueInTheMonth(receita);

        var receitaEntity = Receita.builder()
                .descricao(receita.getDescricao())
                .valor(receita.getValor())
                .data(receita.getData())
                .build();

        return receitaRepository.save(receitaEntity);
    }

    private void isUniqueInTheMonth(ReceitaDTO receita) throws InvalidParameterException {
        var exist = receitaRepository.getByDescricao(receita.getDescricao());
        if (exist != null && LocalDateTime.now().getMonth() == exist.getData().getMonth()) {
            throw new InvalidParameterException("Duplicated item - Descricao already exists in this month");
        }
    }

}