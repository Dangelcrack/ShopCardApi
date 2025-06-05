package com.github.dangelcrack.shopcard.repositories;

import com.github.dangelcrack.shopcard.models.EstadosProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EstadosProductoRepository extends JpaRepository<EstadosProducto, Long> {
    @Query(
            value = "SELECT * FROM estados_producto AS e WHERE e.descripcion LIKE %?1",
            nativeQuery = true
    )
    List<EstadosProducto> findByDescripcionContaining(String descripcion);

    List<EstadosProducto> findByCodigoStartingWithIgnoreCase(String codigo);
}