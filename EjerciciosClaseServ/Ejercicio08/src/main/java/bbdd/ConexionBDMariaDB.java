package bbdd;

import bbdd.interfaces.ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDMariaDB implements ConexionBD {
    private static final String URL = "jdbc:mariadb://localhost:3306/dwes_07";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    @Override
    public org.mariadb.jdbc.Connection abrir() throws SQLException {
        try {
            // Carga explícita del controlador que Maven incluye en WEB-INF/lib.
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encuentra el controlador JDBC de MariaDB.", e);
        }
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
    @Override
    public void prepararBaseDatos() throws SQLException {
        try (Connection conexion = abrir()) {
            if (!conexion.isValid(5)) {
                throw new SQLException("La conexión con MariaDB no es válida.");
            }
        }
    }
}
