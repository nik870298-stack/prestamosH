package com.example.prestamosH.Service;

import com.example.prestamosH.DTO.PrestamoRequest;
import com.example.prestamosH.Model.Prestamo;

import java.util.List;

public interface PrestamoService {
    Prestamo registrarPrestamo(PrestamoRequest request);
    List<Prestamo> obtenerTodos();
    List<Prestamo> obtenerPorHerramienta(Long herramientaId);
}
