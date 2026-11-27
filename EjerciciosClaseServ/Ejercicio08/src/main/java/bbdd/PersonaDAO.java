package bbdd;

import bbdd.interfaces.ConexionBD;
import modelo.Persona;
import org.mariadb.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private final ConexionBD baseDatos;

    public PersonaDAO(ConexionBD baseDatos) {
        this.baseDatos = baseDatos;
    }

    public Persona buscarPersona(Long id ) throws SQLException {
        String sql = "SELECT id, nombre, apellidos, edad, mail FROM personas WHERE id = ?";
        try (Connection conexion = baseDatos.abrir(); PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setLong(1, id);

            try (ResultSet filas = sentencia.executeQuery()) {

                return filas.next() ? leerPersona(filas) : null;
        }

        }

    }
    public void insertarPersona(String nombre, String apellido, int edad, String email ) throws SQLException {
        String sql = "INSERT INTO personas (nombre, apellido, edad, email) VALUES (?, ?, ?, ?)";
        try (Connection conexion = baseDatos.abrir();
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setInt(3, edad);
            sentencia.setString(4, email);
            sentencia.executeUpdate();
        }
    }

    public boolean actualizarPersona(Long id, String nombre, String apellido, int edad, String email) throws SQLException {
        String sql = "UPDATE personas SET nombre = ?, apellido = ?, edad = ?, email = ? WHERE id = ?";
        try (Connection conexion = baseDatos.abrir();
        PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setInt(3, edad);
            sentencia.setString(4, email);
            sentencia.setLong(5, id);
            return sentencia.executeUpdate() == 1;
        }

    }

    public boolean eliminarPersona(Long id) throws SQLException {
        String sql = "DELETE FROM personas WHERE id = ?";
        try (Connection conexion = baseDatos.abrir();
        PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setLong(1, id);
            return sentencia.executeUpdate() == 1;
        }
    }




    private  Persona leerPersona(ResultSet filas) throws SQLException {
        Persona persona = new Persona();
        persona.setId(filas.getLong("id"));
        persona.setNombre(filas.getString("nombre"));
        persona.setApellido(filas.getString("apellidos"));
        persona.setEdad(filas.getInt("edad"));
        persona.setEmail(filas.getString("mail"));

        return persona;
    }

    public List<Persona> listarPersonas() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT id, nombre, apellidos, edad, email FROM personas ORDER BY id";
        try(Connection conexion = baseDatos.abrir();
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet filas = sentencia.executeQuery()){
            while (filas.next()) {
                personas.add(leerPersona(filas));
            }
        }
        return personas;
    }



}
