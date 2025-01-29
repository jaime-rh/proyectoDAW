// Función para buscar libros usando la API de Google Books
function buscarLibros() {
    // Obtener el valor del campo de texto con id 'buscador'
    var query = document.getElementById("buscador").value;

    // Verificar si el campo está vacío
    if (query.trim() === "") {
        alert("Por favor ingrese un título de libro.");
        return;
    }

    // URL para la API de Google Books
    var url = "https://www.googleapis.com/books/v1/volumes?q=" + encodeURIComponent(query) + "&maxResults=6";

    // Crear una solicitud HTTP utilizando Fetch API
    fetch(url)
        .then(function(response) {
            if (!response.ok) {
                throw new Error('Error al obtener los datos de la API');
            }
            return response.json(); // Convertir la respuesta en JSON
        })
        .then(function(data) {
            // Limpiar resultados previos
            var resultadosDiv = document.getElementById('resultados');
            resultadosDiv.innerHTML = ''; // Limpiar los resultados anteriores

            // Verificar si hay libros en los resultados
            if (data.items) {
                // Recorrer los libros encontrados y mostrar los resultados
                data.items.forEach(function(item) {
                    var libro = item.volumeInfo;
                    var titulo = libro.title || "Sin título";
                    var autores = libro.authors ? libro.authors.join(", ") : "Autor desconocido";
                    var imagen = libro.imageLinks ? libro.imageLinks.thumbnail : "https://via.placeholder.com/150"; // Imagen por defecto

                    // Crear el HTML para cada libro encontrado
                    var resultadoHTML = `
                        <div class="libro" onclick="calificar('${encodeURIComponent(titulo)}', '${encodeURIComponent(autores)}', '${encodeURIComponent(imagen)}')">
                            <img class="portada" src="${imagen}"/>
                            <h3 class="titulo">${titulo}</h3>
                            <p class="autor"><strong>Autor/es:</strong> ${autores}</p>
                        </div>
                    `;
                    resultadosDiv.innerHTML += resultadoHTML; // Agregar el resultado a la página
                });
            } else {
                // Si no se encontraron resultados
                resultadosDiv.innerHTML = "<p>No se encontraron libros con ese título.</p>";
            }
        })
        .catch(function(error) {
            console.error(error);
            alert("Hubo un error al buscar los libros.");
        });
}

// Función para redirigir a calificar.jsp con los parámetros del libro
function calificar(titulo, autores, imagen) {
    // Redirigir a calificar.jsp pasando los parámetros por URL
    var url = "calificar.jsp?titulo=" + titulo + "&autores=" + autores + "&imagen=" + imagen;
    window.location.href = url; // Redirigir a la página
}