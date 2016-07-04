angular.module("app-post").factory("postAPI", function ($http) {
	var url = "http://localhost:8080/SenacAppBarramento/Post";

	var _buscaPosts = function () {
		return $http.get(url);
	};

	var _buscaPostPorId = function (id) {
		return $http.get(url + "/" + id);
	};

	var _inserirPost = function (post) {
		return $http.post(url, post);
	};

	return {
		buscaPosts: _buscaPosts,
		buscaPostPorId: _buscaPostPorId,
		inserirPost: _inserirPost
	};
});