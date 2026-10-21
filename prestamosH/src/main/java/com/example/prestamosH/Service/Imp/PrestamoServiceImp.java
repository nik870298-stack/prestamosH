package com.example.prestamosH.Service.Imp;

import com.example.prestamosH.Service.PrestamoService;
import com.example.prestamosH.DTO.PrestamoRequest;
import com.example.prestamosH.Model.Herramienta;
import com.example.prestamosH.Model.Prestamo;
import com.example.prestamosH.Repository.HerramientaRepository;
import com.example.prestamosH.Repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class PrestamoServiceImp implements PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final HerramientaRepository herramientaRepository;

    public PrestamoServiceImp(PrestamoRepository prestamoRepository, HerramientaRepository herramientaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    @Transactional
    public Prestamo registrarPrestamo(PrestamoRequest request) {
        // Regla 1: Validar que la cantidad solicitada sea mayor a 0
        if (request.getCantidad() == null || request.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad prestada debe ser mayor a cero.");
        }

        // Regla 2: Comprobar que la herramienta exista
        Herramienta herramienta = herramientaRepository.findById(request.getHerramientaId())
                .orElseThrow(() -> new IllegalArgumentException("La herramienta indicada no existe."));

        // Regla 3: Comprobar disponibilidad de unidades
        if (request.getCantidad() > herramienta.getCantidad()) {
            throw new IllegalArgumentException("Unidades insuficientes. Disponibles: " + herramienta.getCantidad());
        }

        // Regla 4: Descontar unidades y actualizar inventario
        herramienta.setCantidad(herramienta.getCantidad() - request.getCantidad());
        herramientaRepository.save(herramienta);

        // Regla 5: Registrar el préstamo en la misma transacción
        Prestamo prestamo = Prestamo.builder()
                .responsable(request.getResponsable())
                .fechaPrestamo(request.getFechaPrestamo())
                .cantidad(request.getCantidad())
                .herramienta(herramienta)
                .build();

        return prestamoRepository.save(prestamo);
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    @Override
    public List<Prestamo> obtenerPorHerramienta(Long herramientaId) {
        return prestamoRepository.findByHerramientaId(herramientaId);
    }
}
