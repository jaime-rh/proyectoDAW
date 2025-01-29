//registro acabado

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet{

    public RegistroServlet() {
        super(); // Llamada al constructor de la superclase (HttpServlet)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener los parámetros del formulario
        String usuario = request.getParameter("usuario");
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        if (usuario==null || usuario.isEmpty() || email==null || email.isEmpty() || clave==null || clave.isEmpty()) {
            request.setAttribute("error", "Debe rellenar todos los campos");
            request.getRequestDispatcher("/registro.jsp").forward(request, response);
            return; 
        }

         try (Connection conexion = ConexionBD.conexion()){

            // Encriptar la contraseña usando BCrypt
            String claveHash = BCrypt.hashpw(clave, BCrypt.gensalt());

            //consulta para verificar email
            String verificarEmail = "SELECT * FROM usuarios WHERE email = ?";
            //insercion de datos
            String query = "INSERT INTO usuarios (usuario, email, clave) VALUES (?, ?, ?)";

            //ejecucion verificacion correo
            try (PreparedStatement stmtVerificar = conexion.prepareStatement(verificarEmail)) {
                stmtVerificar.setString(1, email);
                try (ResultSet resultadoEmail = stmtVerificar.executeQuery()){
                    if (resultadoEmail.next()) {
                        request.setAttribute("error", "Usuario ya registrado");
                        request.getRequestDispatcher("/registro.jsp").forward(request, response);
                        return;
                    }
                }
            }

            //ejecucion insercion
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, usuario);
                stmt.setString(2, email);
                stmt.setString(3, claveHash);
    
                // Ejecutar la actualización en la base de datos
                int filasInsertadas = stmt.executeUpdate();
    
                // Verificar si el registro fue exitoso
                if (filasInsertadas > 0) {
                    request.getSession().setAttribute("mensajeExito", "Usuario registrado con éxito.");
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("error", "Error al registrar el usuario.");
                    request.getRequestDispatcher("/registro.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                response.getWriter().println("Error en la consulta SQL: " + e.getMessage());
            }

    
         }catch (SQLException e) {
            response.getWriter().println("Error al conectar con la base de datos: " + e.getMessage());
         }
    }  
}
