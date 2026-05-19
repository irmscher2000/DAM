-- 1. Escribe una consulta SELECT que muestre todos los usuarios.
SELECT nombre 
FROM usuarios;


-- 2. Escribe una consulta que muestre los títulos de las películas y la duración en horas (duración / 60).
SELECT titulo, ROUND(duracion / 60, 2) AS "Duración (Horas)"   -- Divido la duración entre 60 minutos y lo redondeo a 2 decimales,
FROM peliculas;


-- 3. Muestra los géneros de películas únicos.
SELECT DISTINCT genero      -- Con DISTINCT selecciono todos los generos sin duplicarlos
FROM peliculas;


-- 4. Obtén todas las peliculas ordenadas por su calificación en orden ascendente.
SELECT titulo, calificacion
FROM peliculas
ORDER BY  calificacion ASC;


-- 5. Obtén todos los géneros de películas únicos ordenados en orden descendente.
SELECT DISTINCT genero
FROM peliculas
ORDER BY genero DESC;


-- 6. Encuentra todas las películas cuyo título comienza con la letra T.
SELECT titulo
FROM peliculas
WHERE titulo LIKE 'T%';


-- 7. Encuentra todas las películas cuyo título tiene exactamente 10 caracteres.
SELECT *
FROM peliculas
WHERE LENGTH(titulo) = 10;


-- 8. Encuentra las peliculas con duración mayor a 120 minutos.
SELECT *
FROM peliculas
WHERE duracion > 120;


-- 9. Encuentra las peliculas con una calificación entre 8.0 y 9.0
SELECT * 
FROM peliculas
WHERE calificacion BETWEEN 8.0 AND 9.0;


-- 10. Encuentra las peliculas alquiladas realizadas después del 1 de noviembre de 2023
SELECT peliculas.titulo, alquilada.fecha_alquilada
FROM peliculas
JOIN alquilada
ON peliculas.id_pelicula = alquilada.id_pelicula
WHERE alquilada.fecha_alquilada > '01-11-2023';


-- 11. Muestra el precio de las películas alquiladas aumentado un 10% y el titulo de la pelicula
SELECT (alquilada.precio * 1.1) AS Precio_Aumentado, peliculas.titulo 
FROM peliculas
JOIN alquilada
ON peliculas.id_pelicula = alquilada.id_pelicula;


-- 12. Muestra los bombres de los usuarios concatenados con su email.
SELECT nombre || ' - ' || email AS nombre_email     -- Utilizo || para concatenar el nombre y el email.
FROM usuarios;


-- 13. Encuentra las películas de género Drama o con calificación mayor a 9.0
SELECT *
FROM peliculas
WHERE genero = 'Drama' OR calificacion > 9.0;       
-- solo muestra de genero drama porque no hay peliculas con calificacion superior a 9


-- 14. Encuentra las películas alquiladas cuyo precio sea menor a 6 
-- y fecha de devolución antes del 5 de noviembre de 2023
SELECT peliculas.titulo, alquilada.precio, alquilada.fecha_devolucion
FROM peliculas
JOIN alquilada
ON peliculas.id_pelicula = alquilada.id_pelicula
WHERE alquilada.precio < 6 AND alquilada.fecha_devolucion < '05-11-2023';


-- 15. Muestra las peliculas con la duración redondeada al número entero más cercano.
SELECT titulo, ROUND(duracion) AS duracion_redondeada
FROM peliculas;


-- 16 Muestra las peliculas con la duracion truncada a la decena mas cercana.
SELECT titulo, TRUNC(duracion, -1) AS duracion_truncada
FROM peliculas;


-- 17. Muestra los nombres de los usuarios en mayúsculas.
SELECT UPPER(nombre)
FROM usuarios;


