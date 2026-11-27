package servlets;

import bbdd.ConexionBDMariaDB;
import bbdd.PersonaDAO;
import bbdd.interfaces.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet( value = {"/personas", "/personas/"}, loadOnStartup = 1)
public class ListadoPersonasServlet extends HttpServlet {
    private PersonaDAO personaDAO;

    public void init() throws ServletException {

        System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));

        ConexionBD baseDatos = new ConexionBDMariaDB();
        try {
            baseDatos.prepararBaseDatos();
            personaDAO =  new PersonaDAO(baseDatos);
        } catch (SQLException e) {
            throw new ServletException("No se pudo preparar la base de datos.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.setAttribute("personas", personaDAO.listarPersonas());
            req.getRequestDispatcher("/WEB-INF/vistas/listado.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new ServletException("No se  ha  podido consultar el listado de personas",e);
        }
    }
}
