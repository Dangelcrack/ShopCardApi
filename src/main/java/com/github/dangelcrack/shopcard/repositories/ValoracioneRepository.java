package com.github.dangelcrack.shopcard.repositories;

import com.github.dangelcrack.shopcard.models.Valoracione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValoracioneRepository extends JpaRepository<Valoracione, Integer> {
    List<Valoracione> findByProductoId(Integer productoId);
    List<Valoracione> findByPuntuacion(Byte puntuacion);
    List<Valoracione> findByNombreClienteContainingIgnoreCase(String nombreCliente);
}