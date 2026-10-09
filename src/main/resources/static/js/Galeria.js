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

const indicesActuales = {
    'tlaxcalli': 0,
    'cproveedores': 0,
};

const relojes = {};

function cambiarFoto(proyecto, direccion) {
    const fotos = baseDeDatosFotos[proyecto];
    if (!fotos) return;

    indicesActuales[proyecto] += direccion;

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


function iniciarCarrusel(proyecto) {

    detenerCarrusel(proyecto);


    relojes[proyecto] = setTimeout(() => {
        cambiarFoto(proyecto, 1);

        iniciarCarrusel(proyecto);
    }, 3000);
}


function detenerCarrusel(proyecto) {
    if (relojes[proyecto]) {
        clearTimeout(relojes[proyecto]);
        relojes[proyecto] = null;
    }
}


function cambiarFotoManual(proyecto, direccion) {

    detenerCarrusel(proyecto);

    cambiarFoto(proyecto, direccion);

    iniciarCarrusel(proyecto);
}