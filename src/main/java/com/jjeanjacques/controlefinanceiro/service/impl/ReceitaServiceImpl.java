package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Receita;
import com.jjeanjacques.controlefinanceiro.exception.NotFoundRecipe;
import com.jjeanjacques.controlefinanceiro.repository.ReceitaRepository;
import com.jjeanjacques.controlefinanceiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private RecursoServiceImpl recursoService;

    @Override
    public List<Receita> findAll(String descricao) {
        if (descricao != null) {
            return receitaRepository.findByDescricaoContaining(descricao);
        }
        return receitaRepository.findAll();
    }

    @Override
    public Receita findById(Long idReceita) throws NotFoundRecipe {
        return receitaRepository.findById(idReceita)
                .orElseThrow(() -> new NotFoundRecipe("Recipe id " + idReceita + " not found"));
    }

    @Override
    public Receita createReceita(ReceitaDTO receita) {
        recursoService.isUniqueInTheMonth(receita.getDescricao(), receita.getData().getMonthValue(), receitaRepository);

        var receitaEntity = Receita.builder()
                .descricao(receita.getDescricao())
                .valor(receita.getValor())
                .data(receita.getData())
                .build();
        return receitaRepository.save(receitaEntity);
    }

    @Override
    public void updateReceita(Long id, ReceitaDTO receita) {
        recursoService.isUniqueInTheMonth(receita.getDescricao(), receita.getData().getMonthValue(), receitaRepository);

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

}
