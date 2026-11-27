package servlets;

import bbdd.ConexionBDMariaDB;
import bbdd.PersonaDAO;
import bbdd.interfaces.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Persona;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/personas/editar")
public class EditarPersonaServlet extends HttpServlet {

    private final ConexionBD baseDatos = new ConexionBDMariaDB();
    private final PersonaDAO personaDAO = new PersonaDAO(baseDatos);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id =  Long.parseLong(req.getParameter("id"));
        try {
            Persona persona = personaDAO.buscarPersona(id);
            if (persona == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El producto no existe.");
                return;
            }
            req.setAttribute("persona", persona);
            req.getRequestDispatcher("/WEB-INF/vistas/alta_editar.jsp").forward(req, resp);
        }catch (SQLException e){
            throw new ServletException("No se ha podido editar la persona, no aparece",e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        long id = Long.parseLong(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        int edad = Integer.parseInt(req.getParameter("edad"));
        String email = req.getParameter("email");
        try {
            if(!personaDAO.actualizarPersona(id, nombre, apellido, edad, email)){
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El producto no existe.");
                return;
            }
            resp.sendRedirect(req.getContextPath()+"/personas");
        }catch (SQLException e) {
            throw new ServletException("No se pudo actualizar el producto.", e);
        }
    }
}
