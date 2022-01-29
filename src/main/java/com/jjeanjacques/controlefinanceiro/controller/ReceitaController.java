package com.jjeanjacques.controlefinanceiro.controller;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;
import com.jjeanjacques.controlefinanceiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping()
    public ResponseEntity<List<ReceitaDTO>> getAll(@RequestParam(required = false) String descricao) {
        List<ReceitaDTO> receitas = receitaService.findAll(descricao);
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> getById(@PathVariable Long id) {
        ReceitaDTO receita = receitaService.findById(id);
        return ResponseEntity.ok(receita);
    }

    @PostMapping()
    public ResponseEntity<String> createReceita(@Valid @RequestBody ReceitaDTO receita) {
        var receitaEntity = receitaService.createReceita(receita);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(receitaEntity.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReceita(@PathVariable Long id, @Valid @RequestBody ReceitaDTO receita) {
        receitaService.updateReceita(id, receita);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReceita(@PathVariable Long id) {
        receitaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<List<ReceitaDTO>> receitasByMonth(@PathVariable int ano, @PathVariable int mes) {
        List<ReceitaDTO> receitas = receitaService.findByMonth(ano, mes);
        return ResponseEntity.ok(receitas);
    }
}
