package com.example.prestamosH.Service.Imp;

import com.example.prestamosH.Model.Herramienta;
import com.example.prestamosH.Repository.HerramientaRepository;
import com.example.prestamosH.Service.HerramientaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HerramientaServiceImp implements HerramientaService {
    private final HerramientaRepository herramientaRepository;

    public HerramientaServiceImp(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    public Herramienta guardarHerramienta(Herramienta herramienta) {
        if (herramienta.getCantidad() == null || herramienta.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa ni nula.");
        }
        return herramientaRepository.save(herramienta);
    }

    @Override
    public List<Herramienta> obtenerTodas() {
        return herramientaRepository.findAll();
    }

    @Override
    public Herramienta obtenerPorId(Long id) {
        return herramientaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La herramienta con ID " + id + " no existe."));
    }
}
