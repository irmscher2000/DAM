// Eugen Moga
// DWEC Tarea 4

// Arrays paralelos
let nombres = [];
let edades = [];
let cargos = [];

// Array multidimensional para almacenar las notas de cada estudiante
// Estructura: [[nota1, nota2, nota3], [nota1, nota2, nota3], ...]
let notas = [];

// Array de objetos para almacenar información completa de estudiantes 
let alumnado = [];

/**
 * VARIABLES GLOBALES  REFERENCIANDO ELEMENTOS DEL DOM
 */
let formulario;
let cargoSeleccionado;
let bloqueNotas;            // Div que contiene los campos de notas
let nombreInput;
let edadInput;
let resultadoTextarea;      // Área de texto para mostrar resultados


/**
 * INICIALIZACION DE LA APLICACION Y GESTION DEL FORMULARIO
 *
 */

/**
 * Event Listener principal que se ejecuta cuando el DOM está completamente cargado.
 * Inicializa todas las referencias al DOM y configura los event listeners.
 */
document.addEventListener('DOMContentLoaded', function(){
    // Obtener referencias a los elementos del DOM
    formulario = document.getElementById('form-general');
    cargoSeleccionado = document.getElementById('cargo');
    bloqueNotas = document.getElementById('bloque-notas');
    nombreInput = document.getElementById('nombre');
    edadInput = document.getElementById('edad');
    resultadoTextarea = document.getElementById('resultado');

    // Listener para el envío del formulario
    formulario.addEventListener('submit', manejarEnvioFormulario);

    // Listener para mostrar/ocultar el bloque de notas según el cargo seleccionado
    cargoSeleccionado.addEventListener('change', function(){
        if (cargoSeleccionado.value === "1"){  // Estudiante
            bloqueNotas.style.display = 'block';
        } else {  // Profesor
            bloqueNotas.style.display = 'none';
        }
    });

    // Inicializar estado del formulario según el cargo seleccionado al cargar la página
    if (cargoSeleccionado.value === "1"){
        bloqueNotas.style.display = 'block';
    }else {
        bloqueNotas.style.display = 'none';
    }

    // Limpiar y preparar el formulario para el primer uso
    limpiarFormulario();

});


/*
    FUNCIONES DE PROCESAMIENTO DEL FORMULARIO
*/

/**
 * Maneja el envío del formulario principal.
 * Valida los datos, procesa según el tipo de cargo (Estudiante/Profesor),
 * crea objetos de alumnos y actualiza el array alumnado.
 * 
 * @param {Event} evento - Evento de submit del formulario
 */
function manejarEnvioFormulario(evento){
    evento.preventDefault();            // Evita el envio tradicional del formulario

    // Obtener valores del formulario
    let nombre = nombreInput.value.trim();
    let edad = parseInt(edadInput.value);
    let cargo = cargoSeleccionado.value;

    // Validar campo nombre
    if (!nombre || nombre === ''){
        alert('Por favor, introduce un nombre válido.');
        nombreInput.focus();
        return;
    }

    // Validar campo edad
    if (isNaN(edad) || edad <= 0 || edad > 120){
        alert('Por favor, introduce una edad válida mayor que 0 y menor o igual a 120.');
        edadInput.focus();
        return;
    }

    // Procesar segun el cargo
    if (cargo === "1"){ //Estudiante
        let exito = procesarEstudiante(nombre, edad);
        if (!exito) return;
    }else {             // Profesor
        procesarProfesor(nombre, edad);
    }

    // Mostrar confirmacion del registro.
    let tipoPersona = (cargo === "1") ? 'Estudiante' : 'Profesor/a';
    resultadoTextarea.value = `Registrado correctamente el ${tipoPersona} con nombre ${nombre}`;

    /*
       EJERCICIO 4 CREACION DE OBJETOS
    */
   // Convertir cada alumno en un objeto y se muestra en console.log
    actualizarAlumnado();

    // Limpiar formulario para nuevo registro
    limpiarFormulario();
}

/**
 * Procesa y valida los datos de un estudiante.
 * Captura las notas de las tres asignaturas, las valida y almacena
 * toda la información en los arrays paralelos.
 * 
 * @param {string} nombre - Nombre del estudiante
 * @param {number} edad - Edad del estudiante
 */
