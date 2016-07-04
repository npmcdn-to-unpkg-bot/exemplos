angular.module("listaTelefonica").directive("uiModal", function ($http) {
	return {
		templateUrl: "view/ui-modal.html",
		restrict: 'AE',
		scope: {
			contatoSelecionado: "=contato",
			titulo: "@",
			fun: "=funcao",
			textoDoBotao: "@textoDoBotao"
		}
	};
});