-- 18. Muestra la cantidad de días entre la fecha de películas alquilada y la fecha de devolución.
SELECT titulo, (alquilada.fecha_devolucion - alquilada.fecha_alquilada) AS dias_de_diferencia
FROM alquilada
JOIN peliculas
ON peliculas.id_pelicula = alquilada.id_pelicula;


--19 Muestra el numero total de películas alquiladas realizadas.
SELECT COUNT(id_alquilada) AS peliculas_alquiladas 
FROM alquilada;


-- 20. Encuentra la calificación máxima de las peliculas.
SELECT titulo, calificacion
FROM peliculas
WHERE calificacion = (SELECT MAX(calificacion) FROM peliculas);


-- 21. Muestra el número de peliculas por género.
SELECT genero, COUNT(*) AS numero_peliculas
FROM peliculas
GROUP BY genero;


-- 22. Muestra los géneros con más de 1 película.
SELECT genero, COUNT(*) AS numero_peliculas
FROM peliculas
GROUP BY genero
HAVING COUNT(*) > 1;
-- Con HAVING COUNT(*) filtra aquellos generos que tienen mas de una película


-- 23. Muestra las películas alquiladas junto con los nombres de los usuarios y los títulos de las películas.
SELECT a.id_alquilada, u.nombre AS nombre_usuario, p.titulo AS titulo_pelicula
FROM alquilada a
JOIN peliculas p ON a.id_pelicula = p.id_pelicula
JOIN usuarios u ON a.id_usuario = u.id_usuario;


-- 24. Muestra las peliculas alquiladas con los títulos de las películas usando LEFT JOIN.
SELECT a.id_alquilada, p.titulo
FROM alquilada a LEFT JOIN peliculas p ON a.id_pelicula = p.id_pelicula;


-- 25. Muestra los usuarios con sus alquiladas usando RIGHT JOIN.
SELECT u.nombre, id_alquilada
FROM alquilada a RIGHT JOIN usuarios u ON a.id_usuario = u.id_usuario;
-- En teoria lo que pide el enunciado es el usuario con sus alquiladas, pero viendo solo el id no es muy visual.


-- 25. Muestra los usuarios con sus alquiladas usando RIGHT JOIN.
SELECT u.nombre, p.titulo AS pelicula_alquilada
FROM alquilada a RIGHT JOIN usuarios u ON a.id_usuario = u.id_usuario
                 LEFT JOIN peliculas p ON a.id_pelicula = p.id_pelicula;
-- Esto queda mas visual pero tambien hace referencia a la tabla peliculas de la izquierda para obtener el titulo


-- 26. Muestra los títulos de las películas y el numero de veces que han sido alquiladas.
SELECT p.titulo, COUNT(a.id_pelicula) AS num_veces_alquilada
FROM alquilada a LEFT JOIN peliculas p ON a.id_pelicula = p.id_pelicula
GROUP BY p.titulo;
-- Cada pelicula solo ha sido alquilada una vez. 


-- 27. Muestra los usuarios y sus gastos totales en películas alquiladas.
SELECT u.nombre, SUM(a.precio) AS gasto_total
FROM alquilada a RIGHT JOIN usuarios u ON a.id_usuario = u.id_usuario
GROUP BY u.nombre;


-- 28. Encuentra las peliculas alquiladas en noviembre de 2023.
SELECT p.titulo, a.fecha_alquilada
FROM alquilada a
JOIN peliculas p ON a.id_pelicula = p.id_pelicula
WHERE EXTRACT(MONTH FROM a.fecha_alquilada) = 11 AND EXTRACT(YEAR FROM a.fecha_alquilada) = 2023
ORDER BY a.fecha_alquilada;


-- 29. Encuentra los usuarios que han alquilado películas de más de 2 horas de duración.
SELECT u.nombre, p.titulo, p.duracion
FROM alquilada a LEFT JOIN peliculas p ON a.id_pelicula = p.id_pelicula
                 RIGHT JOIN usuarios u ON a.id_usuario = u.id_usuario
WHERE p.duracion > 120;


