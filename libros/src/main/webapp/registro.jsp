<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href=".//libros.css">
    <title>Registro</title>
</head>
<body>

<form action="RegistroServlet" method="post">
    <div class="contenedor">
        <h1>Registrate</h1>
                <input class="camposReg" type="text" placeholder="Usuario" name="usuario" value="">
                <input class="camposReg" type="text" placeholder="Email" name="email" value="">
                <input class="camposReg" type="password" placeholder="Clave" name="clave" value="">

                <div class="yaLogDiv">
                    <a id="yaLog" href=".//login.jsp">Ya estas logeado?</a>
                </div>
                <button type="submit" class="botonReg" id="log">Registrate</button>

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
    </div>
</form>
</body>
</html>