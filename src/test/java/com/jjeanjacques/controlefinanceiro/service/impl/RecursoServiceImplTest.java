package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.fixtures.DespesaFixture;
import com.jjeanjacques.controlefinanceiro.repository.DespesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RecursoServiceImplTest {

    @Autowired
    private RecursoServiceImpl recursoService;

    private DespesaRepository despesaRepository;

    @BeforeEach
    void setup() {
        recursoService = new RecursoServiceImpl();
        despesaRepository = mock(DespesaRepository.class);
    }

    @Test
    void shouldBeUniqueInTheMonth() {
        var descricao = "Descricao que existe";
        var month = 1;
        when(despesaRepository.getByDescricaoAndMonth(descricao, month))
                .thenReturn(null);
        recursoService.isUniqueInTheMonth(descricao, month, despesaRepository);
        verify(despesaRepository).getByDescricaoAndMonth(descricao, month);
    }

    @Test
    void shouldNotBeUniqueInTheMonth() {
        var descricao = "Descricao que nao existe";
        when(despesaRepository.getByDescricaoAndMonth(descricao, 1))
                .thenReturn(DespesaFixture.get(descricao));

        assertThrows(InvalidParameterException.class, () -> {
            recursoService.isUniqueInTheMonth(descricao, 1, despesaRepository);
        });
    }
}