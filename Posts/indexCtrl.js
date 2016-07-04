//angular.module("app-post", ['ngRoute']);
angular.module("app-post").controller("indexCtrl", function ($scope) {

	$scope.nomeUsuarioLogado = localStorage.nomeUsuarioLogado
});