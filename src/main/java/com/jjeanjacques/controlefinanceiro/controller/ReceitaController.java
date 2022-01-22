package com.jjeanjacques.controlefinanceiro.controller;

import com.jjeanjacques.controlefinanceiro.controller.dto.ReceitaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Receita;
import com.jjeanjacques.controlefinanceiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping()
    public ResponseEntity<List<Receita>> getAll() {
        List<Receita> receitas = receitaService.findAll();
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> getById(@PathVariable Long id) {
        Receita receita = receitaService.findById(id);
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
    public ResponseEntity<String> updateReceita(@PathVariable Long id, @RequestBody ReceitaDTO receita) {
        receitaService.updateReceita(id, receita);
        return ResponseEntity.ok().build();
    }
}
