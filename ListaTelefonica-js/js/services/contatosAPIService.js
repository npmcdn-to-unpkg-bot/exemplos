angular.module("listaTelefonica").factory("contatosAPI", function ($http, config) {
	var _getContatos = function () {
		return $http.get(config.baseUrl + "/ListaTelefonica/Contato");
	};

	var _saveContato = function (contato) {
		return $http.post(config.baseUrl + "/ListaTelefonica", contato);
	};
	
	var _deletarContatos = function (contatos) {
		return $http.post(config.baseUrl + "/ListaTelefonica/Deletar", contatos);
	};

	var _deletarContato = function (id) {
		return $http.delete(config.baseUrl + "/ListaTelefonica/"+id);		
	};

	return {
		getContatos: _getContatos,
		saveContato: _saveContato,
		deletarContatos: _deletarContatos,
		deletarContato: _deletarContato
	};
});