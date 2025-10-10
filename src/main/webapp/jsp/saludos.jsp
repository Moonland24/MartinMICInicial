<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%
    String nombre = request.getParameter("nombre");
    String sexo = request.getParameter("sexo");

    int hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    String saludo;

    if (hora >= 8 && hora <= 13) {
        saludo = "Buenos días";
    } else if (hora >= 13 && hora <= 21) {
        saludo = "Buenas tardes";
    } else {
        saludo = "Buenas noches";
    }

    String genero = "Bienvenid@";
    if ("hombre".equals(sexo)) {
        genero = "Bienvenido";
    } else if ("mujer".equals(sexo)) {
        genero = "Bienvenida";
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Saludo</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="burbujas">
        <div class="burbuja" style="width:60px;height:60px;left:10%;animation-delay:0s;"></div>
        <div class="burbuja" style="width:40px;height:40px;left:30%;animation-delay:2s;"></div>
        <div class="burbuja" style="width:80px;height:80px;left:50%;animation-delay:4s;"></div>
        <div class="burbuja" style="width:50px;height:50px;left:70%;animation-delay:1s;"></div>
        <div class="burbuja" style="width:70px;height:70px;left:90%;animation-delay:3s;"></div>
    </div>

    <div class="container">
        <div class="card">
            <h1><%= saludo %>, <%= genero %> <%= nombre %>!</h1>
            <p>Esperamos que tengas un excelente día</p>
            <a href="../index.html" class="volver">Volver al índice</a>
        </div>
    </div>
</body>
</html>
