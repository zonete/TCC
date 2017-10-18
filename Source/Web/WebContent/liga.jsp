<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="arduino.Arduino"%>
<%@page import="disp.Dispositivos"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="2;URL=listardisp.jsp">
<title>TCC-2013 LIGAR</title>
</head>
<body>

 <%
 if (session.getAttribute("codiuser") == null){
		out.print("<script>alert('Acesso Restrito!');</script>");
		out.print("<meta http-equiv=\"refresh\" content=\"0;URL=login.jsp\">'");
		out.close();
	}
 
 
        String id = request.getParameter("id");
        Arduino ard = new Arduino();
        ard.abriporta();
        Dispositivos disp = new Dispositivos();
        disp.CarregaPortaID(Integer.parseInt(id));
        ard.liga(disp.getPorta(), Integer.parseInt(session.getAttribute("codiuser").toString()), disp.getCodiDisp());
        ard.fechaporta();
%>
<center>
<h3>Dispositivo ligado com Sucesso</h3>

</center>

</body>
</html>