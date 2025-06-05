package com.github.dangelcrack.shopcard.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;

/**
 * Entidad que representa las valoraciones de los clientes sobre los productos.
 * Se mapea a la tabla "valoraciones" en la base de datos.
 *
 * <p>Cada valoración contiene una puntuación numérica y opcionalmente un comentario,
 * asociados a un producto específico y un cliente.</p>
 */
@Entity
@Table(name = "valoraciones")
public class Valoracione {

    /**
     * Identificador único de la valoración.
     * Generado automáticamente mediante estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del cliente que realizó la valoración.
     * Campo obligatorio.
     */
    @NotNull
    @Column(name = "nombre_cliente", nullable = false, length = 100)
    private String nombreCliente;

    /**
     * Producto asociado a esta valoración.
     * Relación muchos-a-uno con la entidad Producto.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    /**
     * Puntuación asignada al producto (normalmente de 1 a 5).
     * Campo obligatorio.
     */
    @NotNull
    @Column(name = "puntuacion", nullable = false)
    private Byte puntuacion;

    /**
     * Comentario opcional sobre el producto.
     * Almacenado como Large Object (LOB) para permitir textos extensos.
     */
    @Lob
    @Column(length = 2000)
    private String comentario;

    /**
     * Fecha y hora de creación de la valoración.
     * Se establece automáticamente al momento de creación.
     */
    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "fecha", nullable = false, updatable = false)
    private Instant fecha;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Valoracione() {
        this.fecha = Instant.now();
    }

    // Getters y Setters

    /**
     * Obtiene el ID de la valoración.
     * @return Identificador único
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el ID de la valoración.
     * @param id Identificador único
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return Nombre del cliente que realizó la valoración
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombreCliente Nombre del cliente
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Obtiene el producto valorado.
     * @return Producto asociado a la valoración
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto valorado.
     * @param producto Producto asociado
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la puntuación asignada.
     * @return Puntuación (normalmente 1-5)
     */
    public Byte getPuntuacion() {
        return puntuacion;
    }

    /**
     * Establece la puntuación.
     * @param puntuacion Valor de 1 a 5
     */
    public void setPuntuacion(Byte puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Obtiene el comentario de la valoración.
     * @return Comentario opcional
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece el comentario.
     * @param comentario Texto del comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Obtiene la fecha de creación.
     * @return Fecha y hora de la valoración
     */
    public Instant getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de creación (solo para uso interno).
     * @param fecha Fecha y hora
     */
    protected void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    /**
     * Representación en cadena de la valoración.
     * @return Cadena con los principales atributos
     */
    @Override
    public String toString() {
        return "Valoracione{" +
                "id=" + id +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", productoId=" + (producto != null ? producto.getId() : null) +
                ", puntuacion=" + puntuacion +
                ", comentario='" + (comentario != null ? "[comentario]" : null) + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}