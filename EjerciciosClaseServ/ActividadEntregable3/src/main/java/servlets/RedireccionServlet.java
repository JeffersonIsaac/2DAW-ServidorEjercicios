package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/inicio")
public class RedireccionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destino = request.getContextPath() + "/estudiantes/15";

        response.setHeader("Location", destino);

        // HTTP 307 (Redirección Temporal)
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT); // Equivalente a 307
    }
}
