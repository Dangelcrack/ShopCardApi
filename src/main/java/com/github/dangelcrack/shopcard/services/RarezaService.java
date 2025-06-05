package com.github.dangelcrack.shopcard.services;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.Rareza;
import com.github.dangelcrack.shopcard.repositories.RarezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con las rarezas.
 */
@Service
public class RarezaService {

    @Autowired
    private RarezaRepository repository;

    /**
     * Obtiene todas las rarezas disponibles.
     *
     * @return Lista de objetos Rareza.
     */
    public List<Rareza> getAllRarezas() {
        return repository.findAll();
    }

    /**
     * Obtiene una rareza específica por su ID.
     *
     * @param id Identificador único de la rareza.
     * @return Objeto Rareza correspondiente al ID proporcionado.
     * @throws RecordNotFoundException Si no se encuentra una rareza con el ID especificado.
     */
    public Rareza getRarezaById(Long id) throws RecordNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe rareza para el id: " + id, id));
    }

    /**
     * Crea una nueva rareza en el repositorio.
     *
     * @param rareza Objeto Rareza a crear.
     * @return La rareza creada.
     */
    public Rareza createRareza(Rareza rareza) {
        return repository.save(rareza);
    }

    /**
     * Actualiza una rareza existente con nuevos datos.
     *
     * @param id Identificador único de la rareza a actualizar.
     * @param rarezaActualizada Objeto Rareza con los datos actualizados.
     * @return La rareza actualizada.
     * @throws RecordNotFoundException Si no se encuentra una rareza con el ID especificado.
     */
    public Rareza updateRareza(Long id, Rareza rarezaActualizada) throws RecordNotFoundException {
        Rareza rarezaExistente = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Rareza no encontrada con el id ", id));

        // Actualizar campos
        rarezaExistente.setNombre(rarezaActualizada.getNombre());
        rarezaExistente.setColor(rarezaActualizada.getColor());
        return repository.save(rarezaExistente);
    }

    /**
     * Elimina una rareza específica por su ID.
     *
     * @param id Identificador único de la rareza.
     * @throws RecordNotFoundException Si no se encuentra una rareza con el ID especificado.
     */
    public void deleteRareza(Long id) throws RecordNotFoundException {
        Rareza rareza = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe rareza para el id: " + id, id));
        repository.delete(rareza);
    }

    /**
     * Busca rarezas cuyo nombre contenga una cadena específica.
     *
     * @param nombre Cadena a buscar en los nombres de las rarezas.
     * @return Lista de rarezas que coincidan con el criterio.
     */
    public List<Rareza> findByNombreContaining(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    /**
     * Busca rarezas cuyo color comienza con una cadena específica.
     *
     * @param color Prefijo del color a buscar.
     * @return Lista de rarezas que coincidan con el criterio.
     */
    public List<Rareza> findByColorStartingWith(String color) {
        return repository.findByColorStartingWithIgnoreCase(color);
    }
}
