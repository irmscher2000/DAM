
-- Eugen Moga 
-- BDDA06 TAREA


-- 1º PARTE, EJERCICIOS BÁSICOS
-- Ejercicio 1: Procedimiento - Insertar una nueva pelicula
CREATE OR REPLACE PROCEDURE InsertarPelicula(p_titulo VARCHAR2, p_duracion NUMBER, p_calificacion NUMBER)
AS 
    nuevo_id NUMBER;
    v_calificacion NUMBER(3,1);
    
BEGIN
    -- Verifico que el titulo no este vacio o solo tenga espacios en blanco
    IF p_titulo IS NULL OR TRIM(p_titulo) = '' THEN
        RAISE_APPLICATION_ERROR(-20001, 'El titulo de la pelicula no puede estar vacio.');
    END IF;
    
    -- Verifico que la duracion sea mayor a 0
    IF p_duracion <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'La duracion de la pelicula debe ser mayor a 0.');
    END IF;
        
    -- Asigno p_calificacion a v_calificacion
    v_calificacion := p_calificacion;
        
    -- Obtengo el nuevo ID de pelicula
    SELECT nvl(MAX(id_pelicula), 0) + 1 INTO nuevo_id FROM peliculas;
    
    -- Insertar pelicula en la tabla
    INSERT INTO peliculas (id_pelicula, titulo, genero, duracion, calificacion)
    VALUES (nuevo_id, p_titulo, 'Desconocido', p_duracion, v_calificacion);
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Pelicula insertada correctamente.');
END InsertarPelicula;
/
    
-- Inserto una pelicula
BEGIN
    InsertarPelicula('Pelicula1', 5, 8.5);
END;
/

-- En caso de error de conversion ORA 06502 ejecutar este ALTER SESION
SELECT * FROM NLS_SESSION_PARAMETERS WHERE PARAMETER = 'NLS_NUMERIC_CHARACTERS';
ALTER SESSION SET NLS_NUMERIC_CHARACTERS = '. '; 





-- Ejercicio 2: Procedimiento - Contar películas alquiladas por un usuario
CREATE OR REPLACE PROCEDURE ContarPeliculasAlquiladas(p_id_usuario NUMBER, p_total OUT NUMBER)
AS
BEGIN
    -- Cuento el numero de peliculas alquiladas por el usuario
    SELECT COUNT(*) INTO p_total
    FROM alquilada
    WHERE id_usuario = p_id_usuario;
    
    -- Muestro el mensaje
    DBMS_OUTPUT.PUT_LINE('El usuario ' || p_id_usuario || ' ha alquilado un total de ' || p_total || ' peliculas');
    
-- Capturo los errores
EXCEPTION
    WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    p_total := 0; -- En caso de error devuelve 0
END ContarPeliculasAlquiladas;
/

-- Pruebo el procedimiento    
DECLARE
    total NUMBER;
BEGIN
    ContarPeliculasAlquiladas(1, total);
END;





-- Ejercicio 3: Trigger - Evitar precios negativos en alquileres de películas
CREATE OR REPLACE TRIGGER EvitarPreciosNegativos
BEFORE INSERT OR UPDATE ON alquilada
FOR EACH ROW
BEGIN
    -- Si el precio es menor a 0 lazo el error
    IF :NEW.precio < 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'El precio de la pelicula no puede ser negativo.');
    END IF;
END EvitarPreciosNegativos;
/

-- Pruebo que funciona el disparador
DECLARE
    nuevo_id NUMBER;
BEGIN
    -- Obtengo el nuevo id_alquilada
    SELECT nvl(MAX(id_alquilada), 0) + 1 INTO nuevo_id  FROM alquilada;
    -- Registro un alquiler para comprobar que funciona 
    INSERT INTO alquilada(id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)
    VALUES(nuevo_id, 1, 1, TO_DATE('2025-03-18', 'YYYY-MM-DD'), TO_DATE('2025-03-21', 'YYYY-MM-DD'), 5.86);
END;
/





-- Ejercicio 4: Trigger - Registrar fecha de alquiler automaticamente
CREATE OR REPLACE TRIGGER AsignarFechaAlquiler
BEFORE INSERT ON alquilada
FOR EACH ROW
BEGIN
    -- Si la fecha de alquiler es null asigno la fecha actual
    IF :NEW.fecha_alquilada IS NULL THEN
        :NEW.fecha_alquilada := SYSDATE;
    END IF;
END AsignarFechaAlquiler;
/


-- Pruebo el disparador
DECLARE
    nuevo_id NUMBER;
BEGIN
    -- Obtengo el nuevo id_alquilada
    SELECT nvl(MAX(id_alquilada), 0) + 1 INTO nuevo_id FROM alquilada;
    -- Registro un alquiler para comprobar que funciona
    INSERT INTO alquilada(id_alquilada, id_usuario, id_pelicula, fecha_devolucion, precio)
    VALUES(nuevo_id, 2, 2, TO_DATE('2025-03-23', 'YYYY-MM-DD'), 6.99);
END;





-- 2º PARTE, EJERCICIOS COMPLEJOS
-- Ejercicio 5: Procedimiento para cambiar las películas de un usuario a otro
CREATE OR REPLACE PROCEDURE CambiarPeliculasUsuario(id_usuarioOrigen NUMBER, id_usuarioDestino NUMBER)
AS
    v_existe_usuario_origen NUMBER;
    v_existe_usuario_destino NUMBER;
    v_peliculas_cambiadas NUMBER;
    v_nombre_origen VARCHAR2(50);
    v_nombre_destino VARCHAR2(50);
