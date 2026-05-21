/*
    Eugen Moga
    DWEC Tarea Tema 2
*/

// Ejercicio 1

// Obtiene el elmento con id frm-persona y añade un escuchador de eventos
// Cuando se envia el formulario ejecuta la función.
document.getElementById('frm-persona').addEventListener('submit', function(event){

    // Detiene la acción por defecto del formulario que es refrescar la pagina y
    // permite manejar el envio con JavaScript
    event.preventDefault();

    // Obtienen los elementos con sus respectivos id. con trim eliminos los espacion al inicio y al final
    const nombre = document.getElementById('in-nombre').value.trim();
    const apellido = document.getElementById('in-apellido').value.trim();
    const localidad = document.getElementById('in-localidad').value.trim();
    const salida = document.getElementById('out-datos');

    // Se comprueba si las variables estan vacias para manejar el error.
    // Si todo esta bien se muestra el mensaje.
    if (nombre === '' || apellido === '' || localidad === ''){
        salida.textContent = "Error: Completa todos los datos. "
    }else {
        salida.textContent = `Hola, me llamo ${nombre} ${apellido} y vivo en ${localidad}. `;
    }
});

// Ejercicio 2
/*
 Funcion con bucle for 
*/
function primosFor(N){
    let divisores = [];
    for (let i = 2; i < N; i++){
        if (N % i === 0) divisores.push(i);
    }
    return divisores;
}

/*
 Funcion con bucle while
*/
function primosWhile(N){
    let divisores = [];
    let i = 2;
    while (i < N){
        if (N % i === 0) divisores.push(i);
        i++;
    }
    return divisores;
}

/*
 Funcion con bucle do while
*/
function primosDoWhile(N){
    let divisores = [];
    let i = 2;
    do{
        if (N % i === 0) divisores.push(i);
        i++;
    }while (i < N);
    return divisores;
}

/*
 Manejo el boton2
*/
// Se busca el elemento con id boton2 y se agrega un escuchardor para cuando 
// se haga clic en el boton.
document.getElementById('boton2').addEventListener('click', function (){

    // Se obtiene el valor ingresado en el input con id in-n y 
    // se convierte a un numero entero.
    const N = parseInt(document.getElementById('in-n').value);

    // Se obtiene el resultado de salida para cada bucle con su respectivo id
    const outFor = document.getElementById('out-for');
    const outWhile = document.getElementById('out-while');
    const outDoWhile = document.getElementById('out-dowhile');

    // Se llama a la funcion de cada bucle con el numero N 
    // y almacena el array de los numeros divisores.
    const variableFor =primosFor(N);
    const variableWhile =primosWhile(N);
    const variableDoWhile =primosDoWhile(N);

    // Se manja la salida con su correspondiente mensaje
    // Comprueba si el array de divisores esta vacio y muestra el mensaje
    outFor.textContent = variableFor.length === 0 ?
        `El número ${N} es un número primo.` :
        // Si no esta vacio, se muestra la lista de divisores encontrados
        `El número ${N} tiene los siguientes divisores: ${variableFor.join(", ")}`;

    outWhile.textContent = variableWhile.length === 0 ?
        `El número ${N} es un número primo.` :
        `El número ${N} tiene los siguientes divisores: ${variableWhile.join(", ")}`;

    outDoWhile.textContent = variableDoWhile.length === 0 ?
        `El número ${N} es un número primo.` :
        `El número ${N} tiene los siguientes divisores: ${variableDoWhile.join(", ")}`;

});
