package com.jjeanjacques.controlefinanceiro.repository;

import com.jjeanjacques.controlefinanceiro.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>, RecursoRepository {

    @Query(value = "SELECT * FROM despesa WHERE descricao = :descricao AND month(data) = :month LIMIT 1", nativeQuery = true)
    Despesa getByDescricaoAndMonth(String descricao, int month);

}
