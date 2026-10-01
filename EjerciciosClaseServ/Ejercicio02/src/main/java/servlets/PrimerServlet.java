package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("")
public class PrimerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, Usuario> mapaUsuarios = new HashMap<>();

        Usuario usu1 = new Usuario("Pepe","123","Francisco Perez",47,"pepe47@gmail.com");
        Usuario usu2 = new Usuario("Andy","223","Andres Ruiz",13,"andy13@gmail.com");
        Usuario usu3 = new Usuario("Carl","323","Carlos Gomez",30,"carl30@gmail.com");

        mapaUsuarios.put(usu1.getNombre(), usu1);
        mapaUsuarios.put(usu2.getNombre(), usu2);
        mapaUsuarios.put(usu3.getNombre(), usu3);

        req.setAttribute("listaUsuarios", mapaUsuarios);
        req.getRequestDispatcher("/vista-usuarios.jsp").forward(req, resp);
    }
}
