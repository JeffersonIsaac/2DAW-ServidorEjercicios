<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de personas</title>
</head>
<body>
    <main>
        <h1>Listado de personas</h1>
        <p><a href="${pageContext.request.contextPath}/personas/altaPersonas">Dar de alta</a></p>
        <c:choose>
            <c:when test="${not empty requestScope.personas}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Edad</th>
                            <th>Email</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="persona" items="${requestScope.personas}">
                            <tr>
                                <td>${persona.id}</td>
                                <td>${persona.nombre}</td>
                                <td>${persona.apellido}</td>
                                <td>${persona.edad}</td>
                                <td>${persona.email}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/personas/editar?id=${persona.id}">Editar</a> |
                                    <form action="${pageContext.request.contextPath}/personas/eliminar" method="post">
                                        <input type="hidden" name="id" value="${persona.id}">
                                        <button type="submit">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No hay personas registradas.</p>
            </c:otherwise>
        </c:choose>
    </main>
</body>
</html>