BEGIN
    -- Verifico que el usuario origen existe
    SELECT COUNT(*) INTO v_existe_usuario_origen FROM usuarios WHERE id_usuario = id_usuarioOrigen;
    IF v_existe_usuario_origen = 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'El usuario origen no existe.');
    END IF;
    
    -- Verifico que el usuario destino existe 
    SELECT COUNT(*) INTO v_existe_usuario_destino FROM usuarios WHERE id_usuario = id_usuarioDestino;
    IF v_existe_usuario_destino = 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'El usuario de destino no existe');
    END IF;
    
    -- Verifico que los usuario no sean iguales 
    IF id_usuarioOrigen = id_usuarioDestino THEN
        RAISE_APPLICATION_ERROR(-20006, 'Error: el usuario de origen y de destino son iguales');
    END IF;
    
    -- Obtengo los nombres de los usuarios origen y destino
    SELECT nombre INTO v_nombre_origen FROM usuarios WHERE id_usuario = id_usuarioOrigen;
    SELECT nombre INTO v_nombre_destino FROM usuarios WHERE id_usuario = id_usuarioDestino;
    
    -- Cuento las peliculas que tiene alquiladas el usuario origen antes de transferirlas al usuario destino
    SELECT COUNT(*) INTO v_peliculas_cambiadas FROM alquilada WHERE id_usuario = id_usuarioOrigen;
    
    -- Actualizo las peliculas alquiladas del usuario origen al usuario destino
    UPDATE alquilada
    SET id_usuario = id_usuarioDestino
    WHERE id_usuario = id_usuarioOrigen;
    
    -- Muestro la cantidad de peliculas que se han cambidado
    DBMS_OUTPUT.PUT_LINE('Se han transferido ' || v_peliculas_cambiadas || ' peliculas de ' || v_nombre_origen || ' a ' || v_nombre_destino ||  ' . ');
    
    COMMIT;
END CambiarPeliculasUsuario;
/

-- Pruebo el procedimiento
BEGIN
    cambiarpeliculasusuario(1, 2);
END;





-- Ejercicio 6: Disparador para asegurar restricciones en la tabla Películas
CREATE OR REPLACE TRIGGER DisparadorValidarPeliculas
BEFORE INSERT OR UPDATE ON peliculas
FOR EACH ROW
BEGIN
    -- Verifico que el titulo no este vacio
    IF :NEW.titulo IS NULL THEN
        RAISE_APPLICATION_ERROR(-20007, 'El titulo de la pelicula no puede estar vacio. ');
    END IF;
    
    -- Verifico que la duracion sea mayor a 0
    IF :NEW.duracion <= 0 THEN
        RAISE_APPLICATION_ERROR(-20008, 'La duración de la película debe ser mayor que 0 minutos.' );
    END IF;
    
    -- Verifico que la calificacion esta entre 0 y 10
    IF :NEW.calificacion < 0 OR :NEW.calificacion > 10 THEN
        RAISE_APPLICATION_ERROR(-20009, 'La calificación debe estar entre 0 y 10.');
    END IF;
END;
/


-- Pruebo el disparador validar peliculas
DECLARE
    nuevo_id NUMBER;
BEGIN
    -- Obtengo el nuevo ID de pelicula
    SELECT nvl(MAX(id_pelicula), 0) + 1 INTO nuevo_id FROM peliculas;
    -- Inserto la pelicula
    INSERT INTO peliculas(id_pelicula, titulo, genero, duracion, calificacion)
    VALUES(nuevo_id, 'Hola', 'Desconocido', 60, 0.5);
END;
/





-- Ejercicio 7: Disparador para validar alquileres
CREATE OR REPLACE TRIGGER DisparadorValidarAlquileres
BEFORE INSERT OR UPDATE ON alquilada
FOR EACH ROW
DECLARE
    v_existe NUMBER;
BEGIN
    -- Verifico que la fecha de devolución sea posterior a la fecha de alquiler
    IF :NEW.fecha_devolucion <= :NEW.fecha_alquilada THEN
        RAISE_APPLICATION_ERROR(-20010, 'La fecha de devolución debe ser posterior a la fecha de alquiler.');
    END IF;
    
    -- Verifico que el precio de alquiler no puede ser menor a 0
    IF :NEW.precio < 0 THEN
        RAISE_APPLICATION_ERROR(-20011, 'El precio de alquiler no puede ser menor a 0. ');
    END IF;
    
    -- Verifico que el usuario no puede alquilar la misma pelicula dos veces sin haberla devuelto antes
    SELECT COUNT(*) INTO v_existe
    FROM alquilada
    WHERE id_usuario = :NEW.id_usuario
    AND id_pelicula = :NEW.id_pelicula
    AND fecha_devolucion > SYSDATE;
    
    IF v_existe > 0 THEN
        RAISE_APPLICATION_ERROR(-20012, 'El usuario ya tiene alquilada esta película, debes devolverla antes de poder alquilarla de nuevo.');
    END IF;
END;
/

-- Pruebo el disparador validar alquileres
DECLARE
    nuevo_id NUMBER;
BEGIN
    -- Obtengo el nuevo ID alquilada
    SELECT nvl(MAX(id_alquilada), 0) + 1 INTO nuevo_id FROM alquilada;
    -- Inserto un alquiler de un usuario que tiene una pelicula que todavia no ha devuelto
    INSERT INTO alquilada(id_alquilada, id_usuario, id_pelicula, fecha_alquilada, fecha_devolucion, precio)
    VALUES(nuevo_id, 2, 3, TO_DATE('2025-03-19', 'YYYY-MM-DD'), TO_DATE('2025-03-25', 'YYYY-MM-DD'), 2);
END;
/