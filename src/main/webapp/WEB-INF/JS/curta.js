encurtada = URL + hash;
var metodo = "", atualiza = false;

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
	if(atualiza == false){
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
	}else{
		if($('#input').val()!= original){
			original = $('#input').val();
			console.log(hash+" "+ metodo + " "+original);
			$.post("PonteEncurta",{
				hash: hash,
				metodo: metodo,
				url: original
			});// colocar function em algum lugar por aqui
			atualiza = false;
		}else{
			alert("URL Nao foi alterada!");
		}
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
	metodo = "delete";
	hash = $(this).attr('name');
	$.post("PonteEncurta",{
		hash: hash,
		metodo: metodo
	});
	$("#" + hash).remove();
	$("." + hash ).remove();
	//$(".del input[value='" + hash + "']").remove();
	//$(".alter input[value='" + hash + "']").remove();
});
$(".alter").click(function(){
	metodo = "update";
	hash = $(this).attr('name');
	original = $('.'+hash).val();
	$('#input').val(original);
	atualiza = true;
	/*$.post("PonteEncurta",{
		hash: hash,
		metodo: metodo
	});*/
	//$("#" + hash).remove();
	//$("." + hash ).remove();
	//$(".del input[value='" + hash + "']").remove();
	//$(".alter input[value='" + hash + "']").remove();
});

