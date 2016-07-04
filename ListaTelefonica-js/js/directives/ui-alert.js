angular.module("listaTelefonica").directive("uiAlert", function () {
	return {
		templateUrl: "view/ui-alert.html",
		replace: true,
		scope: {
			titulo: "@title",
			conteudo: "=mensagem"
		}
	};
});