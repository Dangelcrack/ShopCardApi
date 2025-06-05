package com.github.dangelcrack.shopcard.services;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.EstadosProducto;
import com.github.dangelcrack.shopcard.repositories.EstadosProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con los estados de los productos.
 */
@Service
public class EstadosProductoService {

    @Autowired
    private EstadosProductoRepository repository;

    /**
     * Obtiene todos los estados de los productos disponibles en el repositorio.
     *
     * @return Lista de objetos EstadosProducto.
     */
    public List<EstadosProducto> getAllEstados() {
        return repository.findAll();
    }

    /**
     * Obtiene un estado de producto específico por su ID.
     *
     * @param id Identificador único del estado de producto.
     * @return Objeto EstadosProducto correspondiente al ID proporcionado.
     * @throws RecordNotFoundException Si no se encuentra un estado con el ID especificado.
     */
    public EstadosProducto getEstadoById(Long id) throws RecordNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe estado para el id: " + id, id));
    }

    /**
     * Crea un nuevo estado de producto en el repositorio.
     *
     * @param estado Objeto EstadosProducto a crear.
     * @return El estado de producto creado.
     */
    public EstadosProducto createEstado(EstadosProducto estado) {
        return repository.save(estado);
    }

    /**
     * Actualiza un estado de producto existente con nuevos datos.
     *
     * @param id Identificador único del estado de producto a actualizar.
     * @param estadoActualizado Objeto EstadosProducto con los datos actualizados.
     * @return El estado de producto actualizado.
     * @throws RecordNotFoundException Si no se encuentra un estado con el ID especificado.
     */
    public EstadosProducto updateEstado(Long id, EstadosProducto estadoActualizado) throws RecordNotFoundException {
        EstadosProducto estadoExistente = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Estado no encontrado con el id ", id));
        estadoExistente.setCodigo(estadoActualizado.getCodigo());
        estadoExistente.setDescripcion(estadoActualizado.getDescripcion());
        return repository.save(estadoExistente);
    }

    /**
     * Elimina un estado de producto existente del repositorio.
     *
     * @param id Identificador único del estado de producto a eliminar.
     * @throws RecordNotFoundException Si no se encuentra un estado con el ID especificado.
     */
    public void deleteEstado(Long id) throws RecordNotFoundException {
        EstadosProducto estado = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe estado para el id: " + id, id));
        repository.delete(estado);
    }

    /**
     * Busca estados de producto cuya descripción contenga una cadena específica.
     *
     * @param descripcion Cadena a buscar dentro de las descripciones de los estados de producto.
     * @return Lista de objetos EstadosProducto que contienen la cadena especificada en su descripción.
     */
    public List<EstadosProducto> findByDescripcionContaining(String descripcion) {
        return repository.findByDescripcionContaining(descripcion);
    }

    /**
     * Busca estados de producto cuyos códigos comiencen con una cadena específica (ignorando mayúsculas/minúsculas).
     *
     * @param codigo Prefijo a buscar en los códigos de los estados de producto.
     * @return Lista de objetos EstadosProducto cuyos códigos comienzan con el prefijo especificado.
     */
    public List<EstadosProducto> findByCodigoStartingWith(String codigo) {
        return repository.findByCodigoStartingWithIgnoreCase(codigo);
    }
}
