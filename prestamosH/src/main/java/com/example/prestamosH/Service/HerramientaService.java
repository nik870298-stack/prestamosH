package com.example.prestamosH.Service;

import com.example.prestamosH.Model.Herramienta;

import java.util.List;

public interface HerramientaService {
    Herramienta guardarHerramienta(Herramienta herramienta);
    List<Herramienta> obtenerTodas();
    Herramienta obtenerPorId(Long id);
}
