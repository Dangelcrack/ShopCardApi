package com.github.dangelcrack.shopcard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando no se encuentra un registro en la base de datos.
 * Automáticamente devuelve una respuesta HTTP 404 (NOT FOUND) cuando se produce.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
    private final String exceptionDetail;
    private final Object fieldValue;

    /**
     * Constructor para crear una nueva instancia de la excepción.
     *
     * @param exceptionDetail Detalle del error (ej. "No se encontró producto con ID")
     * @param fieldValue Valor del campo que causó el error (ej. el ID no encontrado)
     */
    public RecordNotFoundException(String exceptionDetail, Object fieldValue) {
        super(exceptionDetail + " - " + fieldValue);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = fieldValue;
    }

    /**
     * Obtiene el detalle específico de la excepción.
     * @return Mensaje detallado del error
     */
    public String getExceptionDetail() {
        return exceptionDetail;
    }

    /**
     * Obtiene el valor del campo que causó la excepción.
     * @return Valor del campo no encontrado
     */
    public Object getFieldValue() {
        return fieldValue;
    }
}