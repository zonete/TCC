<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="arduino.Arduino"%>
<%@page import="disp.Dispositivos"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teste Bateria</title>
</head>
<body>
 <%

        Arduino ard = new Arduino();
        ard.abriporta();
        ard.testee();
        ard.fechaporta();
        
%>
2
</body>
</html>