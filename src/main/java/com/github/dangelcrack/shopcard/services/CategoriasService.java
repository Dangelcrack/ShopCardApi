package com.github.dangelcrack.shopcard.services;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.Categoria;
import com.github.dangelcrack.shopcard.repositories.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para manejar operaciones relacionadas con categorías.
 * Proporciona métodos para el CRUD de categorías y búsquedas específicas.
 */
@Service
@Transactional
public class CategoriasService {

    private final CategoriasRepository repository;

    @Autowired
    public CategoriasService(CategoriasRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene todas las categorías existentes.
     * @return Lista de todas las categorías
     */
    public List<Categoria> getAllCategorias() {
        return repository.findAll();
    }

    /**
     * Obtiene una categoría por su ID.
     * @param id ID de la categoría a buscar
     * @return La categoría encontrada
     * @throws RecordNotFoundException Si no se encuentra la categoría
     */
    public Categoria getCategoriaById(Long id) throws RecordNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No se encontró categoría con ID: " + id, id));
    }

    /**
     * Crea una nueva categoría.
     * @param categoria La categoría a crear
     * @return La categoría creada
     */
    public Categoria createCategoria(Categoria categoria) {
        return repository.save(categoria);
    }

    /**
     * Actualiza una categoría existente.
     * @param id ID de la categoría a actualizar
     * @param categoriaActualizada Datos actualizados de la categoría
     * @return La categoría actualizada
     * @throws RecordNotFoundException Si no se encuentra la categoría
     */
    public Categoria updateCategoria(Long id, Categoria categoriaActualizada) throws RecordNotFoundException {
        Categoria categoriaExistente = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No se encontró categoría con ID: " + id, id));

        categoriaExistente.setNombre(categoriaActualizada.getNombre());
        categoriaExistente.setDescripcion(categoriaActualizada.getDescripcion());
        categoriaExistente.setImagenUrl(categoriaActualizada.getImagenUrl());

        return repository.save(categoriaExistente);
    }

    /**
     * Elimina una categoría existente.
     * @param id ID de la categoría a eliminar
     * @throws RecordNotFoundException Si no se encuentra la categoría
     */
    public void deleteCategoria(Long id) throws RecordNotFoundException {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException("No se encontró categoría con ID: " + id, id);
        }
    }

    /**
     * Busca categorías cuyo nombre contenga el texto especificado.
     * @param nombre Texto a buscar en los nombres de categoría
     * @return Lista de categorías que coinciden con el criterio
     */
    public List<Categoria> findByNombreContaining(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    /**
     * Busca categorías cuyo nombre comience con el texto especificado.
     * @param nombre Texto inicial para buscar
     * @return Lista de categorías que coinciden con el criterio
     */
    public List<Categoria> findByNombreStartingWith(String nombre) {
        return repository.findByNombreStartingWithIgnoreCase(nombre);
    }

    /**
     * Verifica si una categoría existe por su ID.
     * @param id ID de la categoría a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}