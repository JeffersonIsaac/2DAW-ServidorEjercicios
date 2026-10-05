<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ejemplo.modelo.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Perfil de Usuario</title>
</head>
<body>
<%-- Recuperamos el objeto del atributo de la request --%>
<%
Usuario usr = (Usuario) request.getAttribute("usuario");
%>

<h1>Bienvenido a tu perfil</h1>
<p>Has iniciado sesión correctamente.</p>

<ul>
    <li><strong>Usuario activo:</strong> <%= usr.getNombre() %></li>
</ul>
<p><a href="${pageContext.request.contextPath}/index.jsp">Volver al formulario</a></p>
</body>
</html>

