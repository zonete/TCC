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
 <link rel="stylesheet" href="demos.css" type="text/css" media="screen" />
    
    <script src="RGraph/libraries/RGraph.common.core.js" ></script>
    <script src="RGraph/libraries/RGraph.common.dynamic.js" ></script>
    <script src="RGraph/libraries/RGraph.common.tooltips.js" ></script>
    <script src="RGraph/libraries/RGraph.pie.js" ></script>
    <!--[if lt IE 9]><script src="RGraph/excanvas/excanvas.js"></script><![endif]-->
    
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

<title>Análise Resumo</title>
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
<form name="relatorio" action="analiseresumida.jsp" method="get">
Inicio: <input name="dataini" id="datepicker" size="10" <% out.println("value='"+di+"'"); %> />
Fim: <input name="datafim" id="datepicker2" size="10"  <% out.println("value='"+df+"'"); %>/>
<input type="submit" value="Gerar">
</form>

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
out.println(an.AnaliseResumida(dini, dfim));
%>

<br>
<table width=100%><tr><td align="center">
<h3>Total Horas Acionado</h3>
 <canvas id="cvs" width="350" height="250">[No canvas support]</canvas>
    
    <script>
        
            var pie = new RGraph.Pie('cvs', [<%  out.println(an.getV2()); %>])
                .Set('tooltips', [<%  out.println(an.getV_2()); %>])
                .Set('labels', [<%  out.println(an.getTitulo()); %>])
                .Set('shadow', true)
                .Draw();
        
    </script>


</td>

<td align="center">
<h3>Média Periodo Ligado </h3>
 <canvas id="cvs2" width="350" height="250">[No canvas support]</canvas>
    
    <script>
        
            var pie2 = new RGraph.Pie('cvs2', [<%  out.println(an.getV3()); %>])
                .Set('tooltips', [<%  out.println(an.getV_3()); %>])
                .Set('labels', [<%  out.println(an.getTitulo()); %>])
                .Set('shadow', true)
                .Draw();
        
    </script>


</td>


<td align="center">
<h3>Quantidade Acionamento </h3>
 <canvas id="cvs3" width="350" height="250">[No canvas support]</canvas>
    
    <script>
            var pie3 = new RGraph.Pie('cvs3', [<%  out.println(an.getV1()); %>])
                .Set('tooltips', [<%  out.println(an.getV_1()); %>])
                .Set('labels', [<%  out.println(an.getTitulo()); %>])
                .Set('shadow', true)
                .Draw();
        
    </script>


</td>


</tr></table>
<br>
<center>
<a href="index.jsp" class='classname'> Home </a>
</center>
</body>
</html>