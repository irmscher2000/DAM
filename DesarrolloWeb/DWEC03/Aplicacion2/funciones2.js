/* Eugen Moga
   DW Tema 3 Tarea*/ 


/* Funcion para validar el formulario*/
function validarFormulario(){
    const nombre = document.getElementById("nombre").value.trim();
    const dia = document.getElementById("dia").value.trim();
    const mes = document.getElementById("mes").value.trim();
    const anio = document.getElementById("anio").value.trim();

    // Validación de datos vacíos
    if (!nombre || !dia || !mes || !anio){
        alert("Se deben de completar todos los datos");
    }

    // Validación del dia
    if (dia < 1 || dia > 31){
        alert("El día de nacimiento no es correcto");
        return false;
    }

    // Validación del mes
    if (mes < 1 || mes > 12){
        alert("El mes de nacimiento no es correcto");
        return false;
    }

    // Validacion del año
    const anioActual = new Date().getFullYear();
    if (anio < 1900 || anio > anioActual){
        alert("El año de nacimiento no es correcto");
        return false;
    }

    // Longitud del nombre
    const longitudNombre = nombre.length;

    // Primera vocal
    const infoVocal = primeraVocal(nombre);
    const vocal = infoVocal.vocal;
    const posicionVocal = infoVocal.posicion;

    // Nombre en Mayusculas
    const nMayuscula = nombre.toUpperCase();

    // Calcular edad 
    const edad = calcularEdad(anio);

    // Número mayor de un conjunto
    const mayor = numeroMayor([24, 67, 23, 76, 35, 17]);

    // Número aleatorio y coseno
    const nAleatorio = Math.random() * 360;

    // Coseno en grados
    const coseno = cosenoGrados(nAleatorio);

    // Texto en la pantalla principal
    const texto = `
                Buenos días ${nombre}. Tu nombre tiene ${longitudNombre} caracteres, incluidos espacios.<br>
                La primera vocal de tu nombre es "${vocal}" y está en la posición ${posicionVocal}.<br>
                Tu nombre todo en mayúsculas es: ${nMayuscula}.<br>
                Además tu edad es: ${edad} años, ya que naciste el ${dia}/${mes} del año ${anio}.<br>
                El número mayor de (24, 67, 23, 76, 35, 17) es: ${mayor}.
                Se ha generado un número al azar entre 0 y 360: ${nAleatorio.toFixed(2)}, y el coseno de dicho número es: 
                ${coseno.toFixed(4)}.
                `;

    // Escribo dentro de la caja out
    document.getElementById("out").innerHTML = texto;
 
    // Función que devuelve la primera vocal y su posicion
    function primeraVocal(texto){
        const vocales = "aeiouAEIOU";

        for (let i = 0; i < texto.length; i++){
            if (vocales.includes(texto[i])){
                return { vocal: texto[i], posicion: i + 1};
            }
        }
        return { vocal: "-", posicion: -1};
    }

    // Función que devuelve el número mayor de un conjunto
    function numeroMayor(lista){
        let max = lista[0];

        for (let num of lista){
            if (num > max) max = num;
        }
        return max;
    }

    // Función convertir grados, coseno
    function cosenoGrados(grados){
        const radianes = grados * Math.PI / 180;
        return Math.cos(radianes);
    }


    // Función calcular edad
    function calcularEdad(anioNacimiento){
        const anioActual = new Date().getFullYear();
        return anioActual - anioNacimiento;
    }

    return false;
}