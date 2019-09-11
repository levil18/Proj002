<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@page import="com.teste.levil.PonteEncurta" %>
<%
	String contextPath = request.getContextPath(), URL = "levil.com.br/", original = "", hash="", encurtada="";
	//out.println(contextPath);
	if(null != request.getAttribute("original")){
        original = request.getAttribute("original").toString();
    }
	if(null != request.getAttribute("hash")){
		hash = request.getAttribute("hash").toString();
		encurtada = URL+hash;
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
	</div>
	<!-- Fim -->
	<main>
	  	<svg class="LVL-hello" viewBox="-285 -285 1000 1000">

			<!--<g transform="matrix(1.25 0 0 -1.25 0 750)"> -->
				<path  fill="#FFF" stroke-miterlimit="10" d="M-48.8 429.6v-384.8l247.2-175.2h0.2v560H-48.8zM16.1 83.2v287h48.7v-323.6C64.7 46.5 16.1 83.2 16.1 83.2zM83.7 36.4v97.7l54.2-24.3L135 6.1 83.7 36.4z"/>
				<path fill="#FFF" d="M209.4 429.6v-560L466.2 39.9v389.7H209.4zM294.6 370.1h51.1V38l-51-38.5C294.7-0.5 294.6 370.1 294.6 370.1zM366.2 136.4l48.7 24.3v-81.4L366.2 49.1V136.4L366.2 136.4z"/>
			<!--</g>-->
		</svg>
	  <h1>Encurtador de Link</h1>
	  <form id="gera_curta" action="PonteEncurta" method="post">
	  <input type="text" id="input" name="url" placeholder="Insira ou digite sua URL" value="<%=encurtada%>" />
	  <div class="botoes">
	  		<input type="button" name="encurtador" value="Encurtar" id="encurtador" />
			<input type="button" name="refazUrl" value="Refazer" id="refazUrl" />
			<input type="button" name="mostraUrls" value="Mostrar URLs" id="mostraUrls" />
	  </div>
	  </form>
	  <section id="section">
	  	<article class="article">
	  		<header><h3>Link encurtado</h3></header>
	  	 	 <%
			    for(int i = 0; i < list.size(); i++) {
				   out.println("<p id='"+list.get(i).getHash()+"'>" + URL + list.get(i).getHash() + "</p>" );
				}
    		%>
    	</article>
		 <article class="article">
		 	<header><h3>Link original</h3></header>
			<form id="altera_curta" action="PonteEncurta" method="post">
	  	 	 <%
			    for(int i = 0; i < list.size(); i++) {
				   out.println("<input type='text' class='links "+list.get(i).getHash()+"' value='" + list.get(i).getOriginal() + "' readonly />" );
				   out.println("<input type='button' value='' name='"+list.get(i).getHash()+"' class='alter " + list.get(i).getHash() + "' />");
				   out.println("<input type='button' value='' name='"+list.get(i).getHash()+"' class='del " + list.get(i).getHash() + "' />");
				}
    		%>
			</form>
    	</article>
	  </section>
	</main>
	<style><%@include file="/WEB-INF/CSS/boilerplate.min.css"%></style>
	<style><%@include file="/WEB-INF/CSS/curta.css"%></style>
	<script type='text/javascript'><%@include file="/WEB-INF/JS/jquery-3.2.1.min.js"%></script>
	<script type="text/javascript">
		var original, hash, URL;
		original = "<%=original%>";
		hash = "<%=hash%>";
		URL = "<%=URL%>";
	</script>
	<script type='text/javascript'><%@include file="/WEB-INF/JS/curta.js"%></script>
</body>
</html>
