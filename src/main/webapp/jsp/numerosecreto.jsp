<%@ page import="java.util.*" %>
<%
    Integer numeroSecreto = (Integer) session.getAttribute("numeroSecreto");
    String pistasStr = (String) session.getAttribute("pistas");

    if (numeroSecreto == null) {
        numeroSecreto = new Random().nextInt(100) + 1;
        session.setAttribute("numeroSecreto", numeroSecreto);
        pistasStr = "";
        session.setAttribute("pistas", pistasStr);
    }

    String mensaje = "";
    boolean acertado = false;
    String intentoStr = request.getParameter("intento");

    if (intentoStr != null) {
        String[] intentos = intentoStr.split(",");
        for (String intentoTexto : intentos) {
            intentoTexto = intentoTexto.trim();
            try {
                int intento = Integer.parseInt(intentoTexto);
                if (intento < numeroSecreto) {
                    mensaje += "El numero es mayor que " + intento + "<br>";
                    pistasStr += "Intentó: " + intento + " - El numero es mayor,";
                } else if (intento > numeroSecreto) {
                    mensaje += "El numero es menor que " + intento + "<br>";
                    pistasStr += "Intentó: " + intento + " - El numero es menor,";
                } else {
                    mensaje += "Has acertado. El numero era " + numeroSecreto + "<br>";
                    pistasStr += "Intentó: " + intento + " - Correcto,";
                    acertado = true;
                    break;
                }
            } catch (NumberFormatException e) {
                mensaje += intentoTexto + " no es un numero valido.<br>";
            }
        }
        session.setAttribute("pistas", pistasStr);
    }

    String[] pistasArray = pistasStr.equals("") ? new String[0] : pistasStr.split(",");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Numero secreto</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="container">
        <h2 class="titulo">Adivina el numero entre 1 y 100</h2>
        <div class="calculadora-container">
            <div class="calculadora-card">
                <h3> El orenador pensará un número y tu debes de adivinarlo, introduce un número del 1 al 100</h3>
                <form method="post">
                    <% if (!acertado) { %>
                        <input type="text" name="intento"/>
                        <button type="submit">Comprobar</button>
                    <% } else { %>
                        <br><a href="../index.html" class="volver">Volver al indice</a>
                    <% } %>
                </form>

                <p><strong><%= mensaje %></strong></p>

                <h3>Historial de pistas</h3>
                <table class="table">
                    <tr><th>Intento nº</th><th>Pista</th></tr>
                    <% for (int i = 0; i < pistasArray.length; i++) { %>
                        <tr>
                            <td><%= i + 1 %></td>
                            <td><%= pistasArray[i] %></td>
                        </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
</body>
</html>