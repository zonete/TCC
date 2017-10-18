<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.catalina.connector.Request"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="analise.Analises"%>
 <%@ page import="usuario.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/estilo.css">
<link rel="stylesheet" href="jquery/themes/base/jquery.ui.all.css">
        <script src="jquery/js/jquery-1.5.js"></script>
        <script type="text/javascript" src="jquery/js/jquery.tablesorter.js"></script>
    <script src="jquery/js/jquery.maskedinput.js"></script>
	<script src="jquery/ui/jquery.ui.core.js"></script>
	<script src="jquery/ui/jquery.ui.widget.js"></script>
	<script src="jquery/ui/jquery.ui.datepicker.js"></script>
    <script src="jquery/js/jquery.price_format.1.3.js"></script>
    <link rel="stylesheet" href="jquery/themes/blue/style.css" type="text/css" media="print, projection, screen" />

    <script src="../jquery/js/jquery.maskMoney.js" type="text/javascript"></script>

<title>Análise Historico</title>
</script>
  
	<link rel="stylesheet" href="jquery/css/demos.css">
	<script>
	$(function() {
	$( "#datepicker" ).datepicker({
			showOn: "button",
			buttonImage: "jquery/images/calendar.gif",
			dateFormat: 'dd/mm/yy',
            buttonImageOnly: true
            }
             
		);
        $( "#datepicker").mask("99/99/9999",{placeholder:" "});
        $( "#datepicker2" ).datepicker({
			showOn: "button",
			buttonImage: "jquery/images/calendar.gif",
			dateFormat: 'dd/mm/yy',
            buttonImageOnly: true
            }
             
		);
        $( "#datepicker2").mask("99/99/9999",{placeholder:" "});
        $( "#tel").mask("9999-9999",{placeholder:" "});
	});
    
$(document).ready(function()
	{
		$("#myTable").tablesorter( {sortList: [[0,0]]} );
	}
);


        

	</script>

</head>

<body>
<%
String di;
String df;
di="";
df="";
if(request.getParameter("dataini") != null){

di = request.getParameter("dataini").toString();
df = request.getParameter("datafim").toString();

	
}
%>
<center>
<form name="relatorio" action="analisehistorico.jsp" method="get">
Inicio: <input name="dataini" id="datepicker" size="10" <% out.println("value='"+di+"'"); %> />
Fim: <input name="datafim" id="datepicker2" size="10"  <% out.println("value='"+df+"'"); %>/>
<br><br>
<input type="submit" value="Gerar">
</form>
</center>

<%
if (session.getAttribute("codiuser") == null){
	out.print("<script>alert('Acesso Restrito!');</script>");
	out.print("<meta http-equiv=\"refresh\" content=\"0;URL=login.jsp\">'");
	out.close();
}

Usuario user = new Usuario();
user.CarregaUsuarioID(Integer.parseInt(session.getAttribute("codiuser").toString()));
if(request.getParameter("dataini") == null){
	
	out.close();
}

Analises an = new Analises();


String dataini = request.getParameter("dataini").toString();
String datafim = request.getParameter("datafim").toString();
out.println("<BR>");
out.println("<b>Data Inicio:</b> "+dataini);
out.println("<BR>");
out.println("<b>Data Final :</b> "+datafim);


SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

Date dini = sdf1.parse(dataini);
Date dfim = sdf1.parse(datafim);
//out.println("a");
out.println(an.AnaliseHistorico(dini, dfim));
%>

<br><br>
<center>
<a href="index.jsp" class='classname'> Home </a>
</center>
</body>
</html>