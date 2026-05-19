-- Inserciones.
-- 1. Insertar una nueva película en la tabla Películas.
INSERT INTO peliculas (id_pelicula, titulo, genero, duracion, calificacion)
VALUES (14, 'Gladiador', 'Acción', 160, 9.6);


-- 2. Insertar un nuevo alquiler para un usuario específico y una película específica.
INSERT INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)
VALUES (11, 1, 14, TO_DATE('2025/02/12', 'YYYY/MM/DD'), TO_DATE('2025/02/15', 'YYYY/MM/DD'), 6.95);


-- 3. Insertar múltiples registros en la tabla Alquilada de una sola vez.
INSERT ALL                                                                                              -- Utilizo INSERT ALL para insertar multiples registros en una sola consulta
    INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)            
    VALUES  (12, 2, 6, TO_DATE('2025/02/01', 'YYYY/MM/DD'), TO_DATE('2025/02/05', 'YYYY/MM/DD'), 9.99)
    
    INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)
    VALUES (13, 3, 7, TO_DATE('2025/02/02', 'YYYY/MM/DD'), TO_DATE('2025/02/06', 'YYYY/MM/DD'), 8.75)
    
    INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)            
    VALUES  (14, 4, 8, TO_DATE('2025/02/03', 'YYYY/MM/DD'), TO_DATE('2025/02/07', 'YYYY/MM/DD'), 7.95)
SELECT 1 FROM DUAL;                                                                                       -- Uso DUAL para ejecutar la inserción una sola vez.


-- 4. Insertar un nuevo alquiler con información obtenida de otras tablas (Usuarios y Películas).
INSERT INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)    
VALUES (
    15,
    (SELECT id_usuario FROM usuarios WHERE nombre = 'Juan Pérez'),      -- Selecciono el usuario Juan Perez de la tabla usuarios
    (SELECT id_pelicula FROM peliculas WHERE titulo = 'Interestelar'),  -- Selecciono la perlicula Interestelar de la tabla peliculas 
    TO_DATE('2025/02/03', 'YYYY/MM/DD'), 
    TO_DATE('2025/02/09', 'YYYY/MM/DD'),
    7.99
    );


-- 5. Insertar un alquiler para un usuario con el género de película ‘Animación’ seleccionando el primer resultado
INSERT INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)    
SELECT
    16,
    (SELECT id_usuario FROM usuarios WHERE nombre = 'Ana Gómez'),      -- Selecciono el usuario Ana Gómez de la tabla usuarios
    (SELECT id_pelicula FROM peliculas WHERE peliculas.genero = 'Animación' AND ROWNUM = 1),  -- Selecciono la pelicula de genero Animacion que es el primer resultado del genero Animación
    TO_DATE('2025/02/04', 'YYYY/MM/DD'), 
    TO_DATE('2025/02/10', 'YYYY/MM/DD'),
    6.99
FROM dual;


-- 6. Insertar un alquiler para ‘Ana Gómez’ con una película cuya calificación sea mayor a 8.5
INSERT INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)   
VALUES (
    17,     
    (SELECT id_usuario FROM usuarios WHERE nombre = 'Ana Gómez'),      -- Selecciono el usuario Ana Gómez de la tabla usuarios
    (SELECT id_pelicula FROM peliculas WHERE calificacion > 8.5 AND ROWNUM = 1),  -- Selecciono la primera pelicula cuya calificacion sea mayor a 8.5 
    TO_DATE('2025/02/05', 'YYYY/MM/DD'), 
    TO_DATE('2025/02/11', 'YYYY/MM/DD'),
    8.99
);


-- 7. Insertar un alquiler para una película específica obteniendo el usuario desde la base de datos.
INSERT INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)   
VALUES (
    18,     
    (SELECT id_usuario FROM usuarios WHERE nombre = 'Carlos Ruiz'),      -- Selecciono el usuario Carlos Ruiz de la tabla usuarios
    (SELECT id_pelicula FROM peliculas WHERE peliculas.titulo = 'Coco'),  
    TO_DATE('2025/02/06', 'YYYY/MM/DD'), 
    TO_DATE('2025/02/12', 'YYYY/MM/DD'),
    4.99
);


