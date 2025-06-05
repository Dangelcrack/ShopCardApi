package com.github.dangelcrack.shopcard.controllers;

import com.github.dangelcrack.shopcard.models.Producto;
import com.github.dangelcrack.shopcard.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con productos.
 * Proporciona endpoints para CRUD básico, búsquedas específicas y filtrado avanzado.
 */
@RestController
@RequestMapping("/api/productos") // Ruta base para todos los endpoints de productos
public class ProductoController {

    private final ProductoService productoService; // Servicio para la lógica de negocio de productos

    /**
     * Constructor para inyección de dependencias.
     * @param productoService Servicio de productos inyectado
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Obtiene todos los productos disponibles.
     * @return Lista de productos con código HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    /**
     * Obtiene un producto específico por su ID.
     * @param id ID del producto a buscar
     * @return Producto encontrado con código HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        return ResponseEntity.ok(productoService.getProductoById(id));
    }

    /**
     * Crea un nuevo producto.
     * @param producto Datos del producto a crear
     * @return Producto creado con código HTTP 200 (OK)
     */
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.createProducto(producto));
    }

    /**
     * Actualiza un producto existente.
     * @param id ID del producto a actualizar
     * @param producto Nuevos datos del producto
     * @return Producto actualizado con código HTTP 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.updateProducto(id, producto));
    }

    /**
     * Elimina un producto existente.
     * @param id ID del producto a eliminar
     * @return Respuesta vacía con código HTTP 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca productos cuyo nombre contenga el texto especificado.
     * @param nombre Texto a buscar en los nombres de productos
     * @return Lista de productos coincidentes con código HTTP 200 (OK)
     */
    @GetMapping("/search")
    public ResponseEntity<List<Producto>> searchByNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.findByNombreContaining(nombre));
    }

    /**
     * Obtiene productos pertenecientes a una categoría específica.
     * @param categoriaId ID de la categoría
     * @return Lista de productos de la categoría con código HTTP 200 (OK)
     */
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getByCategoriaId(@PathVariable Integer categoriaId) {
        return ResponseEntity.ok(productoService.findByCategoriaId(categoriaId));
    }

    /**
     * Obtiene productos pertenecientes a una colección específica.
     * @param coleccionId ID de la colección
     * @return Lista de productos de la colección con código HTTP 200 (OK)
     */
    @GetMapping("/coleccion/{coleccionId}")
    public ResponseEntity<List<Producto>> getByColeccionId(@PathVariable Integer coleccionId) {
        return ResponseEntity.ok(productoService.findByColeccionId(coleccionId));
    }

    /**
     * Obtiene productos con una rareza específica.
     * @param rarezaId ID de la rareza
     * @return Lista de productos con la rareza especificada con código HTTP 200 (OK)
     */
    @GetMapping("/rareza/{rarezaId}")
    public ResponseEntity<List<Producto>> getByRarezaId(@PathVariable Integer rarezaId) {
        return ResponseEntity.ok(productoService.findByRarezaId(rarezaId));
    }

    /**
     * Obtiene productos con un estado específico.
     * @param estadoId ID del estado
     * @return Lista de productos con el estado especificado con código HTTP 200 (OK)
     */
    @GetMapping("/estado/{estadoId}")
    public ResponseEntity<List<Producto>> getByEstadoId(@PathVariable Integer estadoId) {
        return ResponseEntity.ok(productoService.findByEstadoId(estadoId));
    }

    /**
     * Filtra productos según múltiples criterios.
     * Todos los parámetros son opcionales.
     *
     * @param categorias Lista de IDs de categorías para filtrar
     * @param colecciones Lista de IDs de colecciones para filtrar
     * @param rarezas Lista de IDs de rarezas para filtrar
     * @param estados Lista de IDs de estados para filtrar
     * @param ratings Lista de ratings para filtrar
     * @param minPrice Precio mínimo (opcional)
     * @param maxPrice Precio máximo (opcional)
     * @param nombre Texto para búsqueda en nombre (opcional)
     * @return Lista de productos filtrados con código HTTP 200 (OK)
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Producto>> filterProductos(
            @RequestParam(required = false) List<Integer> categorias,
            @RequestParam(required = false) List<Integer> colecciones,
            @RequestParam(required = false) List<Integer> rarezas,
            @RequestParam(required = false) List<Integer> estados,
            @RequestParam(required = false) List<Integer> ratings,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String nombre) {

        return ResponseEntity.ok(productoService.filterProductos(
                categorias, colecciones, rarezas, estados, ratings, minPrice, maxPrice, nombre));
    }

    /**
     * Obtiene el rating promedio de un producto específico.
     * @param productoId ID del producto
     * @return Valor del rating promedio con código HTTP 200 (OK)
     */
    @GetMapping("/{productoId}/rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Integer productoId) {
        return ResponseEntity.ok(productoService.getAverageRatingByProductoId(productoId));
    }
}