/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Inmaculada
 */
@WebServlet(name = "ProcesaFormIntermedio", urlPatterns = {"/procesaformintermedio"})
public class ProcesaFormIntermedio extends HttpServlet {
    
     /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String[] OPCIONES = {"cine", "baile", "musica", "deporte", "viajes"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha_nacimiento");
        String salario = request.getParameter("salario");
        String hijos = request.getParameter("hijos");
        String[] preferencias = request.getParameterValues("preferencias");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'><head><meta charset='UTF-8'><title>Formulario con Validación</title></head><body>");
            out.println("<div class='container'>");
            out.println("<h1>Mismo formulario validado</h1>");
            String action = request.getContextPath() + "/procesaformintermedio";
            out.println("<form action='" + action + "' method='post' id='miFormulario'>");
            out.println("<label for='nombre'>Nombre:</label><br>");
            out.println("<input type='text' name='nombre' id='nombre' value='" + (nombre != null ? nombre : "") + "'><br><br>");
            out.println("<label for='fecha'>Fecha de nacimiento:</label><br>");
            out.println("<input type='date' name='fecha_nacimiento' id='fecha' value='" + (fecha != null ? fecha : "") + "'><br><br>");
            out.println("<label for='salario'>Salario:</label><br>");
            out.println("<input type='number' name='salario' id='salario' step='0.01' value='" + (salario != null ? salario : "") + "'><br><br>");
            out.println("<label for='hijos'>Número de hijos:</label><br>");
            out.println("<input type='number' name='hijos' id='hijos' value='" + (hijos != null ? hijos : "") + "'><br><br>");
            out.println("<label>Preferencias (elige ninguna, una o varias):</label><br>");
            for (String op : OPCIONES) {
                boolean checked = (preferencias != null) && Arrays.asList(preferencias).contains(op);
                out.print("<input type='checkbox' name='preferencias' value='" + op + "' " + (checked ? "checked" : "") + "> ");
                out.println("<label>" + op + "</label><br>");
            }
            out.println("<br><input type='submit' value='Enviar'>");
            out.println("</form>");
            out.println("<div style='text-align:center; margin-top:30px;'><a href='" + request.getContextPath() + "/index.html'>Volver al índice</a></div>");
            out.println("</div>"); // container
            out.println("</body></html>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Map<String, String[]> parametros = request.getParameterMap();
        boolean hayError = false;
        if (!parametros.containsKey("nombre") || parametros.get("nombre")[0].trim().isEmpty()) {
            hayError = true;
        }
        if (!parametros.containsKey("fecha_nacimiento") || parametros.get("fecha_nacimiento")[0].isEmpty()) {
            hayError = true;
        } else {
            try {
                new SimpleDateFormat("dd-MM-yyyy").parse(parametros.get("fecha_nacimiento")[0]);
            } catch (ParseException e) {
                hayError = true;
            }
        }
        if (!parametros.containsKey("salario") || parametros.get("salario")[0].trim().isEmpty()) {
            hayError = true;
        }
        if (!parametros.containsKey("hijos") || parametros.get("hijos")[0].trim().isEmpty()) {
            hayError = true;
        }

        try (PrintWriter out = response.getWriter()) {
            if (hayError) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang='es'><head><meta charset='UTF-8'><title>Error</title></head><body>");
                out.println("<h2>Se han encontrado errores en el formulario</h2>");

                String action = request.getContextPath() + "/procesaformintermedio";
                out.println("<form action='" + action + "' method='get'>");
                for (Map.Entry<String, String[]> entry : parametros.entrySet()) {
                    String key = entry.getKey();
                    String[] values = entry.getValue();
                    if (values == null) continue;
                    for (String v : values) {
                        out.println("<input type='hidden' name='" + key + "' value='" + v + "'/>");
                    }
                }
                out.println("<input type='submit' value='Volver al formulario'/>");
                out.println("</form>");
                out.println("</body></html>");
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html lang='es'><head><meta charset='UTF-8'><title>Resultado</title></head><body>");
                out.println("<h1>Datos recibidos correctamente</h1>");
                out.println("<ul>");

                for (Map.Entry<String, String[]> entry : parametros.entrySet()) {
                    String clave = entry.getKey();
                    String[] vals = entry.getValue();
                    out.print("<li><strong>" + clave + ":</strong> ");
                    if (vals != null) {
                        for (int i = 0; i < vals.length; i++) {
                            out.print(vals[i]);
                            if (i < vals.length - 1) out.print(", ");
                        }
                    }
                    out.println("</li>");
                }

                out.println("</ul>");
                out.println("<a href='" + request.getContextPath() + "/index.html'>Volver al índice</a>");
                out.println("</body></html>");
            }
        }
    }
}
