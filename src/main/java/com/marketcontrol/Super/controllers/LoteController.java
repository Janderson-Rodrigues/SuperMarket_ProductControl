package com.marketcontrol.Super.controllers;

import com.marketcontrol.Super.dtos.LoteRecordDto;
import com.marketcontrol.Super.model.LoteModel;
import com.marketcontrol.Super.services.LoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class LoteController {

    final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @PostMapping("/lotes")
    public ResponseEntity<Object> newLote(@RequestBody @Valid LoteRecordDto dto) {
        try {
            var loteSalvo = loteService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(loteSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/lotes")
    public ResponseEntity<List<LoteModel>> getAllLotes() {
        return ResponseEntity.status(HttpStatus.OK).body(loteService.findAll());
    }

    @GetMapping("/lotes/alerta")
    public ResponseEntity<List<LoteModel>> getAlertas(@RequestParam(defaultValue = "7") int dias) {
        // Chama o método inteligente do Service
        var alertas = loteService.buscarLotesEmAlerta(dias);

        if (alertas.isEmpty()) {
            // Retorna 204 (No Content) se não tiver nada vencendo (o que é bom!)
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/lotes/vencidos")
    public ResponseEntity<List<LoteModel>> getVencidos() {
        return ResponseEntity.ok(loteService.buscarLotesVencidos());
    }

    @GetMapping("/lotes/produto/{id}")
    public ResponseEntity<List<LoteModel>> getPorProduto(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(loteService.buscarPorProduto(id));
    }
}