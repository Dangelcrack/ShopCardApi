package com.github.dangelcrack.shopcard.controllers;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.EstadosProducto;
import com.github.dangelcrack.shopcard.services.EstadosProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar los estados de producto.
 * Proporciona operaciones CRUD estándar y endpoints adicionales para búsquedas específicas.
 */
@RestController
@RequestMapping("/estados-producto") // Ruta base para los endpoints de estados de producto
public class EstadosProductoController {

    @Autowired
    private EstadosProductoService service; // Servicio inyectado para la lógica de negocio

    /**
     * Obtiene todos los estados de producto registrados.
     *
     * @return ResponseEntity con lista de estados y código HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<EstadosProducto>> getAllEstados() {
        List<EstadosProducto> list = service.getAllEstados();
        return ResponseEntity.ok(list);
    }

    /**
     * Obtiene un estado de producto específico por su ID.
     *
     * @param id ID del estado a buscar
     * @return ResponseEntity con el estado encontrado y código HTTP 200 (OK)
     * @throws RecordNotFoundException Si no se encuentra el estado con el ID especificado
     */
    @GetMapping("/{id}")
    public ResponseEntity<EstadosProducto> getEstadoById(@PathVariable Long id) throws RecordNotFoundException {
        EstadosProducto estado = service.getEstadoById(id);
        return ResponseEntity.ok(estado);
    }

    /**
     * Crea un nuevo estado de producto.
     *
     * @param estado Objeto EstadosProducto con los datos a registrar
     * @return ResponseEntity con el estado creado y código HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<EstadosProducto> createEstado(@RequestBody EstadosProducto estado) {
        EstadosProducto createdEstado = service.createEstado(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEstado);
    }

    /**
     * Actualiza un estado de producto existente.
     *
     * @param id ID del estado a actualizar
     * @param updatedEstado Objeto con los nuevos datos del estado
     * @return ResponseEntity con el estado actualizado y código HTTP 200 (OK)
     * @throws RecordNotFoundException Si no se encuentra el estado con el ID especificado
     */
    @PutMapping("/{id}")
    public ResponseEntity<EstadosProducto> updateEstado(@PathVariable Long id, @RequestBody EstadosProducto updatedEstado) throws RecordNotFoundException {
        EstadosProducto estadoUpdated = service.updateEstado(id, updatedEstado);
        return ResponseEntity.ok(estadoUpdated);
    }

    /**
     * Elimina un estado de producto existente.
     *
     * @param id ID del estado a eliminar
     * @return HttpStatus ACCEPTED (202) indicando que la solicitud fue recibida
     * @throws RecordNotFoundException Si no se encuentra el estado con el ID especificado
     */
    @DeleteMapping("/{id}")
    public HttpStatus deleteEstado(@PathVariable Long id) throws RecordNotFoundException {
        service.deleteEstado(id);
        return HttpStatus.ACCEPTED;
    }

    /**
     * Busca estados de producto por descripción parcial.
     *
     * @param descripcion Texto a buscar en las descripciones
     * @return ResponseEntity con lista de estados coincidentes y código HTTP 200 (OK)
     */
    @GetMapping("/search/{descripcion}")
    public ResponseEntity<List<EstadosProducto>> searchByDescripcion(@PathVariable String descripcion) {
        List<EstadosProducto> estados = service.findByDescripcionContaining(descripcion);
        return ResponseEntity.ok(estados);
    }

    /**
     * Busca estados de producto por código inicial.
     *
     * @param codigo Texto inicial del código a buscar
     * @return ResponseEntity con lista de estados coincidentes y código HTTP 200 (OK)
     */
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<List<EstadosProducto>> getByCodigoStartingWith(@PathVariable String codigo) {
        List<EstadosProducto> estados = service.findByCodigoStartingWith(codigo);
        return ResponseEntity.ok(estados);
    }
}