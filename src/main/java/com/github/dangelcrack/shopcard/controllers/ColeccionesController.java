package com.github.dangelcrack.shopcard.controllers;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.Colecciones;
import com.github.dangelcrack.shopcard.services.ColeccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de colecciones.
 * Proporciona endpoints para crear, leer, actualizar y eliminar colecciones,
 * así como métodos de búsqueda específicos.
 */
@RestController
@RequestMapping("/api/colecciones")
@Validated
public class ColeccionesController {

    private final ColeccionesService service;

    /**
     * Constructor para inyección de dependencias.
     * @param service Servicio de colecciones a inyectar.
     */
    @Autowired
    public ColeccionesController(ColeccionesService service) {
        this.service = service;
    }

    /**
     * Obtiene todas las colecciones disponibles con paginación.
     * @param pageable Configuración de paginación.
     * @return ResponseEntity con la página de colecciones y estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<Page<Colecciones>> getAllColecciones(Pageable pageable) {
        Page<Colecciones> list = service.getAllColecciones(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Obtiene una colección específica por su ID.
     * @param id ID de la colección a buscar.
     * @return ResponseEntity con la colección encontrada y estado HTTP 200 (OK).
     * @throws RecordNotFoundException Si no se encuentra la colección con el ID especificado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Colecciones> getColeccionById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Colecciones coleccion = service.getColeccionById(id);
        return new ResponseEntity<>(coleccion, HttpStatus.OK);
    }

    /**
     * Crea una nueva colección.
     * @param coleccion Objeto Colecciones con los datos a crear.
     * @return ResponseEntity con la colección creada y estado HTTP 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Colecciones> createColeccion( @RequestBody Colecciones coleccion) {
        Colecciones createdColeccion = service.createColeccion(coleccion);
        return new ResponseEntity<>(createdColeccion, HttpStatus.CREATED);
    }

    /**
     * Actualiza una colección existente.
     * @param id ID de la colección a actualizar.
     * @param updatedColeccion Objeto Colecciones con los datos actualizados.
     * @return ResponseEntity con la colección actualizada y estado HTTP 200 (OK).
     * @throws RecordNotFoundException Si no se encuentra la colección con el ID especificado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Colecciones> updateColeccion(
            @PathVariable("id") Long id,
            @RequestBody Colecciones updatedColeccion) throws RecordNotFoundException {
        Colecciones coleccionUpdated = service.updateColeccion(id, updatedColeccion);
        return new ResponseEntity<>(coleccionUpdated, HttpStatus.OK);
    }

    /**
     * Elimina una colección existente.
     * @param id ID de la colección a eliminar.
     * @return ResponseEntity con estado HTTP 204 (NO_CONTENT) indicando éxito sin contenido.
     * @throws RecordNotFoundException Si no se encuentra la colección con el ID especificado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColeccion(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteColeccion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Busca colecciones cuyo nombre contenga el texto especificado.
     * @param nombre Texto a buscar en los nombres de colecciones.
     * @return ResponseEntity con la lista de colecciones encontradas y estado HTTP 200 (OK).
     */
    @GetMapping("/search/by-name")
    public ResponseEntity<List<Colecciones>> searchByNombre(@RequestParam("nombre") String nombre) {
        List<Colecciones> colecciones = service.findByNombreContaining(nombre);
        return new ResponseEntity<>(colecciones, HttpStatus.OK);
    }

    /**
     * Busca colecciones cuyo nombre comience con el texto especificado (case-insensitive).
     * @param nombre Texto inicial para buscar en los nombres de colecciones.
     * @return ResponseEntity con la lista de colecciones encontradas y estado HTTP 200 (OK).
     */
    @GetMapping("/search/starts-with")
    public ResponseEntity<List<Colecciones>> getByNombreStartingWith(@RequestParam("nombre") String nombre) {
        List<Colecciones> colecciones = service.findByNombreStartingWith(nombre);
        return new ResponseEntity<>(colecciones, HttpStatus.OK);
    }
}
