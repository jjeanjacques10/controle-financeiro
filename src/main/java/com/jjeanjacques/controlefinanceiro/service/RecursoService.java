package com.jjeanjacques.controlefinanceiro.service;

import com.jjeanjacques.controlefinanceiro.repository.RecursoRepository;

import java.security.InvalidParameterException;

public interface RecursoService {

    void isUniqueInTheMonth(String descricao, int month, RecursoRepository repository) throws InvalidParameterException;

}
