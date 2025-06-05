DROP TABLE IF EXISTS MENSAJES;
DROP TABLE IF EXISTS PUBLICACIONES;
DROP TABLE IF EXISTS INMUEBLE;
DROP TABLE IF EXISTS USUARIO;

CREATE TABLE USUARIO (
                         ID INTEGER AUTO_INCREMENT PRIMARY KEY,
                         NOMBRE VARCHAR(100) NOT NULL,
                         APELLIDO VARCHAR(100) NOT NULL,
                         CEDULA INTEGER NOT NULL UNIQUE,
                         CORREO VARCHAR(120) NOT NULL UNIQUE,
                         CONTRASENA VARCHAR(128) NOT NULL,
                         PREGUNTA_SEGURIDAD VARCHAR(120) NOT NULL,
                         RESPUESTA_PREGUNTA_SEGURIDAD VARCHAR(120) NOT NULL,
                         NUMERO_TELEFONICO VARCHAR(20) NOT NULL
);


CREATE TABLE INMUEBLE (
                          ID INTEGER AUTO_INCREMENT PRIMARY KEY,
                          TIPO VARCHAR(50) NOT NULL,
                          ESTADO VARCHAR(50) NOT NULL,
                          DIRECCION VARCHAR(255) NOT NULL,
                          ESTRATO INTEGER NOT NULL,
                          HABITACIONES INTEGER NOT NULL,
                          BANOS INTEGER NOT NULL,
                          AREA DOUBLE NOT NULL,
                          PRECIO DECIMAL(18,2) NOT NULL,
                          IMAGEN BLOB
);


CREATE TABLE PUBLICACIONES (
                               ID INTEGER AUTO_INCREMENT PRIMARY KEY,
                               ID_USUARIO INTEGER NOT NULL,
                               ID_INMUEBLE INTEGER NOT NULL,
                               ESTADO VARCHAR(50) NOT NULL,
                               FECHA_PUBLICACION DATE NOT NULL,
                               FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID),
                               FOREIGN KEY (ID_INMUEBLE) REFERENCES INMUEBLE(ID)
);


CREATE TABLE MENSAJES (
                          ID INTEGER AUTO_INCREMENT PRIMARY KEY,
                          FK_ID_PUBLICACION INTEGER NOT NULL,
                          EMISOR_ID INTEGER NOT NULL,
                          RECEPTOR_ID INTEGER NOT NULL,
                          CONTENIDO VARCHAR(1000) NOT NULL,
                          FECHA TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (FK_ID_PUBLICACION) REFERENCES PUBLICACIONES(ID),
                          FOREIGN KEY (EMISOR_ID) REFERENCES USUARIO(ID),
                          FOREIGN KEY (RECEPTOR_ID) REFERENCES USUARIO(ID)
);


