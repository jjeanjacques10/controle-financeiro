package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Receita;
import com.jjeanjacques.controlefinanceiro.exception.NotFoundRecipe;
import com.jjeanjacques.controlefinanceiro.repository.ReceitaRepository;
import com.jjeanjacques.controlefinanceiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Override
    public List<Receita> findAll() {
        return receitaRepository.findAll();
    }

    @Override
    public Receita findById(Long idReceita) throws NotFoundRecipe {
        return receitaRepository.findById(idReceita)
                .orElseThrow(() -> new NotFoundRecipe("Recipe id " + idReceita + " not found"));
    }

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

    @Override
    public void updateReceita(Long id, ReceitaDTO receita) {
        isUniqueInTheMonth(receita);

        var receitaEntity = receitaRepository.findById(id)
                .orElseThrow(() -> new NotFoundRecipe("Recipe id " + id + " not found"));

        receitaEntity.setDescricao(receita.getDescricao() != null ? receita.getDescricao() : receitaEntity.getDescricao());
        receitaEntity.setData(receita.getData() != null ? receita.getData() : receitaEntity.getData());
        receitaEntity.setValor(receita.getValor() != null ? receita.getValor() : receitaEntity.getValor());

        receitaRepository.save(receitaEntity);
    }

    @Override
    public void delete(Receita receita) {
        receitaRepository.delete(receita);
    }

    private void isUniqueInTheMonth(ReceitaDTO receita) throws InvalidParameterException {
        var exist = receitaRepository.getByDescricao(receita.getDescricao());
        if (exist != null && LocalDateTime.now().getMonth() == exist.getData().getMonth()) {
            throw new InvalidParameterException("Duplicated item - Descricao already exists in this month");
        }
    }

}
