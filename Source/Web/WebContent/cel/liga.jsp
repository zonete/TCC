<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="arduino.Arduino"%>
<%@page import="disp.Dispositivos"%>
 <%
 
        String id = request.getParameter("id");
 		String codus = request.getParameter("codus");
        Arduino ard = new Arduino();
        ard.abriporta();
        Dispositivos disp = new Dispositivos();
        disp.CarregaPortaID(Integer.parseInt(id));
        ard.liga(disp.getPorta(), Integer.parseInt(codus), disp.getCodiDisp());
        ard.fechaporta();
%>