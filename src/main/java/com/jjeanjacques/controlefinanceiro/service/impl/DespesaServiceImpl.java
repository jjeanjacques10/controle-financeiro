package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.controller.dto.DespesaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Despesa;
import com.jjeanjacques.controlefinanceiro.enums.Categoria;
import com.jjeanjacques.controlefinanceiro.exception.NotFoundExpense;
import com.jjeanjacques.controlefinanceiro.repository.DespesaRepository;
import com.jjeanjacques.controlefinanceiro.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespesaServiceImpl implements DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private RecursoServiceImpl recursoService;

    @Override
    public List<DespesaDTO> findAll(String descricao) {
        var despesas = new ArrayList<Despesa>();

        if (descricao != null) {
            despesas.addAll(despesaRepository.findByDescricaoContaining(descricao));
        } else {
            despesas.addAll(despesaRepository.findAll());
        }

        return despesas.stream().map(DespesaDTO::new).collect(Collectors.toList());
    }

    @Override
    public DespesaDTO findById(Long idDespesa) throws NotFoundExpense {
        return despesaRepository.findById(idDespesa)
                .map(DespesaDTO::new)
                .orElseThrow(() -> new NotFoundExpense("Despesa id " + idDespesa + " not found"));
    }

    @Override
    public DespesaDTO create(DespesaDTO despesa) {
        recursoService.isUniqueInTheMonth(despesa.getDescricao(), despesa.getData().getMonthValue(), despesaRepository);

        var despesaEntity = Despesa.builder()
                .descricao(despesa.getDescricao())
                .valor(despesa.getValor())
                .data(despesa.getData())
                .categoria(despesa.getCategoria() == null ? Categoria.OUTRAS : despesa.getCategoria())
                .build();

        despesaEntity = despesaRepository.save(despesaEntity);
        return new DespesaDTO(despesaEntity);
    }

    @Override
    public void update(Long id, DespesaDTO despesa) {
        recursoService.isUniqueInTheMonth(despesa.getDescricao(), despesa.getData().getMonthValue(), despesaRepository);

        var despesaEntity = despesaRepository.findById(id)
                .orElseThrow(() -> new NotFoundExpense("Expense id " + id + " not found"));

        despesaEntity.setDescricao(despesa.getDescricao() != null ? despesa.getDescricao() : despesaEntity.getDescricao());
        despesaEntity.setData(despesa.getData() != null ? despesa.getData() : despesaEntity.getData());
        despesaEntity.setValor(despesa.getValor() != null ? despesa.getValor() : despesaEntity.getValor());

        despesaRepository.save(despesaEntity);
    }

    @Override
    public void delete(Long id) {
        var despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new NotFoundExpense("Despesa id " + id + " not found"));
        despesaRepository.delete(despesa);
    }

    @Override
    public List<DespesaDTO> findByMonth(int ano, int mes) {
        List<Despesa> despesas = despesaRepository.findByMonth(ano, mes);
        return despesas.stream().map(DespesaDTO::new).collect(Collectors.toList());
    }

}
