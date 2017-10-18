<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="usuario.Usuario" %>
 <%@ page import="nivel.Nivel" %>
 <%@ page import="disp.Dispositivos" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/estilo.css">
<title>TCC Zonete 2013</title>
</head>
<body class='txt1'>
<%
if (session.getAttribute("codiuser") == null){
	out.print("<script>alert('Acesso Restrito!');</script>");
	out.print("<meta http-equiv=\"refresh\" content=\"0;URL=login.jsp\">'");
	out.close();
}
Usuario user = new Usuario();
user.CarregaUsuarioID(Integer.parseInt(session.getAttribute("codiuser").toString()));
if(!user.getAdm()){
	out.close();
}

Dispositivos dis = new Dispositivos();
dis.DELETA(Integer.parseInt(request.getParameter("cod").toString()));

	out.print("<script>alert('Usuário excluido com sucesso!')</script>");
	out.print("<meta http-equiv=\"refresh\" content=\"0;URL=disp.jsp\">'");
	out.close();



%>
<center>

 

</center>



</body>
</html>