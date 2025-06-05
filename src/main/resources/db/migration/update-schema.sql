ALTER TABLE productos
    ADD precio_original DECIMAL(10, 2) NULL;

ALTER TABLE productos
    MODIFY precio_original DECIMAL (10, 2) NOT NULL;

ALTER TABLE productos
    MODIFY categoria_id INT NOT NULL;

ALTER TABLE productos
    MODIFY coleccion_id INT NOT NULL;

ALTER TABLE productos
    MODIFY descripcion VARCHAR (500);

ALTER TABLE productos
    MODIFY estado_id INT NOT NULL;

ALTER TABLE productos
DROP
COLUMN numero_carta;

ALTER TABLE productos
    ADD numero_carta INT NOT NULL;

ALTER TABLE productos
    MODIFY numero_carta INT NOT NULL;

ALTER TABLE productos
    MODIFY rareza_id INT NOT NULL;