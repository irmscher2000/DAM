-- 1 A Eliminar columna sexo de tabla CICLISTA
ALTER TABLE CICLISTA                                                            -- Indica la tabla que va sufrir las modificaciones
DROP COLUMN sexo;

-- 1 B Cambiar el nombre de la tabla LLEVA por PREMIO
RENAME LLEVA TO PREMIO;

-- 1 C Los km de una etapa nu pueden ser superiores a 300km
ALTER TABLE ETAPA                                                               -- Indica la tabla que va sufrir las modificaciones
ADD CONSTRAINT CHK_KM_MAXIMO CHECK (km <= 300);                                 -- Agrega la restricción.

-- 1 D Crear nuevo atributo en la tabla ETAPA llamado altura.
ALTER TABLE ETAPA                                                               -- Indica la tabla que va sufrir las modificaciones
ADD altura number(6,0);                                                         -- Agrega el campo altura de tipo numerico que permite hasta 2 decimales

-- 1 E Elimina la restricción que pusiste al atributo peso de la tabla CICLISTA.
ALTER TABLE CICLISTA
DROP CONSTRAINT CHK_PESO;

-- 3 Creacion de usuarios
ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;
create user Director_Tour identified by 123456;

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;
create user Director_equipo identified by 123456;

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;
create user jueces identified by 123456;

-- Asigno privilegios para el usuario Director_Tour
GRANT UPDATE, DELETE ON ETAPA TO Director_Tour;                                 -- Concede permisos para modificar y borrar en la tabla ETAPA para el usuario Director_Tour
GRANT UPDATE, DELETE ON TRAMO TO Director_Tour;                                 -- Concede permisos para modificar y borrar en la tabla TRAMO para el usuario Director_Tour
GRANT UPDATE, DELETE ON MAILLOT TO Director_Tour;                               -- Concede permisos para modificar y borrar en la tabla MAILLOT para el usuario Director_Tour
GRANT UPDATE, DELETE ON PREMIO TO Director_Tour;                                -- Concede permisos para modificar y borrar en la tabla PREMIO para el usuario Director_Tour

-- Asigno privilegios para el Director_equipo
GRANT UPDATE, DELETE ON CICLISTA TO Director_equipo;                            -- Concede permisos para modificar y borrar en la tabla CICLISTA para el usuario Director_equipo
GRANT UPDATE, DELETE ON EQUIPO TO Director_equipo;                              -- Concede permisos para modificar y borrar en la tabla EQUIPO para el usuario Director_equipo

-- Asigno privilegios para el usuario jueces
GRANT INSERT, UPDATE, DELETE ON TRAMO TO jueces;                                -- Concede permisos para insertar, modificar y borrar en la tabla TRAMO para el usuario jueces
GRANT UPDATE ON ETAPA TO jueces;                                                -- Concede permisos para modificar datos en la tabla ETAPA para el usuario jueces 


-- 4 Creación de vista lider ETAPA
CREATE VIEW vista_lider_etapa AS                                                -- Crea la vista con el nombre vw_lider_etapa
SELECT                                                                          -- Selecciona los campos que vamos a mostrad en la vista
    p.num_etapa AS num_etapa,                                                   -- Selecciona el número de etapa desde la tabla Premio
    c.nombre AS nombre,                                                         -- Selecciona el nombre del ciclista desde la tabla Ciclista
    m.tipo AS tipo                                                              -- Seleccionar el tipo de maillot desde la tabla Maillot
FROM                                                                            -- Indica de que tablas provienen los datos 
    ETAPA e, CICLISTA c, MAILLOT m, PREMIO p                                    -- Especifica todas las tablas con sus alias
WHERE                                                                           -- Solo selecciona las filas que cumplen con las condiciones especificadas
    p.num_etapa = e.num_etapa                                                   -- Condición para unir Premio y Etapa por num_etapa
    AND p.cod_ciclista = c.cod_ciclista                                         -- Condición para unir Premio y Ciclista por codigo_ciclista
    AND p.cod_maillot = m.cod_maillot;                                          -- Condición para unir Premio y Maillot por codigo_maillot