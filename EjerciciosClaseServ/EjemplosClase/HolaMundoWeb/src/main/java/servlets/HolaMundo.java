package servlets;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/holaMundo")
public class HolaMundo extends HttpServlet {

    // Cuando alguien acceda a la URL holaMundo por GET
    // Entraremos por aquí.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hola mundo! por doGet");
        String nombreUsuario = req.getParameter("nombre");
        String apUsuario =  req.getParameter("apellido");
        resp.getWriter().println("<h1>Hola "+  nombreUsuario+ " "+apUsuario +"  doGet!</h1>");
    }
}