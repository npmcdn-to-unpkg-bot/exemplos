angular.module("app-post").factory("comentarioAPI", function ($http) {
	var url = "http://localhost:8080/SenacAppBarramento/Comentario";

	var _inserirComentario = function (comentario) {
		return $http.post(url, comentario);
	};

	return {
		inserirComentario: _inserirComentario
	};
});