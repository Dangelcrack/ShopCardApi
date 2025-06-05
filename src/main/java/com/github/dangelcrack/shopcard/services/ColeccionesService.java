package com.github.dangelcrack.shopcard.services;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.Colecciones;
import com.github.dangelcrack.shopcard.repositories.ColeccionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con las colecciones.
 */
@Service
public class ColeccionesService {

    @Autowired
    private ColeccionesRepository repository;

    /**
     * Obtiene todas las colecciones disponibles, con soporte para paginación.
     *
     * @param pageable Configuración de paginación.
     * @return Página de colecciones.
     */
    public Page<Colecciones> getAllColecciones(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Obtiene una colección específica por su ID.
     *
     * @param id Identificador único de la colección.
     * @return La colección correspondiente al ID proporcionado.
     * @throws RecordNotFoundException Si no se encuentra una colección con el ID especificado.
     */
    public Colecciones getColeccionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe colección para el ID: " + id, id));
    }

    /**
     * Crea una nueva colección en el repositorio.
     *
     * @param coleccion Objeto de colección a crear.
     * @return La colección creada.
     */
    public Colecciones createColeccion(Colecciones coleccion) {
        return repository.save(coleccion);
    }

    /**
     * Actualiza una colección existente con nuevos datos.
     *
     * @param id Identificador único de la colección a actualizar.
     * @param coleccionActualizada Objeto de colección con los datos actualizados.
     * @return La colección actualizada.
     * @throws RecordNotFoundException Si no se encuentra una colección con el ID especificado.
     */
    public Colecciones updateColeccion(Long id, Colecciones coleccionActualizada) {
        Colecciones coleccionExistente = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Colección no encontrada para el ID: " + id, id));

        coleccionExistente.setNombre(coleccionActualizada.getNombre());
        coleccionExistente.setFechaLanzamiento(coleccionActualizada.getFechaLanzamiento());
        coleccionExistente.setImagenUrl(coleccionActualizada.getImagenUrl());
        coleccionExistente.setDescripcion(coleccionActualizada.getDescripcion());
        coleccionExistente.setCodigo(coleccionActualizada.getCodigo());

        return repository.save(coleccionExistente);
    }

    /**
     * Elimina una colección existente del repositorio.
     *
     * @param id Identificador único de la colección a eliminar.
     * @throws RecordNotFoundException Si no se encuentra una colección con el ID especificado.
     */
    public void deleteColeccion(Long id) {
        Colecciones coleccion = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe colección para el ID: " + id, id));
        repository.delete(coleccion);
    }

    /**
     * Busca colecciones que contengan una cadena específica en su nombre.
     *
     * @param nombre Cadena a buscar dentro del nombre de las colecciones.
     * @return Lista de colecciones que contienen la cadena especificada en su nombre.
     */
    public List<Colecciones> findByNombreContaining(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    /**
     * Busca colecciones cuyos nombres comiencen con una cadena específica (ignorando mayúsculas/minúsculas).
     *
     * @param nombre Prefijo a buscar en el nombre de las colecciones.
     * @return Lista de colecciones cuyos nombres comienzan con el prefijo especificado.
     */
    public List<Colecciones> findByNombreStartingWith(String nombre) {
        return repository.findByNombreStartingWithIgnoreCase(nombre);
    }
}
