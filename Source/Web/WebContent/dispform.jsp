<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="usuario.Usuario" %>
 <%@ page import="disp.Dispositivos" %>
 <%@ page import="nivel.Nivel" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/estilo.css">
<title>Disp - TCC = Zonete 2013</title>
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
		
			Dispositivos di = new Dispositivos();
			di.setDescDisp(request.getParameter("desc").toUpperCase().toString());
			di.setPorta(request.getParameter("port").toUpperCase().toString());
			di.GRAVA(Integer.parseInt(request.getParameter("nivel").toString()));
 	
	}else{
		
		Dispositivos di = new Dispositivos();
		di.setDescDisp(request.getParameter("desc").toUpperCase().toString());
		di.setPorta(request.getParameter("porta").toUpperCase().toString());
		di.setCodiDisp(Integer.parseInt(request.getParameter("codi").toUpperCase().toString()));
		
		di.ALTERA(Integer.parseInt(request.getParameter("nivel").toString()));
		
		 
		
	}
	
	
	
	//out.close();
}
Erro = "<font color=red>"+Erro+"</font>";

String acaoform="i";
Dispositivos dis = new Dispositivos();


if (request.getParameter("cod") == null){
    dis.setDescDisp("");
    dis.setPorta("");
	
	acaoform="i";
}else{
	
	acaoform = "a";
}





%>
<center>
<a href="disp.jsp" class='classname'> <- Voltar </a>
<form name="form1" method="post" action="dispform.jsp">
<input type="hidden" name="codigodisp" value="<% out.print(dis.getCodiDisp()); %>"> 
<input type="hidden" name="acao" value="<% out.print(acaoform); %>">
<table class="form">
<tr><td  colspan="2" align="center"><h3>Dispositivo</h3></td></tr>
<tr>
<td>Descricao:</td>
<td><input type="text" name="desc" id="desc" size="40" value='<% out.print(dis.getDescDisp()); %>' > </td>
</tr>
<tr>
<td>Porta:</td>
<td><input type="text" name="port" id="port" size="5" maxsize="1" value='<% out.print(dis.getPorta()); %>'> </td>
</tr>
<%
if (acaoform == "i"){
%>
<tr>
<td>Nivel:</td>
<td><% Nivel nvl = new Nivel();  
	  out.print(nvl.ListaNiveis(0));	
%></td>
</tr>
<%} %>

<tr><td> </td><td><input type="submit" value="Gravar"> </td></tr>
</table>
</form>
<%
out.print(Erro);
%>





</center>
</body>
</html>