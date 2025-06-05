package com.github.dangelcrack.shopcard.controllers;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.Rareza;
import com.github.dangelcrack.shopcard.services.RarezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar las rarezas de productos.
 * Proporciona operaciones CRUD estándar y endpoints adicionales para búsquedas específicas.
 */
@RestController
@RequestMapping("/rarezas")
public class RarezaController {

    @Autowired
    private RarezaService service;

    /**
     * Obtiene todas las rarezas registradas en el sistema.
     * @return ResponseEntity con lista de rarezas y estado HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Rareza>> getAllRarezas() {
        List<Rareza> list = service.getAllRarezas();
        return ResponseEntity.ok(list);
    }

    /**
     * Obtiene una rareza específica por su ID.
     * @param id ID de la rareza a buscar
     * @return ResponseEntity con la rareza encontrada y estado HTTP 200 (OK)
     * @throws RecordNotFoundException si no se encuentra la rareza con el ID especificado
     */
    @GetMapping("/{id}")
    public ResponseEntity<Rareza> getRarezaById(@PathVariable Long id) throws RecordNotFoundException {
        Rareza rareza = service.getRarezaById(id);
        return ResponseEntity.ok(rareza);
    }

    /**
     * Crea una nueva rareza en el sistema.
     * @param rareza Objeto Rareza con los datos a registrar
     * @return ResponseEntity con la rareza creada y estado HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<Rareza> createRareza(@RequestBody Rareza rareza) {
        Rareza createdRareza = service.createRareza(rareza);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRareza);
    }

    /**
     * Actualiza una rareza existente.
     * @param id ID de la rareza a actualizar
     * @param updatedRareza Objeto con los nuevos datos de la rareza
     * @return ResponseEntity con la rareza actualizada y estado HTTP 200 (OK)
     * @throws RecordNotFoundException si no se encuentra la rareza con el ID especificado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Rareza> updateRareza(@PathVariable Long id, @RequestBody Rareza updatedRareza) throws RecordNotFoundException {
        Rareza rarezaUpdated = service.updateRareza(id, updatedRareza);
        return ResponseEntity.ok(rarezaUpdated);
    }

    /**
     * Elimina una rareza existente.
     * @param id ID de la rareza a eliminar
     * @return HttpStatus ACCEPTED (202) indicando que la solicitud fue recibida
     * @throws RecordNotFoundException si no se encuentra la rareza con el ID especificado
     */
    @DeleteMapping("/{id}")
    public HttpStatus deleteRareza(@PathVariable Long id) throws RecordNotFoundException {
        service.deleteRareza(id);
        return HttpStatus.ACCEPTED;
    }

    /**
     * Busca rarezas cuyo nombre contenga el texto especificado.
     * @param nombre Texto a buscar en los nombres de rarezas
     * @return ResponseEntity con lista de rarezas coincidentes y estado HTTP 200 (OK)
     */
    @GetMapping("/search/{nombre}")
    public ResponseEntity<List<Rareza>> searchByNombre(@PathVariable String nombre) {
        List<Rareza> rarezas = service.findByNombreContaining(nombre);
        return ResponseEntity.ok(rarezas);
    }

    /**
     * Busca rarezas cuyo color comience con el texto especificado.
     * @param color Texto inicial del color a buscar
     * @return ResponseEntity con lista de rarezas coincidentes y estado HTTP 200 (OK)
     */
    @GetMapping("/color/{color}")
    public ResponseEntity<List<Rareza>> getByColorStartingWith(@PathVariable String color) {
        List<Rareza> rarezas = service.findByColorStartingWith(color);
        return ResponseEntity.ok(rarezas);
    }
}