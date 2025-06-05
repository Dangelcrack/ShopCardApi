package com.github.dangelcrack.shopcard.repositories;

import com.github.dangelcrack.shopcard.models.Rareza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RarezaRepository extends JpaRepository<Rareza, Long> {
    @Query(
            value = "SELECT * FROM rarezas AS r WHERE r.nombre LIKE %?1",
            nativeQuery = true
    )
    List<Rareza> findByNombreContaining(String nombre);

    List<Rareza> findByColorStartingWithIgnoreCase(String color);
}