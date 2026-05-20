-- Crear tabla alumnos
CREATE TABLE alumnos (
 DNI VARCHAR(9) NOT NULL,
 Nombre VARCHAR(50) NOT NULL,
 Apellidos VARCHAR(70) NOT NULL,
 Direccion VARCHAR(100) NOT NULL,
 FechaNac DATE NOT NULL, PRIMARY KEY (DNI)
);

-- Agregar datos a la tabla alumnos
INSERT INTO alumnos VALUES
('12345678A', 'José Alberto', 'González Pérez', 'C/Albahaca, nº14, 1ºD', '1986-07-15'),
('23456789B', 'Almudena', 'Cantero Verdemar', 'Avd/ Profesor Alvarado, n27, 8ºA', '1988-11-04'),
('14785236D', 'Martín', 'Díaz Jiménez', 'C/Luis de Gongora, nº2.', '1987-03-09'),
('96385274F', 'Lucas', 'Buendia Portes', 'C/Pintor Sorolla, nº 16, 4ºB', '1988-07-10'),
('A1179411X', 'Eugen', 'Moga', 'C/Pintor Picasso, nº 16, 1ºB', '1990-12-02');

-- Crear tabla matricula
CREATE TABLE matriculas (
    DNI VARCHAR(9) NOT NULL,
    Asignatura VARCHAR(60) NOT NULL,
    CursoEscolar VARCHAR(5) NOT NULL,
    Creditos INT(2) NOT NULL,
    Calificacion INT,
    PRIMARY KEY (DNI, Asignatura, CursoEscolar),
    CONSTRAINT fk_matriculas_alumnos
        FOREIGN KEY (DNI) REFERENCES alumnos(DNI)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Agregar datos a la tabla matricula
INSERT INTO matriculas (DNI, Asignatura, CursoEscolar, Creditos, Calificacion) VALUES
('12345678A', 'Programación', '23/24', 8, 7),
('12345678A', 'Bases de Datos', '23/24', 6, 8),
('12345678A', 'Lenguajes de Marcas', '23/24', 4, 9),

('23456789B', 'Programación', '24/25', 8, 6),
('23456789B', 'Sistemas Informáticos', '25/26', 6, 7),
('23456789B', 'Entornos de Desarrollo', '24/25', 3, 8),

('14785236D', 'Bases de Datos', '23/24', 6, 5),
('14785236D', 'Programación', '23/24', 8, 6),

('96385274F', 'Lenguajes de Marcas', '23/24', 4, 10),
('96385274F', 'Entornos de Desarrollo', '23/24', 3, 9),

('A1179411X', 'Programación', '25/26', 8, 8),
('A1179411X', 'Bases de Datos', '25/26', 6, 9),
('A1179411X', 'Sistemas Informáticos', '25/26', 6, 7);