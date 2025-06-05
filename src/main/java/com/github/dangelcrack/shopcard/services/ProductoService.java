package com.github.dangelcrack.shopcard.services;

import com.github.dangelcrack.shopcard.exceptions.RecordNotFoundException;
import com.github.dangelcrack.shopcard.models.Producto;
import com.github.dangelcrack.shopcard.models.Valoracione;
import com.github.dangelcrack.shopcard.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar las operaciones relacionadas con los productos.
 */
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Constructor que permite la inyección del repositorio de productos.
     *
     * @param productoRepository Repositorio de productos a utilizar.
     */
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene todos los productos disponibles.
     *
     * @return Lista de objetos Producto.
     */
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    /**
     * Obtiene un producto específico por su ID.
     *
     * @param id Identificador único del producto.
     * @return Objeto Producto correspondiente al ID proporcionado.
     * @throws RecordNotFoundException Si no se encuentra un producto con el ID especificado.
     */
    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Producto no encontrado para el ID: " + id, id));
    }

    /**
     * Crea un nuevo producto en el repositorio.
     *
     * @param producto Objeto Producto a crear.
     * @return El producto creado.
     */
    public Producto createProducto(Producto producto) {
        validateProducto(producto);
        return productoRepository.save(producto);
    }

    /**
     * Actualiza un producto existente con nuevos datos.
     *
     * @param id Identificador único del producto a actualizar.
     * @param productoDetails Objeto Producto con los datos actualizados.
     * @return El producto actualizado.
     */
    public Producto updateProducto(Integer id, Producto productoDetails) {
        Producto producto = getProductoById(id);
        validateProducto(productoDetails);

        // Actualizar campos básicos
        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStock(productoDetails.getStock());
        producto.setImagenUrl(productoDetails.getImagenUrl());
        producto.setNumeroCarta(productoDetails.getNumeroCarta());
        producto.setPrecioOriginal(productoDetails.getPrecioOriginal());

        // Actualizar relaciones con validaciones
        if (productoDetails.getCategoria() != null) {
            producto.setCategoria(productoDetails.getCategoria());
        }
        if (productoDetails.getColeccion() != null) {
            producto.setColeccion(productoDetails.getColeccion());
        }
        if (productoDetails.getRareza() != null) {
            producto.setRareza(productoDetails.getRareza());
        }
        if (productoDetails.getEstado() != null) {
            producto.setEstado(productoDetails.getEstado());
        }

        // Manejar las valoraciones del producto
        updateValoraciones(producto, productoDetails.getValoraciones());

        return productoRepository.save(producto);
    }

    /**
     * Actualiza las valoraciones asociadas a un producto.
     *
     * @param producto Producto al que se le asignarán las valoraciones.
     * @param nuevasValoraciones Lista de valoraciones a asignar.
     */
    private void updateValoraciones(Producto producto, List<Valoracione> nuevasValoraciones) {
        if (nuevasValoraciones == null) return;

        // Limpiar valoraciones existentes y agregar las nuevas
        producto.getValoraciones().clear();
        for (Valoracione valoracion : nuevasValoraciones) {
            valoracion.setProducto(producto); // Establecer relación bidireccional
            producto.getValoraciones().add(valoracion);
        }
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id Identificador único del producto.
     * @throws RecordNotFoundException Si no se encuentra el producto con el ID especificado.
     */
    public void deleteProducto(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new RecordNotFoundException("Producto no encontrado para el ID: " + id, id);
        }
        productoRepository.deleteById(id);
    }

    /**
     * Busca productos cuyo nombre contenga una cadena específica (ignora mayúsculas/minúsculas).
     *
     * @param nombre Cadena a buscar en los nombres de los productos.
     * @return Lista de productos que coincidan con el criterio.
     */
    public List<Producto> findByNombreContaining(String nombre) {
        if (!StringUtils.hasText(nombre)) {
            throw new IllegalArgumentException("El término de búsqueda no puede estar vacío");
        }
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    /**
     * Busca productos por ID de categoría.
     *
     * @param categoriaId ID de la categoría.
     * @return Lista de productos que pertenecen a la categoría especificada.
     */
    public List<Producto> findByCategoriaId(Integer categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    /**
     * Busca productos por ID de colección.
     *
     * @param coleccionId ID de la colección.
     * @return Lista de productos que pertenecen a la colección especificada.
     */
    public List<Producto> findByColeccionId(Integer coleccionId) {
        return productoRepository.findByColeccionId(coleccionId);
    }

    /**
     * Busca productos por ID de rareza.
     *
     * @param rarezaId ID de la rareza.
     * @return Lista de productos que tienen la rareza especificada.
     */
    public List<Producto> findByRarezaId(Integer rarezaId) {
        return productoRepository.findByRarezaId(rarezaId);
    }

    /**
     * Busca productos por ID de estado.
     *
     * @param estadoId ID del estado.
     * @return Lista de productos que tienen el estado especificado.
     */
    public List<Producto> findByEstadoId(Integer estadoId) {
        return productoRepository.findByEstadoId(estadoId);
    }

    /**
     * Filtra productos basándose en múltiples criterios.
     *
     * @param categorias Lista de IDs de categorías.
     * @param colecciones Lista de IDs de colecciones.
     * @param rarezas Lista de IDs de rarezas.
     * @param estados Lista de IDs de estados.
     * @param ratings Lista de puntuaciones.
     * @param minPrice Precio mínimo.
     * @param maxPrice Precio máximo.
     * @param nombre Cadena a buscar en los nombres de los productos.
     * @return Lista de productos que coinciden con los criterios.
     */
    public List<Producto> filterProductos(
            List<Integer> categorias,
            List<Integer> colecciones,
            List<Integer> rarezas,
            List<Integer> estados,
            List<Integer> ratings,
            Double minPrice,
            Double maxPrice,
            String nombre) {

        List<Producto> productos = productoRepository.findAll();

        return productos.stream()
                .filter(p -> categorias == null || categorias.isEmpty() ||
                        (p.getCategoria() != null && categorias.contains(p.getCategoria().getId())))
                .filter(p -> colecciones == null || colecciones.isEmpty() ||
                        (p.getColeccion() != null && colecciones.contains(p.getColeccion().getId())))
                .filter(p -> rarezas == null || rarezas.isEmpty() ||
                        (p.getRareza() != null && rarezas.contains(p.getRareza().getId())))
                .filter(p -> estados == null || estados.isEmpty() ||
                        (p.getEstado() != null && estados.contains(p.getEstado().getId())))
                .filter(p -> ratings == null || ratings.isEmpty() ||
                        (p.getValoraciones() != null && hasMatchingRating(p.getValoraciones(), ratings)))
                .filter(p -> minPrice == null || (p.getPrecio() != null && p.getPrecio().doubleValue() >= minPrice))
                .filter(p -> maxPrice == null || (p.getPrecio() != null && p.getPrecio().doubleValue() <= maxPrice))
                .filter(p -> nombre == null || nombre.isEmpty() ||
                        (p.getNombre() != null && p.getNombre().toLowerCase().contains(nombre.toLowerCase())))
                .collect(Collectors.toList());
    }

    /**
     * Comprueba si las valoraciones promedio coinciden con las puntuaciones especificadas.
     *
     * @param valoraciones Lista de valoraciones.
     * @param ratings Lista de puntuaciones a comparar.
     * @return True si coincide, False en caso contrario.
     */
    private boolean hasMatchingRating(List<Valoracione> valoraciones, List<Integer> ratings) {
        if (valoraciones == null || valoraciones.isEmpty()) return false;

        double promedio = valoraciones.stream()
                .mapToInt(Valoracione::getPuntuacion)
                .average()
                .orElse(0.0);

        int promedioRedondeado = (int) Math.round(promedio);
        return ratings.contains(promedioRedondeado);
    }

    /**
     * Valida los datos del producto.
     *
     * @param producto Objeto Producto a validar.
     * @throws IllegalArgumentException Si los datos no son válidos.
     */
    private void validateProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (!StringUtils.hasText(producto.getNombre())) {
            throw new IllegalArgumentException("El nombre del producto es requerido");
        }
        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

    /**
     * Obtiene el promedio de las valoraciones de un producto específico.
     *
     * @param productoId Identificador único del producto.
     * @return Promedio de las valoraciones o 0.0 si no hay valoraciones.
     */
    public Double getAverageRatingByProductoId(Integer productoId) {
        Producto producto = getProductoById(productoId);
        if (producto.getValoraciones() == null || producto.getValoraciones().isEmpty()) {
            return 0.0;
        }

        return producto.getValoraciones().stream()
                .mapToInt(Valoracione::getPuntuacion)
                .average()
                .orElse(0.0);
    }
}
