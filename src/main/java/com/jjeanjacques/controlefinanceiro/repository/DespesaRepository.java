package com.jjeanjacques.controlefinanceiro.repository;

import com.jjeanjacques.controlefinanceiro.controller.dto.DespesaCategoriaResumoDTO;
import com.jjeanjacques.controlefinanceiro.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>, RecursoRepository {

    @Query(value = "SELECT d FROM Despesa d WHERE d.descricao = :descricao AND month(d.data) = :month")
    Despesa getByDescricaoAndMonth(String descricao, int month);

    List<Despesa> findByDescricaoContaining(String descricao);

    @Query(value = "SELECT d FROM Despesa d WHERE year(d.data) = :year AND month(d.data) = :month")
    List<Despesa> findByMonth(int year, int month);

    @Query(value = "SELECT SUM(d.valor) FROM Despesa d WHERE year(d.data) = :year AND month(d.data) = :month")
    BigDecimal getTotalDespesas(int year, int month);

    @Query(value = "SELECT new com.jjeanjacques.controlefinanceiro.controller.dto.DespesaCategoriaResumoDTO(d.categoria, sum(d.valor)) FROM Despesa d " +
            "WHERE year(data) = :ano AND month(data) = :mes " +
            "GROUP BY d.categoria")
    List<DespesaCategoriaResumoDTO> getTotalByCategoria(int ano, int mes);
}