-- 30. Encuentra las películas que tienen una calificación mayor que el promedio de todas las calificaciones
SELECT titulo, calificacion
FROM peliculas
WHERE calificacion > (SELECT AVG(calificacion) FROM peliculas);


-- 31. Encuentra los géneros con calificación promedio mayor a 8.5 y el numero total de películas en ese género.
SELECT genero, 
        ROUND(AVG(calificacion), 2) AS calificacion_media,      -- Con AVG calculo la media de las calificaciones, ROUND redondea esa media a 2 decimales
        COUNT(*) AS total_peliculas         -- Count * cuenta todas las peliculas.
FROM peliculas
GROUP BY genero     -- Agrupa las peliculas contadas por genero, 
HAVING AVG(calificacion) > 8.5;      -- Filtra y muestra solo las calificaciones de mas de 8.5    


-- 32 Encuentra el usuario que más dinero ha gastado en películas alquiladas.
SELECT u.nombre, SUM(a.precio) AS gasto_total       -- Selecciono el nombre y con SUM sumo todos los campos de precio.
FROM alquilada a  JOIN usuarios u ON a.id_usuario = u.id_usuario        -- Hago la conexion de las tablas
GROUP BY u.nombre       -- Agrupo la suma por el campo nombre, asi muestra los gastos de cada usuario.
ORDER BY gasto_total DESC       -- Lo ordeno en oreden descendente. 
FETCH FIRST 1 ROW ONLY;         -- Solo muestro la primera fila.
-- Es la consulta 27 y he le he añadido las ultimas 2 lineas. 


-- 33. Encuentra las películas más largas de cada género.
SELECT titulo, genero, duracion
FROM peliculas p
WHERE duracion = (SELECT MAX(duracion) FROM peliculas WHERE genero = p.genero);


-- 34. Encuentra los usuarios que han alquilado más de una vez.
SELECT u.nombre, COUNT(a.id_alquilada) AS cantidad_alquileres       -- Selecciono nombre de usuario y con COUNT cuento los id de alquileres 
FROM alquilada a
JOIN usuarios u ON a.id_usuario = u.id_usuario          -- Relaciono las tablas usuarios y alquilada con sus claves 
GROUP BY u.nombre                                       -- Agrupo los alquileres por nombre de usuario
HAVING COUNT(a.id_alquilada) > 1;                       -- Muestro solo los que han alquilado mas de una vez


-- 35. Encuentra la calificación promedio de las películas alquiladas por cada usuario.
SELECT u.nombre, AVG(p.calificacion) AS calificacion_media      -- Selecciono el nombre de usuario y con AVG calculo la media de las peliculas alquiladas por usuario.
FROM alquilada a                                                -- De la tabla alquilada.
JOIN usuarios u ON a.id_usuario = u.id_usuario                  -- Relaciono la tabla usuario con la tabla alquilada.
JOIN peliculas p ON a.id_pelicula = p.id_pelicula               -- Relaciono la tabla peliculas con la tabla alquilada.
GROUP BY u.nombre;                                              -- Agrupo los resultados por el nombre de usuario.


-- 36. Encuentra las películas alquiladas únicamente en las fechas que coinciden exactamente con la fecha de registro de algún usuario.
SELECT DISTINCT p.titulo, a.fecha_alquilada                 -- Selecciono titulo y fecha alquilada.
FROM alquilada a                                            -- De la tabla alquilada.
JOIN usuarios u ON a.fecha_alquilada = u.fecha_registro     -- Relaciono la fecha de alquiler de la tabla alquilada con la fecha de registro de la tabla usuarios.
JOIN peliculas p ON a.id_pelicula = p.id_pelicula;          -- Relaciono la tabla peliculas con la tabla alquilada.
-- Esta consulta no muestra nada porque los alquileres se realizaron en noviembre de 2023 y los registros de los usuario se realizaron en los meses anteriores.