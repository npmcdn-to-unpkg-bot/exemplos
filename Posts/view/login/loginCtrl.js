//angular.module("app-post", ['ngRoute']);
angular.module("app-post").controller("loginCtrl", function ($scope, loginAPI, $location) {

	$scope.logar = function (cpf, senha) {
		loginAPI.buscarUsuario(cpf, senha).then(function (data) {
			localStorage.usuarioId = data.data.usuarioId;
			localStorage.nomeUsuarioLogado = data.data.nome;
			$location.path("/post");
		});
	};
});