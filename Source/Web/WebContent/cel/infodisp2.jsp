<%@page import="usuario.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="arduino.Arduino"%>
<%@page import="disp.Dispositivos"%>
 <%
 
 		String bt[],arr[];       
  		
 		String user = request.getParameter("user");
 		
        Dispositivos disp = new Dispositivos();
        Usuario u = new Usuario();
        u.CarregaUsuarioID(Integer.parseInt(user));
        String txt = disp.ListaDispUser2(u);
        
        out.print(txt);
        out.close();
        out.print("aki2");
        out.print("<br><br>");
        int i;
        arr = txt.split("#");
        out.print(arr[0]);        
        
        for (i=0;i < arr.length;i++ ){
            txt = arr[i];
            bt = txt.split(";");
           out.print(i);
           out.print("<br>");        
           out.print(bt[0]);
           out.print("<br>");
           out.print(bt[1]);
           out.print("<br>");
           out.print(bt[2]);
           out.print("<br>");
           out.print(bt[3]);
           out.print("<br> --- ---- --- --- <br>");
           
           }
        
%>