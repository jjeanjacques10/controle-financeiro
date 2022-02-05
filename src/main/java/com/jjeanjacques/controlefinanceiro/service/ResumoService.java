package com.jjeanjacques.controlefinanceiro.service;

import com.jjeanjacques.controlefinanceiro.controller.dto.ResumoDTO;

public interface ResumoService {

    ResumoDTO getResumo(int ano, int mes);

}
