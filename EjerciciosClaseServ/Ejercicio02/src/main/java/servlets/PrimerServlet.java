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

@WebServlet("/usuarios")
public class PrimerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, Usuario> mapaUsuarios = new HashMap<>();

        Usuario usu1 = new Usuario("Pepe","123","Francisco Perez",47,"pepe47@gmail.com");
        Usuario usu2 = new Usuario("Andy","223","Andres Ruiz",13,"andy13@gmail.com");
        Usuario usu3 = new Usuario("Carl","323","Carlos Gomez",30,"carl30@gmail.com");

        mapaUsuarios.put(usu1.getNombre(), usu1);
        mapaUsuarios.put(usu2.getNombre(), usu2);
        mapaUsuarios.put(usu3.getNombre(), usu3);

        String nombre = req.getParameter("nombre");
        String contra = req.getParameter("contra");


        if (nombre == null || nombre.isBlank() || contra == null || contra.isBlank()) {
            req.setAttribute("errorMsg", "Debes rellenar todos los campos.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
        if (mapaUsuarios.containsKey(nombre)) {
            Usuario usuarioGuardado = mapaUsuarios.get(nombre);

            // CASO 3: Usuario existente y contraseña correcta
            if (usuarioGuardado.getContra().equals(contra)) {
                // Guardamos el objeto completo bajo el atributo "usuario"
                req.setAttribute("usuario", usuarioGuardado);
                // Redirección segura a la zona protegida
                req.getRequestDispatcher("/WEB-INF/perfil.jsp").forward(req, resp);
                return;
            }
            String textoEdad = (usuarioGuardado.getEdad() >= 18) ? "Mayor de edad" : "Menor de edad";
            req.setAttribute("condicionEdad", textoEdad);


            req.getRequestDispatcher("/WEB-INF/perfil.jsp").forward(req, resp);
            return;

        }
        //  si no entra en el Caso 3 Usuario inexistente O contraseña incorrecta
        req.setAttribute("errorMsg", "Usuario o contraseña incorrectos");
        // El campo contraseña no se toca aquí, por lo que viajará vacío de vuelta al JSP
        req.getRequestDispatcher("index.jsp").forward(req, resp);

        req.setAttribute("listaUsuarios", mapaUsuarios);
        req.getRequestDispatcher("/WEB-INF/perfil.jsp").forward(req, resp);
    }
}
