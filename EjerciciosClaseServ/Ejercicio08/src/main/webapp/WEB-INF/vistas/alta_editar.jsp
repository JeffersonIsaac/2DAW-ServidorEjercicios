<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Alta o Edición de personas</title>
</head>
<body>
    <main>
        <h1>${empty requestScope.persona ? "Alta de persona" : "Edición de persona"}</h1>
        <form action="${pageContext.request.contextPath}/personas/${empty requestScope.persona ? 'altaPersonas' : 'editar'}" method="post">
            <c:if test="${not empty requestScope.persona}">
                <input type="hidden" name="id" value="${requestScope.persona.id}">
            </c:if>
            <div>
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="${empty requestScope.persona ? '' : requestScope.persona.nombre}">
            </div>
            <div>
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" value="${empty requestScope.persona ? '' : requestScope.persona.apellido}">
            </div>
            <div>
                <label for="edad">Edad:</label>
                <input type="number" id="edad" name="edad" value="${empty requestScope.persona ? '' : requestScope.persona.edad}">
            </div>
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${empty requestScope.persona ? '' : requestScope.persona.email}">
            </div>
            <button type="submit">${empty requestScope.persona ? "Dar de alta" : "Guardar cambios"}</button>
        </form>
        <p><a href="${pageContext.request.contextPath}/personas">Volver al listado</a></p>
    </main>
</body>
</html>