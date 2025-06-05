CREATE TABLE USUARIO (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         nombre VARCHAR(100),
                         apellido VARCHAR(100),
                         cedula VARCHAR(100),
                         correo VARCHAR(100) UNIQUE,
                         contrasena VARCHAR(100),
                         pregunta_seguridad VARCHAR(100),
                         respuesta_pregunta_seguridad VARCHAR(100),
                         numero_telefonico VARCHAR(100)
);

INSERT INTO USUARIO (nombre, apellido, cedula, correo, contrasena, pregunta_seguridad, respuesta_pregunta_seguridad, numero_telefonico)
VALUES ('Juan', 'Vargas', '123456789', 'juan@gmail.com', '123', 'nombre de su primera mascota', 'Emilio', '3123456789'),
       ('Santi', 'Martinez', '987654321', 'santi@gmail.com', '123', 'nombre del barrio en donde creció', 'bogota', '3112345678');
