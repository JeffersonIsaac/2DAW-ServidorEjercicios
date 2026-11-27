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

@WebServlet("/personas/eliminar")
public class EliminarPersonaServlet extends HttpServlet {
    private final ConexionBD baseDatos = new ConexionBDMariaDB();
    private final PersonaDAO personaDAO = new PersonaDAO(baseDatos);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id  = Long.parseLong(req.getParameter("id"));
        try {
            if(!personaDAO.eliminarPersona(id)){
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, " No existe el persona con el id " + id);
                return;
            }
            resp.sendRedirect(req.getContextPath()+"/personas");
        }catch (SQLException e){
            throw new ServletException("No se pudo eliminar la persona",e);
        }

    }
}
