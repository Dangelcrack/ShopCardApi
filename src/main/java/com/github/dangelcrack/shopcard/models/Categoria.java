package com.github.dangelcrack.shopcard.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entidad que representa una categoría de productos en el sistema.
 * Se mapea a la tabla "categorias" en la base de datos.
 */
@Entity
@Table(name = "categorias")
public class Categoria {

    /**
     * Identificador único de la categoría.
     * Se genera automáticamente mediante una estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre de la categoría.
     * Requerido y con longitud máxima de 50 caracteres.
     */
    @Size(max = 50)
    @NotNull
    private String nombre;

    /**
     * Descripción detallada de la categoría.
     * Se almacena como Large Object (LOB) para permitir textos largos.
     */
    @Lob
    private String descripcion;

    /**
     * URL de la imagen representativa de la categoría.
     * Longitud máxima de 255 caracteres.
     */
    @Size(max = 255)
    @Column(name = "imagen_url")
    private String imagenUrl;

    /**
     * Conjunto de productos asociados a esta categoría.
     * Relación uno-a-muchos con la entidad Producto.
     * Se usa JsonBackReference para evitar recursión infinita en serialización JSON.
     */
    @OneToMany(mappedBy = "categoria")
    @JsonBackReference
    private Set<Producto> productos = new LinkedHashSet<>();

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Categoria() {}

    // Getters y Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public Set<Producto> getProductos() { return productos; }
    public void setProductos(Set<Producto> productos) { this.productos = productos; }

    /**
     * Representación en cadena de la categoría (excluye la lista de productos para evitar recursión).
     * @return Cadena con los principales atributos de la categoría
     */
    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenUrl='" + imagenUrl + '\'' +
                '}';
    }
}