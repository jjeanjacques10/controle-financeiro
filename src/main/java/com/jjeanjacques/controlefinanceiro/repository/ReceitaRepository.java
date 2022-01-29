package com.jjeanjacques.controlefinanceiro.repository;

import com.jjeanjacques.controlefinanceiro.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>, RecursoRepository {

    Receita getByDescricao(String descricao);

    List<Receita> findByDescricaoContaining(String descricao);

    @Query(value = "SELECT * FROM receita WHERE descricao = :descricao AND month(data) = :month LIMIT 1", nativeQuery = true)
    Receita getByDescricaoAndMonth(String descricao, int month);
}
