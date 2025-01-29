import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/libros"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "123@jm.com"; 

    // Bloque estático para cargar el driver de MySQL (solo necesario si tu JDBC no lo hace automáticamente)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Error al cargar el driver de MySQL: " + e.getMessage());
        }
    }

    public static Connection conexion() throws SQLException{
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }

    }

}
