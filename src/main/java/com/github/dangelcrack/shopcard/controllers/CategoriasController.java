package com.github.dangelcrack.shopcard.controllers;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.Categoria;
import com.github.dangelcrack.shopcard.services.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar operaciones CRUD de categorías.
 * Expone endpoints para crear, leer, actualizar y eliminar categorías,
 * así como para realizar búsquedas específicas.
 */
@RestController
@RequestMapping("/categorias") // Define la ruta base para todos los endpoints del controlador
public class CategoriasController {

    @Autowired
    private CategoriasService service; // Inyección del servicio de categorías

    /**
     * Obtiene todas las categorías existentes.
     *
     * @return ResponseEntity con la lista de todas las categorías y código HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> list = service.getAllCategorias();
        return ResponseEntity.ok(list);
    }

    /**
     * Obtiene una categoría específica por su ID.
     *
     * @param id ID de la categoría a buscar
     * @return ResponseEntity con la categoría encontrada y código HTTP 200 (OK)
     * @throws RecordNotFoundException si no se encuentra la categoría con el ID especificado
     */
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) throws RecordNotFoundException {
        Categoria categoria = service.getCategoriaById(id);
        return ResponseEntity.ok(categoria);
    }

    /**
     * Crea una nueva categoría.
     *
     * @param categoria Objeto Categoria con los datos a crear
     * @return ResponseEntity con la categoría creada y código HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        Categoria createdCategoria = service.createCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param id ID de la categoría a actualizar
     * @param updatedCategoria Objeto Categoria con los nuevos datos
     * @return ResponseEntity con la categoría actualizada y código HTTP 200 (OK)
     * @throws RecordNotFoundException si no se encuentra la categoría con el ID especificado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria updatedCategoria) throws RecordNotFoundException {
        Categoria categoriaUpdated = service.updateCategoria(id, updatedCategoria);
        return ResponseEntity.ok(categoriaUpdated);
    }

    /**
     * Elimina una categoría existente.
     *
     * @param id ID de la categoría a eliminar
     * @return ResponseEntity vacío con código HTTP 204 (NO_CONTENT)
     * @throws RecordNotFoundException si no se encuentra la categoría con el ID especificado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) throws RecordNotFoundException {
        service.deleteCategoria(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Busca categorías cuyo nombre contenga el texto especificado.
     *
     * @param nombre Texto a buscar dentro de los nombres de categoría
     * @return ResponseEntity con la lista de categorías encontradas y código HTTP 200 (OK)
     */
    @GetMapping("/search/{nombre}")
    public ResponseEntity<List<Categoria>> searchByNombre(@PathVariable String nombre) {
        List<Categoria> categorias = service.findByNombreContaining(nombre);
        return ResponseEntity.ok(categorias);
    }

    /**
     * Busca categorías cuyo nombre comience con el texto especificado.
     *
     * @param nombre Texto con el que deben comenzar los nombres de categoría
     * @return ResponseEntity con la lista de categorías encontradas y código HTTP 200 (OK)
     */
    @GetMapping("/inicial/{nombre}")
    public ResponseEntity<List<Categoria>> getByNombreStartingWith(@PathVariable String nombre) {
        List<Categoria> categorias = service.findByNombreStartingWith(nombre);
        return ResponseEntity.ok(categorias);
    }
}