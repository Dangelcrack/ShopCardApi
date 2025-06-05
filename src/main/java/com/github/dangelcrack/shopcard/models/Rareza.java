package com.github.dangelcrack.shopcard.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entidad que representa los niveles de rareza que pueden tener los productos en el sistema.
 * Se mapea a la tabla "rarezas" en la base de datos.
 *
 * <p>La rareza determina la escasez y valor de un producto, y suele estar asociada
 * a características visuales como el color del borde o símbolo en la carta.</p>
 */
@Entity
@Table(name = "rarezas")
public class Rareza {

    /**
     * Identificador único de la rareza.
     * Generado automáticamente mediante estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del nivel de rareza (ej: "Común", "Rara", "Épica").
     * Requerido, longitud máxima de 30 caracteres.
     */
    @Size(max = 30)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    /**
     * Color asociado a la rareza (usado para representación visual).
     * Requerido, longitud máxima de 20 caracteres.
     */
    @Size(max = 20)
    @NotNull
    @Column(name = "color", nullable = false, length = 20)
    private String color;

    /**
     * Conjunto de productos que tienen esta rareza.
     * Relación uno-a-muchos con la entidad Producto.
     * Se usa JsonBackReference para evitar recursión infinita en serialización JSON.
     */
    @OneToMany(mappedBy = "rareza", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Producto> productos = new LinkedHashSet<>();

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Rareza() {}

    // Getters y Setters

    /**
     * Obtiene el ID de la rareza.
     * @return Identificador único
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el ID de la rareza.
     * @param id Identificador único
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la rareza.
     * @return Nombre del nivel de rareza
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la rareza.
     * @param nombre Nombre del nivel de rareza
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el color asociado a la rareza.
     * @return Color representativo
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color asociado a la rareza.
     * @param color Color representativo
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene los productos con esta rareza.
     * @return Conjunto de productos asociados
     */
    public Set<Producto> getProductos() {
        return productos;
    }

    /**
     * Establece los productos con esta rareza.
     * @param productos Conjunto de productos asociados
     */
    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Representación en cadena de la rareza (excluye la lista de productos).
     * @return Cadena con los principales atributos
     */
    @Override
    public String toString() {
        return "Rareza{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}