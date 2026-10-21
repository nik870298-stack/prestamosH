package com.example.prestamosH.Controller;

import com.example.prestamosH.Model.Herramienta;
import com.example.prestamosH.Service.HerramientaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
@RequiredArgsConstructor
public class HerramientaController {
    private final HerramientaService herramientaService;

    @PostMapping
    public ResponseEntity<?> registrarHerramienta(@Valid @RequestBody Herramienta herramienta) {
        try {
            Herramienta creada = herramientaService.guardarHerramienta(herramienta);
            return new ResponseEntity<>(creada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Herramienta>> consultarHerramientas() {
        return ResponseEntity.ok(herramientaService.obtenerTodas());
    }
}
