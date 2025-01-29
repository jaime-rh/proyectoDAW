<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href=".//libros.css">
    <title>log</title>
</head>
<body>

<form action="LoginServlet" method="post">
    <div class="contenedor">
                <h1>Login</h1>

                <% String mensajeExito = (String) session.getAttribute("mensajeExito"); %>

                <% if (mensajeExito != null) { %>
                    <script type="text/javascript">
                        alert("<%= mensajeExito %>");
                    </script>
                    <%
                        session.removeAttribute("mensajeExito");
                    %>
                <% } %>

                <input class="campos" type="text" placeholder="Email" name="email">
                <input class="campos" type="password" placeholder="Clave" name="clave">

                <button type="submit" class="boton" id="log">Login</button>
                <!-- Mostrar mensaje de error si está disponible -->
                    <%
                        String error = (String) request.getAttribute("error");
                        if (error != null && !error.isEmpty()) {
                    %>
                        <div style="color: red; font-weight: bold;">
                            <%= error %>
                        </div>
                    <%
                        }
                    %>
                <p>No tienes cuenta? <a href=".//registro.jsp">Registrate</a></p>
            
    </div>
</form>
</body>
</html>
