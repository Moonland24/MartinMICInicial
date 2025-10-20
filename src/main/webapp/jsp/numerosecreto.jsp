<%@ page import="java.util.*" %>
<%
    Integer numeroSecreto = (Integer) session.getAttribute("numeroSecreto");
    String pistasStr = (String) session.getAttribute("pistas");
    Integer limiteInferior = (Integer) session.getAttribute("limiteInferior");
    Integer limiteSuperior = (Integer) session.getAttribute("limiteSuperior");

    if (numeroSecreto == null) {
        numeroSecreto = new Random().nextInt(100) + 1;
        session.setAttribute("numeroSecreto", numeroSecreto);
        pistasStr = "";
        session.setAttribute("pistas", pistasStr);
        limiteInferior = 1;
        limiteSuperior = 100;
        session.setAttribute("limiteInferior", limiteInferior);
        session.setAttribute("limiteSuperior", limiteSuperior);
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
                    if (intento >= limiteInferior) {
                        limiteInferior = intento + 1;
                        session.setAttribute("limiteInferior", limiteInferior);
                    }
                    mensaje += "El número está entre " + limiteInferior + " y " + limiteSuperior + "<br>";
                    pistasStr += "Intentó: " + intento + " - El número está entre " + limiteInferior + " y " + limiteSuperior + ",";
                } else if (intento > numeroSecreto) {
                    if (intento <= limiteSuperior) {
                        limiteSuperior = intento - 1;
                        session.setAttribute("limiteSuperior", limiteSuperior);
                    }
                    mensaje += "El número está entre " + limiteInferior + " y " + limiteSuperior + "<br>";
                    pistasStr += "Intentó: " + intento + " - El número está entre " + limiteInferior + " y " + limiteSuperior + ",";
                } else {
                    mensaje += "Has acertado. El número era " + numeroSecreto + "<br>";
                    pistasStr += "Intentó: " + intento + " - Correcto,";
                    acertado = true;
                    break;
                }
            } catch (NumberFormatException e) {
                mensaje += intentoTexto + " no es un número válido.<br>";
            }
        }
        session.setAttribute("pistas", pistasStr);
    }

    String[] pistasArray = pistasStr.equals("") ? new String[0] : pistasStr.split(",");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Numero secreto</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="container">
        <h2 class="titulo">Adivina el número entre 1 y 100</h2>
        <div class="calculadora-container">
            <div class="calculadora-card">
                <h3>El ordenador pensará un número y tú debes adivinarlo. Introduce un número del 1 al 100:</h3>
                <form method="post">
                    <% if (!acertado) { %>
                        <input type="text" name="intento"/>
                        <button type="submit">Comprobar</button>
                    <% } else { %>
                        <br><a href="../index.html" class="volver">Volver al índice</a>
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