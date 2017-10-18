<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="usuario.Usuario"%>

	<%
	//out.print("aki");
	if (session.getAttribute("codiuser") != null){
		out.print("<span class='lbl'> Você já está logado! </span>");
		out.print("<meta http-equiv=\"refresh\" content=\"2;URL=index.jsp\">'");
		out.close();
	}
	String Erro = "";
	if( request.getParameter("usuario") != null ) {
		//out.print("Erro!");
		Usuario user = new Usuario();
		user.setLogin(request.getParameter("usuario"));
		user.setSenha(request.getParameter("senha"));
		
		if (user.VerificaLogin()) {
			if(request.getParameter("cel")!=null){
				out.print(user.getCodigo().toString());
				out.close();
				
			}else{
			
				out.print("OK");
				session.setAttribute("codiuser", user.getCodigo());
				session.setAttribute("nomeuser", user.getNome());
				out.print("<meta http-equiv=\"refresh\" content=\"0;URL=index.jsp\">'");
				out.close();
			}
		}
		else{
			if(request.getParameter("cel")!=null){
				out.print("0");
				out.close();
				
			}
			
			Erro = "Login/Senha inválido";
		}
			
		
	}
	
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zonete TCC2013</title>
</head>
	<body>	
	<center>
		<table bgcolor="#F0F0F0" width="250" align="center" height="150">
			
			<tR>
				<td valign="middle" align="center">
				
					<table border=0>
						<tr>
							<td><img alt="" src="img/lock.png">
							</td><td>
								<table border=0>
								<form name="form1" method="post" action="login.jsp">
									<tr>
										<td class="lbl">Usuário:</td>
									</tr>
									<tr>
										<td class="form"><input class="form" type="text"
											name="usuario" size="20"></td>
									</tr>

									<tr>
										<td class="lbl">Senha:</td>
									</tr>
									<tr>
										<td class="form"><input class="form" type="password"
											name="senha" size="20"></td>
									</tr>
									<tr>
										<td align="right"><input type="submit" value="Entrar"></td>
								</form>	
									</tr>
									<tr><td class="erro"><% out.print(Erro); %></td></tr>
																		
								</table>
								
					</table>
				</td>
			</tR>

		</table>
</center>
</body>
</html>