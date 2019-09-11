encurtada = URL + hash;

//quando carregar dar fade de carregamento
$(window).on("load", function (e) {
// Animar para fade out
	$(".se-pre-con").fadeOut("slow");
});
//se receber a hash mostrará o botão de refazer
if("" != hash){
	$("#refazUrl").show();
}
//se botão de rafazer for clicado jogará a URL original no input
$('#refazUrl').click(function() {
	//alert("clicado!");
	$('#input').val(original);
});

//se botão de rafazer for clicado jogará a URL com hash no input
$( "#encurtador" ).click(function() {
	//verifica se é vazio o input
	if($('#input').val()!= ""){
		//se o valor for diferente da url já encurtada
		if($('#input').val()!= encurtada){
			//se a url inserida é diferente da última exibida no input
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

//ação para mostrar as URLs gravadas no BD
$('#mostraUrls').click(function() {
	//alert("clicado!");
	$('.article').fadeIn("slow");
	$('main').css("height", "auto");
});

//ação para deletar uma URL do BD
$(".del").click(function(){
	var hash = $(this).attr('name'), metodo = "delete";
	$.post("PonteEncurta",{
		hash: hash,
		metodo: metodo
	});
	$("#" + hash).remove();
	$("." + hash ).remove();
	//$(".del input[value='" + hash + "']").remove();
	//$(".alter input[value='" + hash + "']").remove();
});


