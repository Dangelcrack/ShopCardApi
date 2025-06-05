package com.github.dangelcrack.shopcard.controllers;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.Valoracione;
import com.github.dangelcrack.shopcard.services.ValoracioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las valoraciones de productos.
 * Proporciona operaciones CRUD estándar y endpoints para consultas específicas.
 */
@RestController
@RequestMapping("/valoraciones")
public class ValoracioneController {

    @Autowired
    private ValoracioneService service;

    /**
     * Obtiene todas las valoraciones registradas en el sistema.
     * @return ResponseEntity con lista de valoraciones y estado HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Valoracione>> getAllValoraciones() {
        List<Valoracione> list = service.getAllValoraciones();
        return ResponseEntity.ok(list);
    }

    /**
     * Obtiene una valoración específica por su ID.
     * @param id ID de la valoración
     * @return ResponseEntity con la valoración encontrada
     * @throws RecordNotFoundException si no se encuentra la valoración
     */
    @GetMapping("/{id}")
    public ResponseEntity<Valoracione> getValoracionById(@PathVariable Integer id) throws RecordNotFoundException {
        Valoracione valoracion = service.getValoracionById(id);
        return ResponseEntity.ok(valoracion);
    }

    /**
     * Crea una nueva valoración de producto.
     * @param valoracion Datos de la valoración a crear
     * @return ResponseEntity con la valoración creada y estado HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<Valoracione> createValoracion(@RequestBody Valoracione valoracion) {
        Valoracione createdValoracion = service.createValoracion(valoracion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdValoracion);
    }

    /**
     * Actualiza una valoración existente.
     * @param id ID de la valoración a actualizar
     * @param updatedValoracion Datos actualizados de la valoración
     * @return ResponseEntity con la valoración actualizada
     * @throws RecordNotFoundException si no se encuentra la valoración
     */
    @PutMapping("/{id}")
    public ResponseEntity<Valoracione> updateValoracion(
            @PathVariable Integer id,
            @RequestBody Valoracione updatedValoracion) throws RecordNotFoundException {
        Valoracione valoracionUpdated = service.updateValoracion(id, updatedValoracion);
        return ResponseEntity.ok(valoracionUpdated);
    }

    /**
     * Elimina una valoración existente.
     * @param id ID de la valoración a eliminar
     * @return HttpStatus 202 (ACCEPTED)
     * @throws RecordNotFoundException si no se encuentra la valoración
     */
    @DeleteMapping("/{id}")
    public HttpStatus deleteValoracion(@PathVariable Integer id) throws RecordNotFoundException {
        service.deleteValoracion(id);
        return HttpStatus.ACCEPTED;
    }

    /**
     * Obtiene valoraciones por ID de producto.
     * @param productoId ID del producto
     * @return ResponseEntity con lista de valoraciones del producto
     */
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Valoracione>> getByProductoId(@PathVariable Integer productoId) {
        List<Valoracione> valoraciones = service.findByProductoId(productoId);
        return ResponseEntity.ok(valoraciones);
    }

    /**
     * Obtiene valoraciones por puntuación específica.
     * @param puntuacion Puntuación a buscar (1-5)
     * @return ResponseEntity con lista de valoraciones con esa puntuación
     */
    @GetMapping("/puntuacion/{puntuacion}")
    public ResponseEntity<List<Valoracione>> getByPuntuacion(@PathVariable Byte puntuacion) {
        List<Valoracione> valoraciones = service.findByPuntuacion(puntuacion);
        return ResponseEntity.ok(valoraciones);
    }

    /**
     * Obtiene valoraciones por nombre de cliente.
     * @param nombreCliente Nombre o parte del nombre del cliente
     * @return ResponseEntity con lista de valoraciones del cliente
     */
    @GetMapping("/cliente/{nombreCliente}")
    public ResponseEntity<List<Valoracione>> getByNombreCliente(@PathVariable String nombreCliente) {
        List<Valoracione> valoraciones = service.findByNombreCliente(nombreCliente);
        return ResponseEntity.ok(valoraciones);
    }
}