package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/operador")
public class MainOperador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Vamos a Operar con num1 y num2 !");
        double num1 = Double.parseDouble(req.getParameter("num1")) ;
        String op = req.getParameter("op");
        double num2 = Double.parseDouble(req.getParameter("num2")) ;
        String operacion = switch (op.toLowerCase()) {
            case "sumar" ->"El resultado es : " + (num1 + num2);
            case "restar" ->"El resultado es : " + (num1 - num2);
            case "multiplicar" ->   "El resultado es : " + (num1 * num2);
            case "dividir" -> "El resultado es : " + (num1 / num2);
            default -> "Introduce una operacion válida";
        };

        resp.getWriter().println(operacion);

    }
}
