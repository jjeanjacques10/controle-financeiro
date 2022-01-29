package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Receita;
import com.jjeanjacques.controlefinanceiro.exception.NotFoundRecipe;
import com.jjeanjacques.controlefinanceiro.repository.ReceitaRepository;
import com.jjeanjacques.controlefinanceiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private RecursoServiceImpl recursoService;

    @Override
    public List<ReceitaDTO> findAll(String descricao) {
        var receitas = new ArrayList<Receita>();

        if (descricao != null) {
            receitas.addAll(receitaRepository.findByDescricaoContaining(descricao));
        } else {
            receitas.addAll(receitaRepository.findAll());
        }

        return receitas.stream().map(ReceitaDTO::new).collect(Collectors.toList());
    }

    @Override
    public ReceitaDTO findById(Long idReceita) throws NotFoundRecipe {
        return receitaRepository.findById(idReceita)
                .map(ReceitaDTO::new)
                .orElseThrow(() -> new NotFoundRecipe("Recipe id " + idReceita + " not found"));
    }

    @Override
    public ReceitaDTO createReceita(ReceitaDTO receita) {
        recursoService.isUniqueInTheMonth(receita.getDescricao(), receita.getData().getMonthValue(), receitaRepository);

        var receitaEntity = Receita.builder()
                .descricao(receita.getDescricao())
                .valor(receita.getValor())
                .data(receita.getData())
                .build();

        return new ReceitaDTO(receitaRepository.save(receitaEntity));
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
    public void delete(Long id) {
        var receita = receitaRepository.findById(id)
                .orElseThrow(() -> new NotFoundRecipe("Recipe id " + id + " not found"));
        receitaRepository.delete(receita);
    }

}
