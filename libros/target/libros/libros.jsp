<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--API utilizada: open library-->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href=".//libros.css">
    <title>libros</title>
</head>
<body>
    <div id="cabezara">
        <p id="logo">BLibros</p>
        <div class="menuUsuario">
            <span id="usuario">Usuario</span>
            <div class="navHidden">
                <ul>
                    <li id="li"><a href="" id="cerrarSesion">Cerrar Sesion</a></li>
                </ul>
            </div>
        </div>
    </div>
                <% String mensajeExito = (String) session.getAttribute("mensajeExito"); %>

                <% if (mensajeExito != null) { %>
                    <script type="text/javascript">
                        alert("<%= mensajeExito %>");
                    </script>
                    <%
                        session.removeAttribute("mensajeExito");
                    %>
                <% } %>
    <div class="contenedorBuscador">
        <p id="introduce">Introduzca el libro</p>
        <input type="text" id="buscador">
        <button id="buscar" onclick="buscarLibros()">Buscar</button>
        <div id="resultados"></div>
    </div>

    <div id="librosCalificados"></div>

    <script src=".//libros.js"></script>
</body>
</html>