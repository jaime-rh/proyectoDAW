<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./libros.css">
    <title>Calificar.com</title>
</head>
<body id="calificar">
    <div id="bookDetails"></div>

    <form action="" method="post">
        <div id="contenedorCalificar">
            <div id="libroCalificar">
                <img class="portada" id="portadaLibro"/>
                <h3 class="titulo" id="tituloLibro"></h3>
                <p class="autor" id="autoresLibro"><strong>Autor/es:</strong></p>
            </div>
            <label id="Lcalificacion">Calificacion: </label>
            <input type="number" id="calificacion" min="1" max="10" required placeholder="1-10">
            <br><br>
            <label id="Lf_inicio">Fecha de inicio: </label>
            <input type="date" id="f_inicio" required>
            <br><br>
            <label id="Lf_fin">Fecha de fin: </label>
            <input type="date" id="f_fin" required>
            <br><br>
            <button id="enviarCalificacion" type="submit">Guardar calificación</button>
        </div>
    </form>

    <script src=".//calificar.js"></script>

</body>
</html>