package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.fixtures.DespesaFixture;
import com.jjeanjacques.controlefinanceiro.repository.DespesaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DespesaServiceImplTest {

    @Mock
    private DespesaRepository despesaRepository;

    @InjectMocks
    private DespesaServiceImpl despesaService;

    @Test
    void shouldFindAll() {
        var despesas = List.of(DespesaFixture.get("Despesa 1"), DespesaFixture.get("Despesa 2"));
        when(despesaRepository.findAll())
                .thenReturn(despesas);

        var response = despesaService.findAll(null);

        assertEquals(2, response.size());
    }

    @Test
    void shouldFilterByDescription() {
        String descricao = "Despesa 1";

        var despesas = List.of(DespesaFixture.get("Despesa 1"));
        when(despesaRepository.findByDescricaoContaining(anyString()))
                .thenReturn(despesas);

        var response = despesaService.findAll(descricao);

        response.forEach(despesa -> {
            assertTrue(despesa.getDescricao().contains(descricao));
        });
    }
}