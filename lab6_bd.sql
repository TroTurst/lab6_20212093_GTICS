-- =========================================
-- CREACIÓN DE TABLAS
-- =========================================

CREATE DATABASE IF NOT EXISTS lab6;
USE `lab6` ;
CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol_id BIGINT NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

CREATE TABLE heroes_navales (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    -- CAMBIO: Añadidas columnas para Ejercicio 1
    rango VARCHAR(50), 
    fecha_nacimiento DATE,
    reseña VARCHAR(255), -- Renombrado de 'descripcion'
    pais VARCHAR(50)
);

CREATE TABLE intenciones (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE canciones_criollas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    letra TEXT
);

CREATE TABLE asignaciones_cancion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    cancion_id BIGINT NOT NULL,
    intentos INT DEFAULT 0,
    adivinada BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (cancion_id) REFERENCES canciones_criollas(id)
);

CREATE TABLE numeros_casa (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    numero_objetivo INT NOT NULL,
    intentos INT DEFAULT 0,
    adivinado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE mesas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero INT NOT NULL UNIQUE,
    capacidad INT NOT NULL DEFAULT 4,
    disponible BOOLEAN DEFAULT TRUE
);

CREATE TABLE reservas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    mesa_id BIGINT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (mesa_id) REFERENCES mesas(id)
);

-- =========================================
-- INSERCIÓN DE DATOS BASE
-- =========================================

-- Roles
INSERT INTO roles (nombre) VALUES 
('ADMIN'),
('USUARIO'),
('VISITANTE');

INSERT INTO usuarios (nombre, correo, password, rol_id) VALUES
-- Hombres
('Carlos Vargas', 'carlos.vargas@example.com', '123456', 2),
('Xavier Ruiz', 'xavier.ruiz@example.com', '123456', 2),
('Victor Guerra', 'victor.guerra@example.com', '123456', 2),
('Diego Torres', 'diego.torres@example.com', '123456', 2),
('Jorge Rubio', 'jorge.rubio@example.com', '123456', 2),
('Paolo Mendoza', 'paolo.mendoza@example.com', '123456', 2),
('Alonso Llanos', 'alonso.llanos@example.com', '123456', 2),
('Ronald Sanchez', 'ronald.sanchez@example.com', '123456', 2),
('Luis Cotrina', 'luis.cotrina@example.com', '123456', 2),
('Jhocell Perez', 'jhocell.perez@example.com', '123456', 2),
('Paolo Valiente', 'paolo.valiente@example.com', '123456', 2),
('Mariana Rojas', 'mariana.rojas@example.com', '123456', 2),
('Camila Fernández', 'camila.fernandez@example.com', '123456', 2),
('Valeria Campos', 'valeria.campos@example.com', '123456', 2),
('Gabriela Paredes', 'gabriela.paredes@example.com', '123456', 2),
('Andrea Lozano', 'andrea.lozano@example.com', '123456', 2),
('Sofía Delgado', 'sofia.delgado@example.com', '123456', 2),
('Lucía Herrera', 'lucia.herrera@example.com', '123456', 2),
('Rosa Aguilar', 'rosa.aguilar@example.com', '123456', 2),
('Claudia Navarro', 'claudia.navarro@example.com', '123456', 2),
('Alejandra Cornejo', 'alejandra.cornejo@example.com', '123456', 2),
('Administrador', 'admin@example.com', 'admin123', 1);

-- Heroes Navales (Actualizados con datos de ejemplo para las nuevas columnas)
INSERT INTO heroes_navales (nombre, rango, fecha_nacimiento, reseña, pais) VALUES
('Miguel Grau', 'Almirante', '1834-07-27', 'El Caballero de los Mares, héroe máximo del Perú, conocido por su caballerosidad en combate.', 'Perú'),
('Arturo Prat', 'Capitán de Fragata', '1848-04-03', 'Héroe naval chileno, líder del abordaje en el Combate Naval de Iquique.', 'Chile'),
('Almirante Brown', 'Almirante Mayor', '1777-06-22', 'Fundador y primer Almirante de la Armada Argentina, clave en las guerras de independencia.', 'Argentina'),
('Simón Bolívar', 'General en Jefe', '1783-07-24', 'Líder militar y político, también clave en campañas navales en el Caribe por la liberación de varios países.', 'Venezuela');

INSERT INTO intenciones (usuario_id, descripcion) VALUES
(1, 'Quiero participar en la campaña de mensajes.'),
(2, 'Deseo enviar flores amarillas a mis amigos.'),
(3, 'Me gustaría sumarme con un carrito de amistad.'),
(4, 'Solicitud de asignación de Canción Criolla.'), -- Ejemplo de solicitud para el Juego 3
(5, 'Solicitud de asignación de Número de Casa para Halloween.'); -- Ejemplo de solicitud para el Juego 4

INSERT INTO canciones_criollas (titulo, letra) VALUES
('La Flor de la Canela', 'Yo perdí el corazón'),
('Contigo Perú', 'Cuando despiertan mis ojos'),
('Valicha', 'Esta es mi tierra'),
('Ritmo, color y sabor', 'Jipi jay'),
('Y se llama Perú', 'Toro Mata');

INSERT INTO asignaciones_cancion (usuario_id, cancion_id) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO numeros_casa (usuario_id, numero_objetivo) VALUES
(1, 75), -- Número objetivo mayor a 64
(2, 90),
(3, 101);

INSERT INTO mesas (numero, capacidad, disponible) VALUES
(1, 4, FALSE), -- Cambiada a FALSE para simular una reserva existente
(2, 4, TRUE),
(3, 4, TRUE),
(4, 4, TRUE),
(5, 4, TRUE);

INSERT INTO reservas (usuario_id, mesa_id) VALUES
(1, 1);