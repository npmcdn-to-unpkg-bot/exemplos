angular.module("app-post").config(function ($routeProvider) {
	$routeProvider.when("/post", {
		templateUrl: "view/post/post.html",
		controller: "postCtrl"
	});

	$routeProvider.when("/login", {
		templateUrl: "view/login/login.html",
		controller: "loginCtrl"
	});

	$routeProvider.when("/detalhesPost/:id", {
		templateUrl: "view/post/detalhesPost.html",
		controller: "detalhesPostCtrl",
		resolve: {
			postAberto: function (postAPI, $route) {
				var id = $route.current.params.id;
				return postAPI.buscaPostPorId(id);
			}
		}
	});

	$routeProvider.otherwise({
		redirectTo: "/post"
	});
});