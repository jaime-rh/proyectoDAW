// Obtener los parámetros de la URL
const libro = new URLSearchParams(window.location.search);
const titulo = libro.get('titulo');
const autores = libro.get('autores');
const imagen = libro.get('imagen');

// Mostrar los datos en la página
document.getElementById('tituloLibro').textContent = titulo;
document.getElementById('autoresLibro').textContent = "Autores: " + autores;
document.getElementById('portada').src = imagen;