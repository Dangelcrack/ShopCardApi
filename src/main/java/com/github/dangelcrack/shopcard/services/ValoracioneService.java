package com.github.dangelcrack.shopcard.services;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.Valoracione;
import com.github.dangelcrack.shopcard.repositories.ValoracioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con las valoraciones.
 */
@Service
public class ValoracioneService {

    @Autowired
    private ValoracioneRepository repository;

    /**
     * Obtiene todas las valoraciones disponibles.
     *
     * @return Lista de objetos Valoracione.
     */
    public List<Valoracione> getAllValoraciones() {
        return repository.findAll();
    }

    /**
     * Obtiene una valoración específica por su ID.
     *
     * @param id Identificador único de la valoración.
     * @return Objeto Valoracione correspondiente al ID proporcionado.
     * @throws RecordNotFoundException Si no se encuentra una valoración con el ID especificado.
     */
    public Valoracione getValoracionById(Integer id) throws RecordNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe valoración para el id: " + id, id));
    }

    /**
     * Crea una nueva valoración en el repositorio.
     *
     * @param valoracion Objeto Valoracione a crear.
     * @return La valoración creada.
     */
    public Valoracione createValoracion(Valoracione valoracion) {
        return repository.save(valoracion);
    }

    /**
     * Actualiza una valoración existente con nuevos datos.
     *
     * @param id Identificador único de la valoración a actualizar.
     * @param valoracionActualizada Objeto Valoracione con los datos actualizados.
     * @return La valoración actualizada.
     * @throws RecordNotFoundException Si no se encuentra una valoración con el ID especificado.
     */
    public Valoracione updateValoracion(Integer id, Valoracione valoracionActualizada) throws RecordNotFoundException {
        Valoracione valoracionExistente = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Valoración no encontrada con el id ", id));

        // Actualizar campos de la valoración
        valoracionExistente.setNombreCliente(valoracionActualizada.getNombreCliente());
        valoracionExistente.setProducto(valoracionActualizada.getProducto());
        valoracionExistente.setPuntuacion(valoracionActualizada.getPuntuacion());
        valoracionExistente.setComentario(valoracionActualizada.getComentario());

        return repository.save(valoracionExistente);
    }

    /**
     * Elimina una valoración específica por su ID.
     *
     * @param id Identificador único de la valoración.
     * @throws RecordNotFoundException Si no se encuentra una valoración con el ID especificado.
     */
    public void deleteValoracion(Integer id) throws RecordNotFoundException {
        Valoracione valoracion = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe valoración para el id: " + id, id));
        repository.delete(valoracion);
    }

    /**
     * Busca valoraciones asociadas a un producto específico.
     *
     * @param productoId Identificador único del producto.
     * @return Lista de valoraciones asociadas al producto.
     */
    public List<Valoracione> findByProductoId(Integer productoId) {
        return repository.findByProductoId(productoId);
    }

    /**
     * Busca valoraciones que coincidan con una puntuación específica.
     *
     * @param puntuacion Puntuación exacta a buscar.
     * @return Lista de valoraciones con la puntuación especificada.
     */
    public List<Valoracione> findByPuntuacion(Byte puntuacion) {
        return repository.findByPuntuacion(puntuacion);
    }

    /**
     * Busca valoraciones cuyos nombres de cliente contengan una cadena específica.
     *
     * @param nombreCliente Cadena a buscar en los nombres de los clientes.
     * @return Lista de valoraciones que coincidan con el criterio.
     */
    public List<Valoracione> findByNombreCliente(String nombreCliente) {
        return repository.findByNombreClienteContainingIgnoreCase(nombreCliente);
    }
}
