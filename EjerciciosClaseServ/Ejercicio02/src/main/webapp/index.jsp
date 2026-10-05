<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Titulo JSP</title>
</head>
<body>

    <h2>Formulario de Login</h2>



    <form action="${pageContext.request.contextPath}/usuarios" method="post" >
    <h1>Este es el título con la breve explicasssssion</h1>
        <label>El nombre : <input id = "nombre"></label> <%-- ${usuario.nombreCompleto} --%>
        <label>La contraseña : <input id = "contra"></label>
        <button type="submit">Enviar</button>
    </form>
    <%-- Mostrar mensaje de error si existe en la request --%>
        <% String error = (String) request.getAttribute("errorMsg");
            if (error != null) { %>
        <p style="color: red;"><%= error %></p>
        <% }
        %>
</body>
</html>