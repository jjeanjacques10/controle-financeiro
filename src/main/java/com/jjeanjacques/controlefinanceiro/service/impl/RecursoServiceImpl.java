package com.jjeanjacques.controlefinanceiro.service.impl;

import com.jjeanjacques.controlefinanceiro.repository.RecursoRepository;
import com.jjeanjacques.controlefinanceiro.service.RecursoService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class RecursoServiceImpl implements RecursoService {

    @Override
    public void isUniqueInTheMonth(String descricao, int month, RecursoRepository repository) throws InvalidParameterException {
        var recursoFound = repository.getByDescricaoAndMonth(descricao, month);
        if (recursoFound != null) {
            throw new InvalidParameterException("Duplicated item - Descricao already exists in this month");
        }
    }

}
