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
				   out.println("<p>" + URL + list.get(i).getHash() + "</p>" );
				}
    		%>
    	</article>
		 <article class="article">
		 	<header><h3>Link original</h3></header>
			<form id="altera_curta" action="PonteEncurta" method="post">
	  	 	 <%
			    for(int i = 0; i < list.size(); i++) {
				   out.println("<input type='text' class='links' value='" + list.get(i).getOriginal() + "' readonly />" );
				   out.println("<input type='button' value='' name='"+list.get(i).getHash()+"' class='alter' />");
				   out.println("<input type='button' value='' name='"+list.get(i).getHash()+"' class='del' />");
				}
    		%>
			</form>
    	</article>
	  </section>
	</main>
	<style><%@include file="/WEB-INF/CSS/boilerplate.min.css"%></style>
	<style><%@include file="/WEB-INF/CSS/curta.css"%></style>
	<script type='text/javascript'><%@include file="/WEB-INF/JS/jquery-3.2.1.min.js"%></script>
	<script type='text/javascript'><%@include file="/WEB-INF/JS/curta.js"%></script>
	<script type="text/javascript">
		var original, hash, URL;
		original = "<%=original%>";
		hash = "<%=hash%>";
		URL = "<%=URL%>";
		encurtada = URL + hash;
		if("" != hash){
			$("#refazUrl").show();
		}
		$('#refazUrl').click(function() {
			//alert("clicado!");
			$('#input').val(original);
		});
		$( "#encurtador" ).click(function() {
			if($('#input').val()!= ""){
				if($('#input').val()!= encurtada){
					if(original == $('#input').val()){
						$('#input').val(encurtada);
					}else{
						$( "#gera_curta" ).submit();
					}
				}else{
					alert("URL encurtada! Insira uma URL.");
					$('#input').focus();
				}
			}else{
				alert("Campo vazio!");
				$('#input').focus();
			}
		});
	</script>
</body>
</html>
