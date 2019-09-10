$(window).on("load", function (e) {
// Animar para fade out
	$(".se-pre-con").fadeOut("slow");
});

$('#mostraUrls').click(function() {
	//alert("clicado!");
	$('.article').fadeIn("slow");
});

$(".del").click(function(){
	var hash = $(this).attr('name'), metodo = "delete";
	$.post("PonteEncurta",{
		hash: hash,
		metodo: metodo
	});
});
