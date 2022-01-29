package com.jjeanjacques.controlefinanceiro.repository;

import com.jjeanjacques.controlefinanceiro.entity.Recurso;

public interface RecursoRepository {

    Recurso getByDescricaoAndMonth(String descricao, int month);

}
