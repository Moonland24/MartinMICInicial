/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Inmaculada
 */
@WebServlet("/procesaform")
public class ProcesaForm extends HttpServlet {
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String salarioStr = request.getParameter("salario");
        String hijos = request.getParameter("hijos");
        String[] preferencias = request.getParameterValues("preferencias");

        Double salario = null;
        if (salarioStr != null && !salarioStr.trim().isEmpty()) {
            try {
                salario = Double.parseDouble(salarioStr);
            } catch (NumberFormatException e) {
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!doctype html><html lang='es'><head><meta charset='utf-8'><title>Resultado</title></head><body>");
            out.println("<h1>Formulario enviado correctamente</h1>");
            out.println("<p>Se han recibido los datos en el servidor:</p>");
            out.println("<ul>");
            out.println("<li><strong>Nombre:</strong> " + (nombre != null ? nombre : "") + "</li>");
            out.println("<li><strong>Fecha de nacimiento:</strong> " + (fecha != null ? fecha : "") + "</li>");
            out.println("<li><strong>Salario:</strong> " + (salario != null ? salario : "no indicado o inválido") + "</li>");
            out.println("<li><strong>Número de hijos:</strong> " + (hijos != null ? hijos : "") + "</li>");

            out.print("<li><strong>Preferencias:</strong> ");
            if (preferencias == null || preferencias.length == 0) {
                out.print("No se seleccionaron preferencias");
            } else {
                out.print("<ul>");
                for (String p : preferencias) {
                    out.print("<li>" + p + "</li>");
                }
                out.print("</ul>");
            }
            out.println("</li>");
            out.println("</ul>");

            out.println("<p><a href='index.html'>Volver al índice</a></p>");
            out.println("</body></html>");
        }
    }
}
