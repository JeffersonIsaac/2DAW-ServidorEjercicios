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

@WebServlet(value = "/personas/altaPersonas")
public class AltaPersonaServlet extends HttpServlet {
    private final ConexionBD baseDatos = new ConexionBDMariaDB();
    private final PersonaDAO personaDAO = new PersonaDAO(baseDatos);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/vistas/alta_editar.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        int edad = Integer.parseInt(req.getParameter("edad"));
        String email = req.getParameter("email");

        try {
            personaDAO.insertarPersona(nombre,apellido,edad,email);
            resp.sendRedirect(req.getContextPath()+"/personas");
        } catch (SQLException e) {
            throw new ServletException("No se pudo dar de alta a la persona",e);
        }
    }
}
