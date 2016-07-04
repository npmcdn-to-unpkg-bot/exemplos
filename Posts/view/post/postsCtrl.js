//angular.module("app-post", ['ngRoute']);
angular.module("app-post").controller("postCtrl", function ($scope, postAPI) {
	$scope.posts = [];
	$scope.nomeUsuarioLogado = localStorage.nomeUsuarioLogado

	var listarPosts = function () {
		postAPI.buscaPosts().then(function (data) {
			$scope.posts = data.data;	
		});
	};

	var inicializarPost = function (post) {
		post.usuario = {};
		post.usuario.usuarioId = localStorage.usuarioId;
	};

	$scope.inserirPost = function (post) {
		inicializarPost(post);
		postAPI.inserirPost(post).then(function (data) {
			listarPosts();
			$("#myModal").modal("hide");
		});	
	};

	$scope.abrirModal = function () {
		$("#myModal").modal("show");
	};

	listarPosts();
});