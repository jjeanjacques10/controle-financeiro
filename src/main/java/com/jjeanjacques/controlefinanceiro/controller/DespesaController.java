package com.jjeanjacques.controlefinanceiro.controller;

import com.jjeanjacques.controlefinanceiro.controller.dto.DespesaDTO;
import com.jjeanjacques.controlefinanceiro.entity.Despesa;
import com.jjeanjacques.controlefinanceiro.entity.Despesa;
import com.jjeanjacques.controlefinanceiro.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<Despesa>> getAll() {
        var despesas = despesaService.findAll();
        return ResponseEntity.ok(despesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> getById(@PathVariable Long id) {
        Despesa despesa = despesaService.findById(id);
        return ResponseEntity.ok(despesa);
    }

    @PostMapping()
    public ResponseEntity<String> createDespesa(@Valid @RequestBody DespesaDTO despesa) {
        var despesaEntity = despesaService.create(despesa);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(despesaEntity.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDespesa(@PathVariable Long id, @RequestBody DespesaDTO despesa) {
        despesaService.update(id, despesa);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDespesa(@PathVariable Long id) {
        var despesa = despesaService.findById(id);

        if (despesa == null) {
            return ResponseEntity.notFound().build();
        }

        despesaService.delete(despesa);
        return ResponseEntity.noContent().build();
    }
}
