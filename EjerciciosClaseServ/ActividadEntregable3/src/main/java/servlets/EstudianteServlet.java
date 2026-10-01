package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = {"/estudiantes", "/estudiantes/*"})
public class EstudianteServlet extends HttpServlet {

    private static final Map<Integer, String> ESTUDIANTES = Map.of(
            15, "Ana García",
            27, "Luis Pérez"
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            String pathInfo = req.getPathInfo();

            if (pathInfo == null || pathInfo.equals("/")) {
                throw new IllegalArgumentException("Error 400: Debe proporcionar un identificador en la ruta (ejemplo: /estudiantes/15).");
            }

            String idParam = pathInfo.substring(1);

            int idEstudiante;
            try {
                idEstudiante = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Error 400: El identificador '" + idParam + "' no es un número entero válido.");
            }
            // Validar la existencia del estudiante
            String nombreEstudiante = ESTUDIANTES.get(idEstudiante);
            if (nombreEstudiante == null) {
                throw new EstudianteNoEncontradoException("Error 404: No existe ningún estudiante registrado con el ID " + idEstudiante + ".");
            }

            // EL EXITO !
            resp.setStatus(HttpServletResponse.SC_OK);
            out.println("ID: " + idEstudiante);
            out.println("Nombre: " + nombreEstudiante);

        } catch (NumberFormatException   e) {
            //Mala request (HTTP 400)
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());

        } catch (EstudianteNoEncontradoException e) {
            // ID no registrado (HTTP 404)
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println(e.getMessage());

        } catch (Exception e) {
            //(HTTP 500)
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Error 500: Ocurrió un error interno en el servidor.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            // 1. El tipo inválido (HTTP 415 )
            String contentType = request.getContentType();
            if (contentType == null || !contentType.toLowerCase().startsWith("text/plain")) {
                response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE); // 415
                out.println("Error 415: El tipo de contenido (Content-Type) debe ser 'text/plain'.");
                return;
            }

            String nombre;
            try (java.io.BufferedReader reader = request.getReader()) {
                nombre = reader.lines().collect(java.util.stream.Collectors.joining("\n")).trim();
            }

            // Mala reQ (HTTP 400 Bad Request)
            if (nombre.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                out.println("Error 400: El nombre del estudiante no puede estar vacío ni contener solo espacios.");
                return;
            }

            // Ya registrado (HTTP 409 Conflict)
            if (nombre.equalsIgnoreCase("Ana García") || nombre.equalsIgnoreCase("Luis Pérez")) {
                response.setStatus(HttpServletResponse.SC_CONFLICT); // 409
                out.println("Error 409: Ya existe un estudiante registrado con el nombre '" + nombre + "'.");
                return;
            }
            /********************  */
            //Para crear el nuevo
            int nuevoId = 28;

            String locationHeader = request.getContextPath() + "/estudiantes/" + nuevoId;
            response.setHeader("Location", locationHeader);

            response.setStatus(HttpServletResponse.SC_CREATED); // 201
            out.println("Estudiante creado con éxito. ID: " + nuevoId + ", Nombre: " + nombre);

        } catch (Exception e) {
            // Error genérico (HTTP 500)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error 500: Ocurrió un error interno en el servidor.");
        }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            //Validar el tipo
            String contentType = request.getContentType();
            if (contentType == null || !contentType.toLowerCase().startsWith("text/plain")) {
                response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE); // 415
                out.println("Error 415: El tipo de contenido (Content-Type) debe ser 'text/plain'.");
                return;
            }

            // Validar el path
            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                out.println("Error 400: Debe proporcionar un identificador en la ruta (ejemplo: /estudiantes/15).");
                return;
            }

            String idParam = pathInfo.substring(1);
            int idEstudiante;
            try {
                idEstudiante = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                out.println("Error 400: El identificador '" + idParam + "' no es un número entero válido.");
                return;
            }

            // Clásico(HTTP 404)
            if (!ESTUDIANTES.containsKey(idEstudiante)) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
                out.println("Error 404: No existe ningún estudiante registrado con el ID " + idEstudiante + ".");
                return;
            }
            //La nueva petición
            String nuevoNombre;
            try (java.io.BufferedReader reader = request.getReader()) {
                nuevoNombre = reader.lines().collect(java.util.stream.Collectors.joining("\n")).trim();
            }

            // (HTTP 400 Bad Request)
            if (nuevoNombre.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                out.println("Error 400: El cuerpo de la petición no puede estar vacío ni contener solo espacios.");
                return;
            }

            // (HTTP 200 OK)
            String nombreAnterior = ESTUDIANTES.get(idEstudiante);

            response.setStatus(HttpServletResponse.SC_OK); // 200
            out.println("Estudiante con ID " + idEstudiante + " actualizado con éxito.");
            out.println("Nombre anterior: " + nombreAnterior);
            out.println("Nuevo nombre: " + nuevoNombre);

        } catch (Exception e) {
            // Por siacaso (HTTP 500)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error 500: Ocurrió un error interno en el servidor.");
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configuración del tipo de respuesta
        response.setContentType("text/plain;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            // Sacar el path
            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                out.println("Error 400: Debe proporcionar un identificador en la ruta (ejemplo: /estudiantes/15).");
                return;
            }

            String idParam = pathInfo.substring(1);
            int idEstudiante;
            try {
                idEstudiante = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                out.println("Error 400: El identificador '" + idParam + "' no es un número entero válido.");
                return;
            }

            // Clásico (HTTP 404 Not Found)
            if (!ESTUDIANTES.containsKey(idEstudiante)) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
                out.println("Error 404: No existe ningún estudiante registrado con el ID " + idEstudiante + ".");
                return;
            }

            // El éxito (HTTP 200 OK)
            String nombreEstudiante = ESTUDIANTES.get(idEstudiante);

            response.setStatus(HttpServletResponse.SC_OK);
            out.println("Simulación de eliminación exitosa.");
            out.println("Se eliminaría el estudiante con ID: " + idEstudiante + " (" + nombreEstudiante + ").");

        } catch (Exception e) {
            // Por siacaso (HTTP 500)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error 500: Ocurrió un error interno en el servidor.");
        }
    }

    /** Funciones necesarias */
    class EstudianteNoEncontradoException extends Exception {
        public EstudianteNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }


}
