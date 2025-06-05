package com.github.dangelcrack.shopcard.repositories;

import com.github.dangelcrack.shopcard.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriasRepository extends JpaRepository<Categoria, Long> {

    @Query(
            value = "SELECT * FROM categorias AS c WHERE c.nombre LIKE %?1%",
            nativeQuery = true
    )
    List<Categoria> findByNombreContaining(String nombre);

    List<Categoria> findByNombreStartingWithIgnoreCase(String nombre);
}
