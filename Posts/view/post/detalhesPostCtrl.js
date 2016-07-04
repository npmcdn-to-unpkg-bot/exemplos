//angular.module("app-post", ['ngRoute']);
angular.module("app-post").controller("detalhesPostCtrl", function ($scope, postAberto, comentarioAPI, $location) {
	$scope.postAberto = postAberto.data;
	$scope.comentarios = $scope.postAberto.comentarios;
	
	var inicializarComentario = function (comentario) {
		comentario.usuario = {};
		comentario.usuario.usuarioId = localStorage.usuarioId;
		comentario.usuario.nome = localStorage.nomeUsuarioLogado;
		comentario.post = {};
		comentario.post.postId = $scope.postAberto.postId;
		comentario.dataDoComentario = new Date();
	};

	$scope.inserirComentario = function (comentario) {
		inicializarComentario(comentario);
		comentarioAPI.inserirComentario(comentario).then(function (data) {
			$scope.comentarios.push(angular.copy(comentario));
			delete $scope.comentario;
		});	
	};

	$scope.voltar = function () {
		$location.path("/post");
	};
});