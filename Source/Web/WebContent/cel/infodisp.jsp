<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="arduino.Arduino"%>
<%@page import="disp.Dispositivos"%>
 <%
 
 		String bt[];       
  		String id = request.getParameter("id");
 		String user = request.getParameter("user")	;
        Dispositivos disp = new Dispositivos();
        disp.CarregaPortaID(Integer.parseInt(id));
        String txt = disp.CelListaDisp(Integer.parseInt(user));
        out.print(txt);
        out.print("aki2");
        out.print("<br><br>");
         bt = txt.split(";");
        out.print(bt[0]);
        out.print("<br>");
        out.print(bt[1]);
        out.print("<br>");
        out.print(bt[2]);
        out.print("<br>");
        out.print(bt[3]);
        
%>