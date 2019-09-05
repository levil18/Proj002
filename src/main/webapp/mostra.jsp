<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teste input Hibernate</title>
</head>
<body>
<%
/*String contextPath = request.getContextPath();
out.println(contextPath);*/
%>

<form action="TesteEntity" method="post">
    <p>Nome:         
    <input type="text" name="nome" /></p>

    <p>Senha:        
    <input type="password" name="senha" /></p>

    <p>Botão de Confirmação.
    <input type="submit" name="submit" value="submit" /></p>
</form>
</body>
</html>