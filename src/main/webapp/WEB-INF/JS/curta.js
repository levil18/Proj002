$(window).on("load", function (e) {
// Animar para fade out
	$(".se-pre-con").fadeOut("slow");
});

$('#mostraUrls').click(function() {
	//alert("clicado!");
	$('.article').fadeIn("slow");
	$("main").css("height", "auto");
});