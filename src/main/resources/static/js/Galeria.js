// 1. EL ARCHIVERO MAESTRO (Todo en un solo lugar)
const baseDeDatosFotos = {
    'tlaxcalli': [
        '/img/projects/tlaxcalli/tlogin.png',
        '/img/projects/tlaxcalli/RegGas.png',
        '/img/projects/tlaxcalli/pdfclean.png',
        '/img/projects/tlaxcalli/VentanaG.png',
        '/img/projects/tlaxcalli/ventanaR.png'
    ],
    'cproveedores': [
        '/img/projects/control-abarrotes/iniAba.png',
        '/img/projects/control-abarrotes/calAba.png',
        '/img/projects/control-abarrotes/povAba.png',
        '/img/projects/control-abarrotes/regAba.png',
        '/img/projects/control-abarrotes/lisAba.png',
        '/img/projects/control-abarrotes/conAba.png'
    ]
};

// 2. Registro de posiciones iniciales
const indicesActuales = {
    'tlaxcalli': 0,
    'cproveedores': 0,
};

// Objeto para almacenar los identificadores de los setTimeout
const relojes = {};

// 3. El motor automatizado (Sin necesidad de usar Switch)
function cambiarFoto(proyecto, direccion) {
    const fotos = baseDeDatosFotos[proyecto];
    if (!fotos) return; // Si hay un error de dedo en el HTML, aquí se protege

    indicesActuales[proyecto] += direccion;

    // Reglas de bucle infinito
    if (indicesActuales[proyecto] >= fotos.length) {
        indicesActuales[proyecto] = 0;
    } else if (indicesActuales[proyecto] < 0) {
        indicesActuales[proyecto] = fotos.length - 1;
    }

    const elementoImg = document.getElementById('img-' + proyecto);
    if (elementoImg) {
        elementoImg.src = fotos[indicesActuales[proyecto]];
    }
}

// 3. Encender el carrusel (Se activa al pasar el ratón)
function iniciarCarrusel(proyecto) {
    // Primero nos aseguramos de limpiar cualquier temporizador activo para evitar duplicados
    detenerCarrusel(proyecto);

    // Programamos la SIGUIENTE foto para dentro de 3 segundos
    relojes[proyecto] = setTimeout(() => {
        cambiarFoto(proyecto, 1);
        // Llamada recursiva: el reloj se vuelve a programar a sí mismo solo si el ratón sigue ahí
        iniciarCarrusel(proyecto);
    }, 3000);
}

// 4. Apagar por completo el carrusel (Se activa al quitar el ratón)
function detenerCarrusel(proyecto) {
    if (relojes[proyecto]) {
        clearTimeout(relojes[proyecto]);
        relojes[proyecto] = null; // Liberamos el recurso de la memoria
    }
}

// 5. Control absoluto para los clics manuales del usuario (< o >)
function cambiarFotoManual(proyecto, direccion) {
    // Detenemos el reloj de inmediato para congelar la línea del tiempo
    detenerCarrusel(proyecto);

    // Cambiamos la foto al instante según la flecha presionada
    cambiarFoto(proyecto, direccion);

    // Volvemos a arrancar el carrusel desde cero, dando 3 segundos limpios a la foto seleccionada
    iniciarCarrusel(proyecto);
}