-- Modificaciones:
-- 1. Actualizar la calificación de una película específica.
UPDATE peliculas 
SET calificacion = 9.9
WHERE titulo = 'Origen';


-- 2. Modificar la fecha de devolución de un alquiler específico.
UPDATE alquilada 
SET fecha_devolucion = TO_DATE('2025/02/12', 'YYYY/MM/DD')
WHERE id_alquilada = 1;   


-- 3. Incrementar el precio de todos los alquileres en un 10%.
UPDATE alquilada 
SET precio = precio * 1.10;


-- 4. Aumentar el precio de alquileres de películas de ‘Ciencia Ficción’ en un 15%.
UPDATE alquilada                                    -- Indico que voy a actualizar la tabla alquilada
SET precio = precio * 1.15                          -- Actualizo la columna precio multiplicando su valor por 1.15 para aumentar un 15%
WHERE id_pelicula IN (                              -- Condicion para actualizar solo aquellos cuyo id_pelicula este relacionado con Ciencia Ficción
    SELECT id_pelicula                              
    FROM peliculas
    WHERE peliculas.genero = 'Ciencia Ficción'
);


-- 5. Modificar el email de los usuarios que han alquilado películas con precio superior a 7.00, agregando el prefijo ‘vip_’.
UPDATE usuarios                                 -- Actualizo la tabla usuarios
SET email = 'vip_' || email                     -- Concateno el prefijo vip_ al valor actual de email
WHERE id_usuario IN (                           -- Condicion para filtrar usuarios que han alquilado peliculas con precio superior a 7 
    SELECT DISTINCT alquilada.id_usuario        -- Asegura que no se dupliquen los los id_usuario que hayan alquilado mas peliculas con precio superior a 7    
    FROM alquilada
    WHERE alquilada.precio > 7
);


-- 6. Incrementar la calificación de todas las películas que hayan sido alquiladas en diciembre de 2023 en 0.3 puntos.
UPDATE peliculas                                -- Actualizo la tabla peliculas
SET calificacion = calificacion + 0.3           -- Sumo 0.3 a la columna calificación
WHERE id_pelicula IN (                          -- Filtro las peliculas que se han alquilado en diciembre del 2023
    SELECT id_pelicula                          -- Subconsulta que selecciona id_pelicula de la tabla alquilada y comprueba la fecha alquilada
    FROM alquilada
    WHERE alquilada.fecha_alquilada BETWEEN TO_DATE('2023-12-01', 'YYYY-MM-DD')
                                        AND TO_DATE('2023-12-31', 'YYYY-MM-DD')
);


-- 7. Modificar la fecha de alquiler para los registros con fecha de devolución entre el 1 y el 10 de diciembre de 2023.
UPDATE alquilada                                                    -- Actualizo la tabla alquilada
SET fecha_alquilada = fecha_alquilada - 3                           -- Resto 3 dias a la fecha de alquiler
WHERE fecha_devolucion BETWEEN TO_DATE('2023-12-01', 'YYYY-MM-DD')  -- Cuando se cumple que la fecha de devolucion se encuentra entre el 1 y el 10 de diciembre
                           AND TO_DATE('2023-12-10', 'YYYY-MM-DD');


-- Borrado:
-- 1. Eliminar una película específica de la tabla Películas.
DELETE FROM alquilada                   -- Primero elimino los alquileres de la tabla que estan relacionados con la pelicula Origen
WHERE id_pelicula = (SELECT id_pelicula FROM peliculas WHERE peliculas.titulo = 'Origen');

DELETE FROM peliculas           -- Hay que ejecutar primero la sentencia de encima para eliminar las relaciones que tiene con la tabla alquilada.
WHERE titulo = 'Origen';
-- Despues de eliminar todos los alquileres relacionados con la pelicula se puede eliminar la pelicula Origen


