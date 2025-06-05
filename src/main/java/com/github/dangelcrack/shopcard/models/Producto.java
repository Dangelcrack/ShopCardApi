package com.github.dangelcrack.shopcard.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad que representa un producto en el sistema.
 * Se mapea a la tabla "productos" en la base de datos.
 */
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    @Column(length = 500)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull(message = "El precio original es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio original debe ser mayor que 0")
    @Column(name = "precio_original", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioOriginal;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;

    @Size(max = 255, message = "La URL de la imagen no puede exceder los 255 caracteres")
    @Column(name = "imagen_url")
    private String imagenUrl;

    @NotNull(message = "El número de carta es obligatorio")
    @Column(name = "numero_carta", nullable = false)
    private Integer numeroCarta;

    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @NotNull(message = "La colección es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coleccion_id", nullable = false)
    private Colecciones coleccion;

    @NotNull(message = "La rareza es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rareza_id", nullable = false)
    private Rareza rareza;

    @NotNull(message = "El estado es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadosProducto estado;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDate fechaCreacion = LocalDate.now();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Valoracione> valoraciones;

    public Producto() {}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public BigDecimal getPrecioOriginal() { return precioOriginal; }
    public void setPrecioOriginal(BigDecimal precioOriginal) { this.precioOriginal = precioOriginal; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public Integer getNumeroCarta() { return numeroCarta; }
    public void setNumeroCarta(Integer numeroCarta) { this.numeroCarta = numeroCarta; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public Colecciones getColeccion() { return coleccion; }
    public void setColeccion(Colecciones coleccion) { this.coleccion = coleccion; }
    public Rareza getRareza() { return rareza; }
    public void setRareza(Rareza rareza) { this.rareza = rareza; }
    public EstadosProducto getEstado() { return estado; }
    public void setEstado(EstadosProducto estado) { this.estado = estado; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public List<Valoracione> getValoraciones() { return valoraciones; }
    public void setValoraciones(List<Valoracione> valoraciones) { this.valoraciones = valoraciones; }

    /**
     * Verifica si el producto tiene descuento.
     * @return true si el precio actual es menor que el precio original
     */
    public boolean tieneDescuento() {
        return precio.compareTo(precioOriginal) < 0;
    }

    /**
     * Calcula el porcentaje de descuento aplicado al producto.
     * @return Porcentaje de descuento (0 si no hay descuento)
     */
    public BigDecimal calcularDescuento() {
        if (precioOriginal == null || precio == null) {
            return BigDecimal.ZERO;
        }
        if (precio.compareTo(precioOriginal) >= 0) {
            return BigDecimal.ZERO;
        }
        return precioOriginal.subtract(precio)
                .divide(precioOriginal, 4, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal(100));
    }

    /**
     * Calcula el rating promedio basado en las valoraciones.
     * @return Rating promedio (0 si no hay valoraciones)
     */
    public Double getRatingPromedio() {
        if (valoraciones == null || valoraciones.isEmpty()) {
            return 0.0;
        }
        return valoraciones.stream()
                .mapToInt(Valoracione::getPuntuacion)
                .average()
                .orElse(0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return id != null && id.equals(producto.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}