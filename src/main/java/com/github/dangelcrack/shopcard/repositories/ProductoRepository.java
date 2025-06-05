package com.github.dangelcrack.shopcard.repositories;

import com.github.dangelcrack.shopcard.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCategoriaId(Integer categoriaId);

    List<Producto> findByColeccionId(Integer coleccionId);

    List<Producto> findByRarezaId(Integer rarezaId);

    List<Producto> findByEstadoId(Integer estadoId);
}