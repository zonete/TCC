<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="disp.Dispositivos"%>
<%@ page import="usuario.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/estilo.css">
<title>Dispositivos - Zonete - TCC -2013</title>
</head>
<body>
<center>
<a href="index.jsp" class='classname'> Home </a>
<br>
<br>
<%
//Verifica se usuário já está logado
if (session.getAttribute("codiuser") == null){
	//Mostra messagem de Erro
	out.print("<script>alert('Acesso Restrito!');</script>");
	//Encaminha para página de login
	out.print("<meta http-equiv=\"refresh\" content=\"0;URL=login.jsp\">'");
	out.close();
}

Usuario user = new Usuario();
user.CarregaUsuarioID(Integer.parseInt(session.getAttribute("codiuser").toString()));
Dispositivos dis = new Dispositivos();
out.print(dis.ListaDispUser(user));


%>
</center>

</body>
</html>