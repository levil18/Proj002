<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@page import="com.teste.levil.PonteEncurta" %>
<%
	String contextPath = request.getContextPath(), URL = "levil.com.br/", original = "", hash="";
	//out.println(contextPath);
	if(null != request.getAttribute("original")){
        original = request.getAttribute("original").toString();
    }
	if(null != request.getAttribute("hash")){
		hash = request.getAttribute("hash").toString();
 	}
    PonteEncurta links = new PonteEncurta();
    List<PonteEncurta> list= null;
    list = links.lista();
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
	  <input type="text" id="input" name="url" placeholder="Insira ou digite sua URL" value=<%=original%>>
	  <div class="botoes">
	  		<input type="submit" name="encurtador" value="Encurtar" id="encurtador">
			<input type="button" name="refazUrl" value="Refazer" id="refazUrl">
			<input type="button" name="mostraUrls" value="Mostrar URLs" id="mostraUrls"/>
	  </div>
	  </form>
	  <section id="section">
	  	<article class="article">
	  		<header><h3>Link encurtado</h3></header>
	  	 	 <%
			    for(int i = 0; i < list.size(); i++) {
				   out.println(URL + list.get(i).getHash() + "<br/>" );
				}
    		%>
    	</article>
		 <article class="article">
		 	<header><h3>Link original</h3></header>
	  	 	 <%
			    for(int i = 0; i < list.size(); i++) {
				   out.println( list.get(i).getOriginal() + "<br/>" );
				}
    		%>
    	</article>
	  </section>
	</main>
	<%
	out.println("<link rel='stylesheet' href='"+contextPath+"/CSS/boilerplate.min.css' type='text/css'>");
	out.println("<link rel='stylesheet' href='"+contextPath+"/CSS/curta.css' type='text/css'>");
	out.println("<script src='"+contextPath+"/JS/jquery-3.2.1.min.js' type='text/javascript'></script>");
	out.println("<script src='"+contextPath+"/JS/curta.js' type='text/javascript'></script>");
	%>
	<script type="text/javascript">
		var original, hash, URL;
		original = <%=original%>
		hash = <%=hash%>
		URL = <%=URL%>
	</script>
</body>
</html>
