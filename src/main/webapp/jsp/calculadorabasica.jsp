<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%
    String resultado = "";
    String mensajeError = "";
    String fechaHoy = "";
    boolean mostrarResultado = false;

    String num1Str = request.getParameter("num1");
    String num2Str = request.getParameter("num2");
    String operacion = request.getParameter("operacion");

    if (num1Str != null || num2Str != null || operacion != null) {
        mostrarResultado = true;

        if (num1Str == null || num1Str.trim().isEmpty() ||
            num2Str == null || num2Str.trim().isEmpty()) {
            mensajeError = "Debe ingresar ambos operandos para realizar la operación.";
        } else if (operacion == null || operacion.trim().isEmpty()) {
            mensajeError = "Debe seleccionar una operación para continuar.";
        } else {
            try {
                int num1 = Integer.parseInt(num1Str.trim());
                int num2 = Integer.parseInt(num2Str.trim());

                switch (operacion) {
                    case "suma":
                        resultado = "Resultado: " + (num1 + num2);
                        break;
                    case "resta":
                        resultado = "Resultado: " + (num1 - num2);
                        break;
                    case "multiplicacion":
                        resultado = "Resultado: " + (num1 * num2);
                        break;
                    case "division":
                        if (num2 == 0) {
                            mensajeError = "No se puede dividir por 0.";
                        } else {
                            resultado = "Resultado: " + (num1 / num2);
                        }
                        break;
                    default:
                        mensajeError = "Operación no válida.";
                }
            } catch (NumberFormatException e) {
                mensajeError = "Algún dato de entrada no es un número válido.";
            }
        }

        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int anio = calendario.get(Calendar.YEAR);

        String[] meses = {
            "enero", "febrero", "marzo", "abril", "mayo", "junio",
            "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
        };

        fechaHoy = "Hoy es " + dia + " de " + meses[mes] + " del " + anio;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Calculadora JSP</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>

<div class="container">
    <h1 class="titulo">Calculadora</h1>
</div>

<div class="calculadora-container">
    <div class="calculadora-card">

        <% if (mostrarResultado) { %>
            <p class="fecha"><%= fechaHoy %></strong></p>
            <% if (!mensajeError.isEmpty()) { %>
                <p class="error"><%= mensajeError %></p>
            <% } else if (!resultado.isEmpty()) { %>
                <p class="resultado"><%= resultado %></p>
            <% } %>
        <% } %>

        <form method="get" action="">
            <label>Operando 1:
                <input type="text" name="num1" value="<%= (num1Str != null) ? num1Str : "" %>">
            </label><br><br>

            <label>Operando 2:
                <input type="text" name="num2" value="<%= (num2Str != null) ? num2Str : "" %>">
            </label><br><br>

            <label><input type="radio" name="operacion" value="suma" <%= "suma".equals(operacion) ? "checked" : "" %>> Suma</label><br>
            <label><input type="radio" name="operacion" value="resta" <%= "resta".equals(operacion) ? "checked" : "" %>> Resta</label><br>
            <label><input type="radio" name="operacion" value="multiplicacion" <%= "multiplicacion".equals(operacion) ? "checked" : "" %>> Multiplicación</label><br>
            <label><input type="radio" name="operacion" value="division" <%= "division".equals(operacion) ? "checked" : "" %>> División</label><br><br>

            <input type="submit" value="Calcular">
            <button type="button" onclick="limpiarFormulario()">Limpiar</button>

            <script>
                function limpiarFormulario() {
                    const form = document.querySelector('form');
                    form.reset(); 
                    window.location.href = window.location.pathname; 
                }
            </script>

            <a href="../index.html" class="volver">Volver al índice</a>
        </form>
    </div>
</div>

</body>
</html>
