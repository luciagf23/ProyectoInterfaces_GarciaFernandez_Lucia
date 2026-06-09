-- Base de datos limpia
DROP DATABASE IF EXISTS bdcirco_luciagarcia;
CREATE DATABASE bdcirco_luciagarcia;
USE bdcirco_luciagarcia;
USE bdtarea3ad;

-- Coordinadores
INSERT INTO persona (id, dtype, nombre, email, nacionalidad, senior, fecha_senior, apodo) VALUES 
(1, 'COORDINACION', 'Luis García', 'luis@circo.com', 'española', 1, '2020-01-15', NULL),
(2, 'COORDINACION', 'María López', 'maria@circo.com', 'española', 0, NULL, NULL);

INSERT INTO coordinacion (id, senior, fecha_senior) VALUES
(1, 1, '2020-01-15'),
(2, 0, NULL);

-- Artistas
INSERT INTO persona (id, dtype, nombre, email, nacionalidad, senior, fecha_senior, apodo) VALUES
(3, 'ARTISTA', 'Pedro Ruiz', 'pedro@circo.com', 'española', 0, NULL, 'El Gran Pedro'),
(4, 'ARTISTA', 'Ana Martín', 'ana@circo.com', 'francesa', 0, NULL, NULL),
(5, 'ARTISTA', 'Carlos Díaz', 'carlos@circo.com', 'italiana', 0, NULL, 'Carlitos'),
(6, 'ARTISTA', 'Sofia Torres', 'sofia@circo.com', 'española', 0, NULL, 'La Maga');

INSERT INTO artista (id, apodo) VALUES
(3, 'El Gran Pedro'),
(4, NULL),
(5, 'Carlitos'),
(6, 'La Maga');

-- Especialidades
INSERT INTO artista_especialidades (artista_id, especialidades) VALUES
(3, 'ACROBACIA'),
(3, 'EQUILIBRISMO'),
(4, 'HUMOR'),
(4, 'MAGIA'),
(5, 'MALABARISMO'),
(5, 'ACROBACIA'),
(6, 'MAGIA'),
(6, 'EQUILIBRISMO');

-- Credenciales
INSERT INTO credencial (username, password, rol, persona_id) VALUES
('luisgarcia', '1234', 'COORDINACION', 1),
('marialopez', '1234', 'COORDINACION', 2),
('pedroruiz', '1234', 'ARTISTA', 3),
('anamartin', '1234', 'ARTISTA', 4),
('carlosdiaz', '1234', 'ARTISTA', 5),
('sofiatorres', '1234', 'ARTISTA', 6);

-- Espectáculos
INSERT INTO espectaculo (nombre, fecha_inicio, fecha_fin, coordinador_id) VALUES
('El Gran Circo', '2026-06-01', '2026-08-31', 1),
('Noche Mágica', '2026-09-01', '2026-11-30', 2),
('Fantasía Circense', '2026-12-01', '2027-02-28', 1);

-- Números
INSERT INTO numero (nombre, duracion, orden, espectaculo_id) VALUES
('Acrobacias Aéreas', 15.0, 1, 1),
('El Mago Asombroso', 20.5, 2, 1),
('Equilibrio Extremo', 10.0, 3, 1),
('Humor en Escena', 12.5, 1, 2),
('Magia y Misterio', 18.0, 2, 2),
('Malabares de Fuego', 15.5, 3, 2),
('Vuelo Circense', 12.0, 1, 3),
('Ilusiones', 20.0, 2, 3),
('Gran Final', 25.0, 3, 3);

-- Artistas en números
INSERT INTO numero_artistas (numero_id, artistas_id) VALUES
(1, 3),
(1, 5),
(2, 4),
(2, 6),
(3, 3),
(4, 4),
(5, 4),
(5, 6),
(6, 5),
(7, 3),
(8, 6),
(9, 3),
(9, 5);