function cargarDatos(){
    document.getElementById("url").textContent = window.location.href;

    const protocol = window.location.protocol;
    document.getElementById("protocolo").innerHTML = window.location.protocol;
    
    document.getElementById("agente").textContent = navigator.userAgent;
}