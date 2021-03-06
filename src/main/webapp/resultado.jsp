<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@page import="com.teste.levil.PonteEncurta" %>
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
	<!-- Preload -->
	<div class="se-pre-con">
	<video autoplay muted loop id="myVideo" preload="auto">
	  <source src="images/black_cube.webm" type="video/webm">
	  <source src="images/video.mp4" type="video/mp4">
	</video>
	</div>
	<!-- Fim -->
	<main>
	  <h1>Encurtador de Link</h1>
	  <form action="PonteEncurta" method="post">
	  <input type="text" id="input" name="url" placeholder="Insira ou digite sua URL"  value=<%=request.getAttribute("original").toString()%>/>
	  <div class="botoes">
		<input type="submit" name="encurtador" value="Encurtar" id="encurtador"/>
		<input type="button" name="refazUrl" value="Refazer" id="refazUrl"/>
		<input type="button" name="mostraUrls" value="Mostrar URLs" id="mostraUrls"/>
	  </div>
	  </form>
	  <section id="section">
	  
	  <%
		    PonteEncurta links = new PonteEncurta();
		    List<PonteEncurta> list= null;
		    
		    //links.insere();
		    //links.insere("3tpg6", "google.com.br");
		    //links.insere("zpnns8a", "www.primevideo.com");
		    //links.insere("6gvvov", "www.youtube.com/");
		    
		    //links.deleta("zpnns8a");
		    //links.atualiza("6gvvov","zpnns8a", "www.primevideo.com");
		    list = links.lista();
		    //links.lista();
		    for(int i = 0; i < list.size(); i++) {
			   out.println("Link encurtado: levil.com.br/" + list.get(i).getHash() + "<br/>" );
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