function procesarEstudiante(nombre, edad){
    // Obtener notas con querySelector
    let notaMat = document.querySelector('#nota-mat');
    let notaLen = document.querySelector('#nota-len');
    let notaHis = document.querySelector('#nota-his');

    // Convertir valores a numeros
    let notaMatNum = parseFloat(notaMat.value);
    let notaLenNum = parseFloat(notaLen.value);
    let notaHisNum = parseFloat(notaHis.value);

    // Validar notas
    if (isNaN(notaMatNum) || isNaN(notaLenNum) || isNaN(notaHisNum) ||
        notaMatNum < 0 || notaMatNum > 10 ||
        notaLenNum < 0 || notaLenNum > 10 ||
        notaHisNum < 0 || notaHisNum > 10){
            alert('Por favor, introduce notas válidas entre 0 y 10 para todas las asignaturas.');
            notaMat.focus();
            return false;
    }

    // Almacenar datos en los arrays paralelos
    nombres.push(nombre);
    edades.push(edad);
    cargos.push("1"); // 1 para estudiante
    notas.push([notaMatNum, notaLenNum, notaHisNum]);
    return true;
}

/**
 * Procesa y almacena los datos de un profesor.
 * Los profesores no tienen notas asociadas.
 * 
 * @param {string} nombre - Nombre del profesor
 * @param {number} edad - Edad del profesor
 */
function procesarProfesor(nombre, edad){
    // Almacenar datos en los arrays
    nombres.push(nombre);
    edades.push(edad);
    cargos.push("2"); // 2 para profesor
    notas.push([]); // No hay notas para profesores
}

/* 
     FUNCIONES AUXILIARES
*/
/**
 * Limpia todos los campos del formulario y lo resetea al estado inicial.
 * Pone el foco en el campo de nombre para facilitar un nuevo registro.
 */
function limpiarFormulario(){
    nombreInput.value = '';
    edadInput.value = '';

    // Restablecer cargo a estudiante
    cargoSeleccionado.value = "1";
    bloqueNotas.style.display = 'block';

    // Limpiar notas
    document.querySelector('#nota-mat').value = '';
    document.querySelector('#nota-len').value = '';
    document.querySelector('#nota-his').value = '';

    // Poner foco en el primer campo
    nombreInput.focus();
}

/**
 * Función auxiliar que filtra y retorna solo los datos de estudiantes.
 * Separa estudiantes de profesores basándose en el array de cargos.
 * 
 * @returns {Object} Objeto con tres arrays: nombres, edades y notas de estudiantes
 */
function obtenerEstudiantes(){
    // Crear objeto para almacenar datos de estudiantes
    let estudiantes = {
        nombres: [],
        edades: [],
        notas: []
    };

    // Recorrer todos los registros
    for (let i = 0; i < cargos.length; i++){
        if (cargos[i] === "1"){ // Si es estudiante
            estudiantes.nombres.push(nombres[i]);
            estudiantes.edades.push(edades[i]);
            estudiantes.notas.push(notas[i]);
        }
    }
    return estudiantes;
}

/**
 * Verifica si hay estudiantes registrados y muestra un mensaje si está vacío.
 * Esta función evita repetir código de validación en todas las funciones de consulta.
 * 
 * @returns {boolean} true si hay estudiantes, false si el array está vacío
 */
function verificarEstudiantes(){
    let estudiantes = obtenerEstudiantes();

    // Verificar si hay estudiantes registrados
    if (estudiantes.nombres.length === 0){
        resultadoTextarea.value = 'No hay alumnos registrados.';
        return false;
    }
    return true;
}

/**
 * Calcula la media de un estudiante dadas sus tres notas.
 * Función reutilizable para evitar repetir el cálculo en múltiples lugares.
 * 
 * @param {Array} notasAlumno - Array con las tres notas [mat, len, his]
 * @returns {number} Media calculada (sin redondear)
 */
function calcularMedia(notasAlumno){
    let suma = notasAlumno[0] + notasAlumno[1] + notasAlumno[2];
    return suma / 3;
}

/* 
   EJERCICIO 3: FUNCIONES PARA TRABAJAR CON LOS DATOS
*/

// Funcion para mostrar alumnos
function mostrarAlumnos(nombres, edades){
    // Verificar si hay estudiantes registrados
    if (!verificarEstudiantes()) return;

    // Obtener solo datos de estudiantes
    let estudiantes = obtenerEstudiantes();

    let texto = '';

    // Ver alumnos registrados
    for (let i = 0; i < estudiantes.nombres.length; i++){
        let numero = i + 1;
        let nombre = estudiantes.nombres[i];
        let edad = estudiantes.edades[i];

        // Construir linea de resultado
        let linea = `${numero} - Nombre: ${nombre} | Edad: ${edad}\n`;

        // Acumular texto
        texto += linea;
    }    
    // Mostrar resultado en el textarea de una vez limpiando contenido previo
    resultadoTextarea.value = texto;
}   



/**
 * Muestra las notas de todas las asignaturas de cada estudiante registrado.
 * Formato: Nombre del alumno seguido de sus tres notas.
 * 
 * @param {Array} nombres - Array de nombres (parámetro por compatibilidad)
 * @param {Array} notas - Array de notas (parámetro por compatibilidad)
 */
