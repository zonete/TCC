<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="usuario.Usuario" %>
 <%@ page import="nivel.Nivel" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/estilo.css">
<title>Usuário - TCC = Zonete 2013</title>
</head>
<body>
<%
if (session.getAttribute("codiuser") == null){
	out.print("<script>alert('Acesso Restrito!');</script>");
	out.print("<meta http-equiv=\"refresh\" content=\"0;URL=login.jsp\">'");
	out.close();
}
Usuario user1 = new Usuario();
user1.CarregaUsuarioID(Integer.parseInt(session.getAttribute("codiuser").toString()));
if(!user1.getAdm()){
	out.close();
}
String Erro = "";
Boolean er = false;
if (request.getParameter("acao") != null){
	
	//out.print(request.getParameter("acao").toString());
	if (request.getParameter("acao").toString().equals("i")){
		//inserir
		out.print("aki");
		if(!request.getParameter("pass1").equals(request.getParameter("pass2"))){
			Erro = Erro + "Senhas não conferem <br>";
			er = true;
		}
		if (!er){
			Usuario usuar = new Usuario();
			//usuar.setAdm()
			usuar.setLogin(request.getParameter("login").toUpperCase().toString());
			usuar.setNome(request.getParameter("nome").toUpperCase().toString());
			usuar.setSenha(request.getParameter("pass1").toString());
			usuar.setNivel(Integer.parseInt(request.getParameter("nivel").toString()));
			//out.print(usuar.getNivel());
			 
			if (usuar.VerificaExisteLogin()){
				Erro = Erro + "Login já existe";
				er = true;
			}else{
				usuar.GRAVA();
				out.print("<script>alert('Usuário cadastrado com Sucesso!')</script>");
			} 
			
		}
		
	}else{
		//edit
		Usuario usuar = new Usuario();
		//usuar.setAdm()
		usuar.setLogin(request.getParameter("login").toUpperCase().toString());
		usuar.setNome(request.getParameter("nome").toUpperCase().toString());
		usuar.setNivel(Integer.parseInt(request.getParameter("nivel").toString()));
		usuar.setCodigo(Integer.parseInt(request.getParameter("codigouser").toString()));
		
		//out.print(usuar.getNivel());
		 
		if (usuar.VerificaExisteLoginCod()){
			Erro = Erro + "Login já existe";
			er = true;
		}else{
			usuar.ALTERA();
			out.print("<script>alert('Usuário Alterado com Sucesso!')</script>");
		} 
		
	}
	
	
	
	//out.close();
}
Erro = "<font color=red>"+Erro+"</font>";

String acaoform="i";
Usuario us = new Usuario();
if (request.getParameter("cod") == null){
	us.setAdm(false);
	us.setCodigo(0);
	us.setLogin("");
	us.setNivel(0);
	us.setNome("");
	us.setSenha("");
	acaoform="i";
}else{
	us.CarregaUsuarioID(Integer.parseInt(request.getParameter("cod")));
	acaoform = "a";
}





%>
<center>
<a href="usuario.jsp" class='classname'> <- Voltar </a>
<form name="form1" method="post" action="usuarioform.jsp">
<input type="hidden" name="codigouser" value="<% out.print(us.getCodigo()); %>"> 
<input type="hidden" name="acao" value="<% out.print(acaoform); %>">
<table class="form">
<tr><td  colspan="2" align="center"><h3>Usuário</h3></td></tr>
<tr>
<td>Nome:</td>
<td><input type="text" name="nome" id="nome" size="40" value='<% out.print(us.getNome()); %>' > </td>
</tr>
<tr>
<td>Login:</td>
<td><input type="text" name="login" id="login" size="40" value='<% out.print(us.getLogin()); %>'> </td>
</tr>
<%
if (acaoform == "i"){
%>
<tr>
<td>Senha:</td>
<td><input type="password" name="pass1" id="pass1" size="20" value='<% out.print(us.getSenha()); %>'> </td>
</tr>
<tr>
<td>Confirma:</td>
<td><input type="password" name="pass2" id="pass2" size="20" value='<% out.print(us.getSenha()); %>'> </td>
</tr>
<%} %>
<tr>
<td>Nivel:</td>
<td><% Nivel nvl = new Nivel();  
	  out.print(nvl.ListaNiveis(us.getNivel()));	
%></td>
</tr>
<tr><td> </td><td><input type="submit" value="Gravar"> </td></tr>
</table>
</form>
<%
out.print(Erro);
%>





</center>
</body>
</html>