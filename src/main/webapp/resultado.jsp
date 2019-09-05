<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@page import="com.teste.leonardo.App" %>
<%
String contextPath = request.getContextPath();
//out.println(contextPath);
%>
<!DOCTYPE html>
<html>
<head>
	<title>Encurtador de URL</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
	<body>

	<!-- Paste this code after body tag -->
	<div class="se-pre-con">
	<video autoplay muted loop id="myVideo" preload="auto">
	  <source src="images/black_cube.webm" type="video/webm">
	  <source src="images/video.mp4" type="video/mp4">
	</video>
	</div>
	<!-- Ends -->
	<main>
	  <h1>Encurtador de Link</h1>
	  <input type="text" id="input" name="url" placeholder="Insira ou digite sua URL">
	  <div class="botoes">
		<button id="refazUrl">Refazer</button>
		<button id="encurtador">Encurtar</button>
	  </div>
	  <section id="section">
	  <%
  
	App teste = new App(); 
    List<App> list= null;
    
    //teste.insere();
    //teste.insere(Leonardo, leal);
    //teste.atualiza(8);
    //teste.deleta(15);
    
    //list = teste.lista();
    
    //teste.lista();
    for(int i = 0; i < list.size(); i++) {
	 //  out.println("Encurtado: " + list.get(i).getHash() + "<br/>" );
	}
    %>
    </section>
	</main>
	<%
	out.println("<link rel='stylesheet' href='"+contextPath+"/CSS/boilerplate.min.css' type='text/css'>");
	out.println("<link rel='stylesheet' href='"+contextPath+"/CSS/curta.css' type='text/css'>");
	out.println("<script src='"+contextPath+"/JS/jquery-3.2.1.min.js' type='text/javascript'></script>");
	%>
	
	<script type="text/javascript">
		$(window).on("load", function (e) {
		// Animar para fade out
			$(".se-pre-con").fadeOut("slow");
		});
	</script>
</body>
</html>