function mostrarNotas(nombres, notas){

    // Verificar si hay estudiantes registrados funcion reutilizable
    if (!verificarEstudiantes()) return;

    // Obtener solo datos de estudiantes
    let estudiantes = obtenerEstudiantes();

    let texto = '';

    // Ver notas de los estudiantes registrados
    for (let i = 0; i < estudiantes.nombres.length; i++){
        let numero = i + 1;
        let nombre = estudiantes.nombres[i];
        let notaMat = estudiantes.notas[i][0];
        let notaLen = estudiantes.notas[i][1];
        let notaHis = estudiantes.notas[i][2];

        // Construir linea de resultado
        let linea = `${numero} - Nombre: ${nombre} | Nota Mat: ${notaMat} | Nota Len: ${notaLen} | Nota His: ${notaHis}\n`;

        // Acumular texto
        texto += linea;
    }    
    // Mostrar resultado en el textarea de una vez limpiando contenido previo
    resultadoTextarea.value = texto;
}


/**
 * Función para calcular la media de cada alumno registrado
 * @param {Array} nombres - Array con los nombres de los alumnos
 * @param {Array} notas - Array multidimensional con las notas
 * Muestra en el textarea el nombre y media de cada estudiante
 */
function calcularMediaAlumno(nombres, notas){

    // Verificar si hay estudiantes registrados funcion reutilizable
    if (!verificarEstudiantes()) return;

    // Obtener solo datos de estudiantes
    let estudiantes = obtenerEstudiantes();

    let texto = '';

    // Recorrer estudiantes para calcular medias
    for (let i = 0; i < estudiantes.nombres.length; i++){
        let numero = i + 1;
        let nombre = estudiantes.nombres[i];

        // Calcular media usando funcion auxiliar
        let media = calcularMedia(estudiantes.notas[i]);
        media = media.toFixed(2); // Redondear a 2 decimales

        // Construir linea de resultado
        let linea = `${numero} - Nombre: ${nombre} | Media: ${media}\n`;

        // Acumular texto
        texto += linea;
    }

    // Mostrar resultado en el textarea
    resultadoTextarea.value = texto;
}    

/**
 * Identifica y muestra el estudiante con la nota media más alta.
 * Calcula la media de cada estudiante y compara para encontrar el máximo.
 * 
 * @param {Array} nombres - Array de nombres (parámetro por compatibilidad)
 * @param {Array} notas - Array de notas (parámetro por compatibilidad)
 */
function mejorAlumno(nombres, notas){

    // Verificar si hay estudiantes registrados funcion reutilizable
    if (!verificarEstudiantes()) return;

    // Obtener solo datos de estudiantes
    let estudiantes = obtenerEstudiantes();

    // Variables para almacenar al mejor alumno
    let mejorNombre = '';
    let mejorMedia = -1;
    
    // Recorrer estudiantes para calcular medias
    for (let i = 0; i < estudiantes.nombres.length; i++){
        let nombre = estudiantes.nombres[i];

        // Calcular media usando funcion auxiliar
        let media = calcularMedia(estudiantes.notas[i]);

        // Comparar si esta media es la mayor hasta ahora
        if (media > mejorMedia){
            mejorMedia = media;
            mejorNombre = nombre;
        }
    }

    // redondear mejor media a 2 decimales
    mejorMedia = mejorMedia.toFixed(2);

    // Mostrar resultado
    resultadoTextarea.value = `El alumno con la media mas alta es ${mejorNombre} y tiene una nota media de ${mejorMedia}`;

}

/*
       EJERCICIO 4 CREACION DE OBJETOS
*/
/**
 * Actualiza el array de objetos alumnado con todos los estudiantes actuales.
 * Reconstruye el array completo cada vez que se registra un nuevo estudiante.
 */
function actualizarAlumnado(){
    alumnado = []; // Reiniciar array

    // Recorrer todos los registros para crear objetos solo para estudiantes
    for (let i = 0; i < nombres.length; i++){
        if (cargos[i] === "1"){ // Si es estudiante
            
            // Calcular media
            let notasAlumno = notas[i];
            let media = calcularMedia(notasAlumno);
            
            // Crear objeto alumno con toda la informacion
            let alumno = {
                nombre: nombres[i],
                edad: edades[i],
                notas: notasAlumno,
                media: parseFloat(media.toFixed(2)) // Redondear a 2 decimales
            };

            // Añadir objeto al array alumnado
            alumnado.push(alumno);
        }
    }

    // Mostrar el array de objetos en la consola 
    console.log(alumnado);
}    

