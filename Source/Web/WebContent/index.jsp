<%@page import="org.apache.catalina.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="usuario.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zonete TCC2013 </title>
</head>
<body>
<%
if (session.getAttribute("codiuser") == null){
	out.print("<script>alert('Acesso Restrito!');</script>");
	out.print("<meta http-equiv=\"refresh\" content=\"0;URL=login.jsp\">'");
	out.close();
}

 	Usuario user  = new Usuario();
 	user.CarregaUsuarioID(Integer.parseInt(session.getAttribute("codiuser").toString()));
 	//out.print(user.getNivel());

	



%>
<center>
<table border=0 align="center">
<tr><td valign="top" align="center">

<h2>Geral</h2>

<a href="listardisp.jsp" class="menu">Listar Dispositivos</a>
<br><br>
<a href="logout.jsp" class="menu"> Sair </a>
</td>
<td valign="top" align="center">

<h2>Análises</h2>

<a href="analisehistorico.jsp" class="menu">Histórico</a>

<br><br>
<a href="analiseresumida.jsp" class="menu">Resumo </a>
</td>

<td valign="top" align="center">
<%
 if (user.getAdm()){
	 out.print("<h2>Administrativo</h2>");
	 out.print("<a href='disp.jsp' class='menu'> Dispositivos</a>  ");	 
	 out.print("<br><br><a href='usuario.jsp' class='menu' > Usuários</a>  ");
	 
 }
 
	
%>
</td>
</table>
</center>





</body>
</html>