package com.github.dangelcrack.shopcard.repositories;

import com.github.dangelcrack.shopcard.models.Colecciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ColeccionesRepository extends JpaRepository<Colecciones, Long> {
    @Query(
            value = "SELECT * FROM colecciones AS c WHERE c.nombre LIKE %?1",
            nativeQuery = true
    )
    List<Colecciones> findByNombreContaining(String nombre);

    List<Colecciones> findByNombreStartingWithIgnoreCase(String nombre);
}