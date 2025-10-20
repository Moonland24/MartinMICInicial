<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/styles.css">
    <title>Resultado</title>
</head>
<body>
    <div class="container card">
        <h1 class="titulo">Resultado</h1>

        <%
            double precio = Double.parseDouble(request.getParameter("precio"));
            double entregado = Double.parseDouble(request.getParameter("entregado"));
            double cambio = Math.round((entregado - precio) * 100.0) / 100.0;

            if (cambio < 0) {
        %>
                <div class="mensaje">Importe entregado insuficiente.</div>
                <div class="botones">
                    <a href="../html/cambio.html" class="volver boton">Otra operación</a>
                </div>
        <%
            } else if (cambio == 0) {
        %>
                <div class="mensaje">Importe exacto. No hay cambio que devolver.</div>
                <div class="botones">
                    <a href="../html/cambio.html" class="volver boton">Menú inicial</a>
                </div>
        <%
            } else {
                double valor;
                int cantidad;
        %>

        <table class="tabla-cambio">
            <tr>
                <th>Billete / Moneda</th>
                <th>Cantidad</th>
            </tr>

        <%
                double[] valores = {500, 200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01};
                String[] imagenes = {
                    "500euro.png","200euro.png","100euro.png","50euro.png","20euro.png",
                    "10euro.png","5euro.png","2euro.png","1euro.png","50cent.png","20cent.png",
                    "10cent.png","5cent.png","2cent.png","1cent.png"
                };

                cambio = Math.round(cambio * 100.0) / 100.0;
                double[] billetes = {500,200,100,50,20,10,5,2,1,0.5,0.2,0.1,0.05,0.02,0.01};
                String[] nombres = {"500 €","200 €","100 €","50 €","20 €","10 €","5 €","2 €","1 €","0.50 €","0.20 €","0.10 €","0.05 €","0.02 €","0.01 €"};
                for (int i = 0; i < billetes.length; i++) {
                    valor = billetes[i];
                    cantidad = 0;
                    while (cambio >= valor - 0.001) {
                        cantidad++;
                        cambio -= valor;
                        cambio = Math.round(cambio * 100.0) / 100.0;
                    }
                    if (cantidad > 0) {
        %>
                        <tr>
                            <td><img src="../imagenes/<%=imagenes[i]%>" alt="<%=nombres[i]%>"></td>
                            <td><%=cantidad%></td>
                        </tr>
        <%
                    }
                }
        %>
        </table>

        <div class="total">Total a devolver: <%= String.format("%.2f", (entregado - precio)) %> €</div>

        <div class="botones">
            <a href="../html/cambio.html" class="volver boton">Otra operación</a>
            <a href="../index.html" class="volver boton">Menú inicial</a>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>

<!-- Que va esta genial creo que no tengo mensajes de errror jajaja ah pos si tengo -->