-- 2. Eliminar un alquiler específico de la tabla Alquilada.
DELETE FROM alquilada
WHERE id_alquilada = 2;


-- 3. Borrar todos los alquileres cuyo precio sea menor a 6.00.
DELETE FROM alquilada
WHERE alquilada.precio < 6;


-- 4. Eliminar alquileres de usuarios cuyo email comience con ‘vip_’
DELETE FROM alquilada                       -- Indico que voy a eliminar registros de la tabla alquilada
WHERE id_usuario IN (                       -- Con where filtro los registros de la tabla alquilada     
    SELECT  id_usuario                      -- Que pertenecen a usuario cuyo email empieza por vip_
    FROM usuarios
    WHERE usuarios.email LIKE 'vip_%'
);


-- 5. Borrar alquileres de películas del género ‘Animación’ cuya calificación sea menor a 8.5.
DELETE FROM alquilada                       -- Indico que voy a eliminar registros de la tabla alquilada
WHERE id_pelicula IN (                       -- Con where filtro los registros de alquilada que pertenecen a peliculas    
    SELECT  id_pelicula                      -- Con genero Animación y calificacion menor a 8.5
    FROM peliculas
    WHERE peliculas.genero = 'Animación'
      AND peliculas.calificacion < 8.5
);


-- 6. Eliminar los usuarios que no hayan realizado ningún alquiler
DELETE FROM usuarios                        -- Indico que voy a eliminar registros de la tabla usuarios
WHERE id_usuario NOT IN (                   -- Con where filtro los usuario que no estan en la tabla alquilada
    SELECT DISTINCT id_usuario               
    FROM alquilada
);


-- 7. Eliminar alquileres de la película ‘Pulp Fiction’.
DELETE FROM alquilada 
WHERE id_pelicula IN  (
    SELECT id_pelicula
    FROM peliculas
    WHERE peliculas.titulo = 'Pulp Fiction'
);


-- Restricciones de FK (NO permitir eliminación/modificación si hay referencias):
-- 1. Evitar la eliminación de un usuario si tiene registros en Alquilada.
-- Tal y como esta creada la tabla usuarios con clave foranea en alquilada, 
-- no hay que hacer cambios ya que por defecto se aplica ON DELETE RESTRICT   
-- y no permite la eliminación de usuarios que tiene referencias en alquilada



-- 2. Impedir la eliminación de una película si está alquilada.
-- Tal y como esta creada la tabla películas con clave foranea en alquilada, 
-- no hay que hacer cambios ya que por defecto se aplica ON DELETE RESTRICT   
-- y no permite la eliminación de películas que tiene referencias en alquilada


-- Eliminación/modificación en cascada:
-- 1. Permitir la eliminación de un usuario y que sus alquileres se eliminen en cascada.
-- Con esta sentecia obtengo el nombre de la restricción SYS_C008425 para poder eliminarla.
-- Primero obtengo el nombre de la restriccion actual SYS_C008425 
-- Esta sentencia la dejo como referencia de como he obtenido el nombre de la restriccion
-- Esto tambien se puede obtener de forma grafica en la tabla alquilada pestaña restricciones
SELECT uc.constraint_name, ucc.column_name
FROM user_constraints uc
JOIN user_cons_columns ucc 
ON uc.constraint_name = ucc.constraint_name
WHERE uc.table_name = 'ALQUILADA' AND uc.constraint_type = 'R';

ALTER TABLE alquilada 
DROP CONSTRAINT SYS_C008425;                    -- Elimino la restriccion actual 

ALTER TABLE alquilada                           -- Modifico la tabla y agrego la nueva restriccion 
ADD CONSTRAINT fk_alquilada_usuario
FOREIGN KEY (id_usuario)
REFERENCES usuarios(id_usuario)
ON DELETE CASCADE;                              -- Especifico que se elimine en cascada. 


