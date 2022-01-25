package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.controller.dto.DespesaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Despesa;
import com.jjeanjacques.controlefinanceiro.exception.NotFoundExpense;
import com.jjeanjacques.controlefinanceiro.repository.DespesaRepository;
import com.jjeanjacques.controlefinanceiro.service.DespesaService;
import com.jjeanjacques.controlefinanceiro.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DespesaServiceImpl implements DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Override
    public List<Despesa> findAll() {
        return despesaRepository.findAll();
    }

    @Override
    public Despesa findById(Long idDespesa) throws NotFoundExpense {
        return despesaRepository.findById(idDespesa)
                .orElseThrow(() -> new NotFoundExpense("Expense id " + idDespesa + " not found"));
    }

    @Override
    public Despesa create(DespesaDTO despesa) {
        isUniqueInTheMonth(despesa);

        var despesaEntity = Despesa.builder()
                .descricao(despesa.getDescricao())
                .valor(despesa.getValor())
                .data(despesa.getData())
                .build();

        return despesaRepository.save(despesaEntity);
    }

    @Override
    public void update(Long id, DespesaDTO despesa) {
        isUniqueInTheMonth(despesa);

        var despesaEntity = despesaRepository.findById(id)
                .orElseThrow(() -> new NotFoundExpense("Expense id " + id + " not found"));

        despesaEntity.setDescricao(despesa.getDescricao() != null ? despesa.getDescricao() : despesaEntity.getDescricao());
        despesaEntity.setData(despesa.getData() != null ? despesa.getData() : despesaEntity.getData());
        despesaEntity.setValor(despesa.getValor() != null ? despesa.getValor() : despesaEntity.getValor());

        despesaRepository.save(despesaEntity);
    }

    @Override
    public void delete(Despesa despesa) {
        despesaRepository.delete(despesa);
    }

    private void isUniqueInTheMonth(DespesaDTO despesa) throws InvalidParameterException {
        var exist = despesaRepository.getByDescricao(despesa.getDescricao());
        if (exist != null && LocalDateTime.now().getMonth() == exist.getData().getMonth()) {
            throw new InvalidParameterException("Duplicated item - Descricao already exists in this month");
        }
    }

}
