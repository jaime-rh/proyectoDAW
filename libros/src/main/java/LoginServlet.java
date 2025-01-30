import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    public LoginServlet() {
        super(); // Llamada al constructor de la superclase (HttpServlet)
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener los parámetros del formulario
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        // Verificar que los campos no estén vacíos
        if (email == null || email.isEmpty() || clave == null || clave.isEmpty()) {
            request.setAttribute("error", "Debe rellenar todos los campos.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Conectar a la base de datos
        try (Connection conexion = ConexionBD.conexion()) {
              String query = "SELECT * FROM usuarios WHERE email = ?";
                try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                    stmt.setString(1, email);
                    ResultSet resultadoEmail = stmt.executeQuery();

                    if (resultadoEmail.next()) {
                        // Obtener la contraseña encriptada de la base de datos
                        String claveHash = resultadoEmail.getString("clave");
                        // Verificar si la contraseña proporcionada coincide con la almacenada
                        if (BCrypt.checkpw(clave, claveHash)) {
                            // Crear una sesión
                            HttpSession session = request.getSession();
                            session.setAttribute("email", email); // Guardar el email de usuario en la sesión
                            // Si la contraseña es correcta redirige a libros.jsp
                            request.getSession().setAttribute("mensajeExito", "Usuario logeado con éxito.");
                            response.sendRedirect("libros.jsp");
                        } else {
                            // Si la contraseña no coincide
                            request.setAttribute("error", "email o contraseña incorrecto");
                            request.getRequestDispatcher("/login.jsp").forward(request, response);
                        }
                    } else {
                        // Si el usuario no existe
                        request.setAttribute("error", "email o contraseña incorrecto");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }catch (SQLException e) {
                    request.setAttribute("error", "Error en la consulta SQL: " + e.getMessage());
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
        }catch (SQLException e) {
                request.setAttribute("error", "Error al conectar con la base de datos: " + e.getMessage());
                request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
