package com.jjeanjacques.controlefinanceiro.repository;

import com.jjeanjacques.controlefinanceiro.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>, RecursoRepository {

    List<Receita> findByDescricaoContaining(String descricao);

    @Query(value = "SELECT * FROM Receita WHERE descricao = :descricao AND month(data) = :month LIMIT 1", nativeQuery = true)
    Receita getByDescricaoAndMonth(String descricao, int month);

    @Query(value = "SELECT * FROM Receita WHERE year(data) = :year AND month(data) = :month", nativeQuery = true)
    List<Receita> findByMonth(int year, int month);

    @Query(value = "SELECT SUM(r.valor) FROM Receita r WHERE year(r.data) = :year AND month(r.data) = :month")
    BigDecimal getTotalReceitas(int year, int month);
}
