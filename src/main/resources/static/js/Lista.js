function toggleProyectos() {
    const lista = document.getElementById('listaRepositorios');
    const btn = document.getElementById('btnVerMas');

    if (lista.classList.contains('max-h-0')) {
        // ABRIR: Quitamos el cero, ponemos altura gigante y opacidad total
        lista.classList.remove('max-h-0', 'opacity-0');
        lista.classList.add('max-h-[2000px]', 'opacity-100');
        btn.innerHTML = '<svg class="w-6 h-6 ml-1 transform group-hover:-translate-x-3 transition-transform inline" ' +
            'fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">' +
            '<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 8 l-4 4 m0 0 l4 4 m-4 -4 H15"></path>' +
            '</svg>ocultar';
    } else {
        // CERRAR: Quitamos la altura gigante y regresamos todo a cero
        lista.classList.remove('max-h-[2000px]', 'opacity-100');
        lista.classList.add('max-h-0', 'opacity-0');
        btn.innerHTML = 'ver más <svg class="w-6 h-6 ml-1 transform group-hover:translate-x-3 transition-transform inline" ' +
            'fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">' +
            '<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 8 l4 4 m0 0 l-4 4 m4-4 H3"></path>' +
            '</svg>';
    }
}