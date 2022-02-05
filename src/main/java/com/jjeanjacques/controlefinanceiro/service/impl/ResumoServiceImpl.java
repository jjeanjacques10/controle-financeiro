package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.controller.dto.DespesaCategoriaResumoDTO;
import com.jjeanjacques.controlefinanceiro.controller.dto.ResumoDTO;
import com.jjeanjacques.controlefinanceiro.repository.DespesaRepository;
import com.jjeanjacques.controlefinanceiro.repository.ReceitaRepository;
import com.jjeanjacques.controlefinanceiro.service.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ResumoServiceImpl implements ResumoService {

    @Autowired
    ReceitaRepository receitaRepository;

    @Autowired
    DespesaRepository despesaRepository;

    @Override
    public ResumoDTO getResumo(int ano, int mes) {
        BigDecimal totalReceitas = receitaRepository.getTotalReceitas(ano, mes);
        BigDecimal totalDespesas = despesaRepository.getTotalDespesas(ano, mes);
        BigDecimal saldoFinal = totalReceitas.subtract(totalDespesas);
        List<DespesaCategoriaResumoDTO> totalByCategoria = despesaRepository.getTotalByCategoria(ano, mes);

        return ResumoDTO.builder()
                .totalReceitas(totalReceitas)
                .totalDespesas(totalDespesas)
                .saldoFinal(saldoFinal)
                .categoriaResumo(totalByCategoria)
                .build();
    }

}
