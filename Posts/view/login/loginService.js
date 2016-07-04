angular.module("app-post").factory("loginAPI", function ($http) {
	var url = "http://localhost:8080/SenacAppBarramento/Usuario";

	var _buscarUsuario = function (cpf, senha) {
		return $http.get(url+"?cpf="+cpf+"&senha="+senha);
	};

	return {
		buscarUsuario: _buscarUsuario
	};
});