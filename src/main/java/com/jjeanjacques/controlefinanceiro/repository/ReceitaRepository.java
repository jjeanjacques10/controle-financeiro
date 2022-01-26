package com.jjeanjacques.controlefinanceiro.repository;

import com.jjeanjacques.controlefinanceiro.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Receita getByDescricao(String descricao);

    List<Receita> findByDescricaoContaining(String descricao);
}
