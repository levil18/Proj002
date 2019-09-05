<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@page import="com.teste.hibernate.TesteEntity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; 
      charset=ISO-8859-1">
     <title>Página de Teste com Hibernate</title>
  </head>
  <body>Teste de Inserção e Listagem de Dados no Console
    <br/><br/>
  
    <%
  
    TesteEntity teste = new TesteEntity();
    List<TesteEntity> list= null;
    
    //teste.insere();
    //teste.insere(Leonardo, leal);
    //teste.atualiza(8);
    //teste.deleta(15);
    list = teste.lista();
    //teste.lista();
    for(int i = 0; i < list.size(); i++) {
	   out.println("Usuário: " + list.get(i).getNome() + "<br/>" );
	}
    %>
  <!-- <s:property value="TesteEntity.lista()" /> -->
  </body>
                                                 
</html>