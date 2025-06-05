package com.github.dangelcrack.shopcard.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entidad que representa los posibles estados de un producto en el sistema.
 * Se mapea a la tabla "estados_producto" en la base de datos.
 */
@Entity
@Table(name = "estados_producto")
public class EstadosProducto {

    /**
     * Identificador único del estado de producto.
     * Generado automáticamente mediante estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Código único del estado (ej: "NUEVO", "USADO").
     * Requerido, longitud máxima de 5 caracteres, solo mayúsculas.
     */
    @Size(max = 5)
    @NotNull
    @Pattern(regexp = "^[A-Z]+$", message = "El código debe contener solo letras mayúsculas")
    @Column(name = "codigo", nullable = false, length = 5, unique = true)
    private String codigo;

    /**
     * Descripción del estado del producto.
     * Requerido, longitud máxima de 50 caracteres.
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    /**
     * Productos que tienen este estado.
     * Relación uno-a-muchos con la entidad Producto.
     * Se usa JsonBackReference para evitar recursión infinita en serialización JSON.
     */
    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Producto> productos = new LinkedHashSet<>();

    /**
     * Constructor por defecto requerido por JPA.
     */
    public EstadosProducto() {}

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Representación en cadena del estado de producto (excluye la lista de productos).
     * @return Cadena con los principales atributos del estado
     */
    @Override
    public String toString() {
        return "EstadosProducto{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    /**
     * Comparación basada en el identificador único.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstadosProducto)) return false;
        EstadosProducto that = (EstadosProducto) o;
        return id != null && id.equals(that.id);
    }

    /**
     * Hash code basado en el identificador único.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}