-- 2. Permitir la eliminación de una película y que sus alquileres se eliminen en cascada.
-- Igual que en el caso anterior utilizo esta sentencia para encontrar el nombre de la restriccion y poder borrarla 
-- Esto tambien se puede obtener de forma grafica en la tabla alquilada pestaña restricciones
SELECT uc.constraint_name, ucc.column_name 
FROM user_constraints uc
JOIN user_cons_columns ucc 
ON uc.constraint_name = ucc.constraint_name
WHERE uc.table_name = 'ALQUILADA' AND uc.constraint_type = 'R';

ALTER TABLE alquilada                   -- Elimino la restriccion actual 
DROP CONSTRAINT SYS_C008426;

ALTER TABLE alquilada                   -- Agrego la nueva restriccion que permite la eliminacion en cascada
ADD CONSTRAINT fk_alquilada_pelicula
FOREIGN KEY (id_pelicula)
REFERENCES peliculas(id_pelicula)
ON DELETE CASCADE;


-- Asignación de valor NULL en caso de eliminación:
-- 1. Si un usuario es eliminado, asignar NULL en Alquilada en lugar de eliminar el registro
-- Al hacer el ejercicio anterio ahora se como se llaman las restricciones paso a eliminarlas directamente
ALTER TABLE alquilada
DROP CONSTRAINT fk_alquilada_usuario;

ALTER TABLE alquilada
ADD CONSTRAINT fk_alquilada_usuario
FOREIGN KEY (id_usuario)
REFERENCES usuarios(id_usuario)
ON DELETE SET NULL;


-- Asignación de valor NULL en caso de eliminación:
-- 1. Si un usuario es eliminado, asignar NULL en Alquilada en lugar de eliminar el registro
-- Al hacer el ejercicio anterio ahora se como se llaman las restricciones paso a eliminarlas directamente
ALTER TABLE alquilada
DROP CONSTRAINT fk_alquilada_usuario;

ALTER TABLE alquilada
ADD CONSTRAINT fk_alquilada_usuario
FOREIGN KEY (id_usuario)
REFERENCES usuarios(id_usuario)
ON DELETE SET NULL;


-- 2. Si una película es eliminada, asignar NULL en Alquilada en lugar de eliminar el registro
-- Elimino la restricción directamente porque ya me se el nombre
ALTER TABLE alquilada
DROP CONSTRAINT fk_alquilada_pelicula;

ALTER TABLE alquilada
ADD CONSTRAINT fk_alquilada_pelicula
FOREIGN KEY (id_pelicula)
REFERENCES peliculas(id_pelicula)
ON DELETE SET NULL;


-- Transacciones:
-- 1. Insertar un nuevo alquiler y confirmar los cambios con COMMIT
INSERT INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)
VALUES (21, 5, 10, TO_DATE('2025/02/13', 'YYYY/MM/DD'), TO_DATE('2025/02/16', 'YYYY/MM/DD'), 9.95);
-- Confirmar los cambios
COMMIT;


-- 2. Insertar un nuevo alquiler y deshacer los cambios con ROLLBACK
INSERT INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)
VALUES (22, 1, 3, TO_DATE('2025/02/13', 'YYYY/MM/DD'), TO_DATE('2025/02/19', 'YYYY/MM/DD'), 11.95);
-- Deshacer cambios
ROLLBACK;


-- 3. Insertar un nuevo alquiler, crear un SAVEPOINT, eliminarlo y deshacer solo esa eliminación.
INSERT INTO alquilada (id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)
VALUES (22, 1, 3, TO_DATE('2025/02/13', 'YYYY/MM/DD'), TO_DATE('2025/02/19', 'YYYY/MM/DD'), 11.95);

-- Creo SAVEPOINT punto1
SAVEPOINT punto1;

-- Elimino el alquiler que acabo de insertar
DELETE FROM alquilada
WHERE id_alquilada = 22;

-- Vuelvo a restaurar el SAVEPOINT
ROLLBACK TO SAVEPOINT punto1;

-- Confirmo los cambios
COMMIT;