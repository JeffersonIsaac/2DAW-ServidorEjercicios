package bbdd.interfaces;

import org.mariadb.jdbc.Connection;

import java.sql.SQLException;

public interface ConexionBD {


    Connection abrir() throws SQLException;


    void prepararBaseDatos() throws SQLException;
}
