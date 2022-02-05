package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.fixtures.ReceitaFixture;
import com.jjeanjacques.controlefinanceiro.repository.ReceitaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReceitaServiceImplTest {
    @Mock
    private ReceitaRepository despesaRepository;

    @InjectMocks
    private ReceitaServiceImpl despesaService;

    @Test
    void shouldFindAll() {
        var despesas = List.of(ReceitaFixture.get("Receita 1"), ReceitaFixture.get("Receita 2"));
        when(despesaRepository.findAll())
                .thenReturn(despesas);

        var response = despesaService.findAll(null);

        assertEquals(2, response.size());
    }

    @Test
    void shouldFilterByDescription() {
        String descricao = "Receita 1";

        var despesas = List.of(ReceitaFixture.get("Receita 1"));
        when(despesaRepository.findByDescricaoContaining(anyString()))
                .thenReturn(despesas);

        var response = despesaService.findAll(descricao);

        response.forEach(despesa -> {
            assertTrue(despesa.getDescricao().contains(descricao));
        });
    }
}