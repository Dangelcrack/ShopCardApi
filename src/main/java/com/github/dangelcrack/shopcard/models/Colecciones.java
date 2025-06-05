package com.github.dangelcrack.shopcard.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entidad que representa una colección de productos en el sistema.
 * Se mapea a la tabla "colecciones" en la base de datos.
 */
@Entity
@Table(name = "colecciones")
public class Colecciones {

    /**
     * Identificador único de la colección.
     * Generado automáticamente mediante estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre de la colección.
     * Campo obligatorio con longitud máxima de 100 caracteres.
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Fecha de lanzamiento de la colección.
     */
    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;

    /**
     * URL de la imagen representativa de la colección.
     * Longitud máxima de 255 caracteres.
     */
    @Size(max = 255)
    @Column(name = "imagen_url")
    private String imagenUrl;

    /**
     * Descripción detallada de la colección.
     * Almacenada como Large Object (LOB) para permitir textos extensos.
     */
    @Lob
    private String descripcion;

    /**
     * Código único identificativo de la colección.
     * Longitud máxima de 10 caracteres.
     */
    @Size(max = 10)
    private String codigo;

    /**
     * Productos pertenecientes a esta colección.
     * Relación uno-a-muchos con la entidad Producto.
     * Se usa JsonBackReference para evitar recursión infinita en serialización JSON.
     */
    @OneToMany(mappedBy = "coleccion")
    @JsonBackReference
    private Set<Producto> productos = new LinkedHashSet<>();

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Colecciones() {}

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Representación en cadena de la colección (excluye la lista de productos para evitar recursión).
     * @return Cadena con los principales atributos de la colección
     */
    @Override
    public String toString() {
        return "Colecciones{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", imagenUrl='" + imagenUrl + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}