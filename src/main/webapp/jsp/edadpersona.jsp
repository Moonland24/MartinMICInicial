<%@ page import="java.time.*, java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado de edad</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="container">
        <h1 class="titulo">Resultado</h1>

<%
    // Obtener parámetros del formulario
    String nombre = request.getParameter("nombre");
    String fechaNacimientoStr = request.getParameter("fecha_nacimiento");

    if (nombre == null || nombre.trim().isEmpty() || fechaNacimientoStr == null || fechaNacimientoStr.isEmpty()) {
%>
        <p>Por favor, completa todos los campos.</p>
<%
    } else {
        try {
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
            LocalDate hoy = LocalDate.now();

            out.println("<p>Hola, <strong>" + nombre + "</strong>.</p>");

            if (fechaNacimiento.isAfter(hoy)) {
                out.println("<p>Aún no has nacido, así que espérate, ansias</p>");
            } else if (fechaNacimiento.isEqual(hoy)) {
                out.println("<p>¡Has nacido hoy! Bienvenido al mundo renacuajo</p>");
            } else {
                Period edad = Period.between(fechaNacimiento, hoy);
                int anios = edad.getYears();
                int meses = edad.getMonths();
                int dias = edad.getDays();

                if (meses == 0 && dias == 0) {
                    out.println("<pTienes " + anios + (anios == 1 ? " año" : " años"));
                } else {
                    out.println("<p>Tienes: " 
                        + anios + (anios == 1 ? " año, " : " años, ")
                        + meses + (meses == 1 ? " mes, " : " meses, ")
                        + dias + (dias == 1 ? " día." : " días.") + "</p>");
                }
            }
        } catch (Exception e) {
            out.println("<p>Fecha inválida. Asegúrate de usar el formato correcto.</p>");
        }
    }
%>

        <br><a href="../index.html" class="volver">Volver al índice</a>
    </div>
</body>
</html>
