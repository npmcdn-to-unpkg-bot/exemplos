angular.module("listaTelefonica").controller("listaTelefonicaCtrl", function ($scope, contatosAPI, operadorasAPI, serialGenerator) {
	$scope.app = "Lista Telefonica";
	$scope.contatos = [];
	$scope.operadoras = [];
	$scope.contato = {
		data: 1355281200000
	};

	var carregarContatos = function () {
		contatosAPI.getContatos().success(function (data) {
			$scope.contatos = data;
		}).error(function (data, status) {
			$scope.mensagem = "Aconteceu um problema";
		});
	};

	var carregaOperadoras = function () {
		operadorasAPI.getOperadoras().success(function (data) {
			$scope.operadoras = data;
		});
	};

	$scope.adicionarContato = function (contato) {
		contato.serial = serialGenerator.generate();
		contato.data = new Date();

		contatosAPI.saveContato(contato).success(function (data) {
			delete $scope.contato;
			$scope.contatoForm.$setPristine();
			carregarContatos();
		});
	};

	$scope.apagarContatos = function (contatos) {
		var contatosSele = contatos.filter(function (c) {
			if (c.selecionado) return c;
		});

		contatosAPI.deletarContatos(contatosSele).success(function (data) {
			carregarContatos();
		});

	};

	$scope.isContatoSelecionado = function (contatos) {
		return contatos.some(function (c) {
			return c.selecionado; 
		});
	};

	$scope.ordenarPor = function (campo) {
		$scope.criterioDeOrdenacao = campo;
		$scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
	};

	$scope.abrirModal = function (contatoSelecionado) {
		console.log("Chamou");	
		$scope.contatoSelecionado = contatoSelecionado;
		$("#idDoModal").modal("show");
	};

	$scope.deletarContato = function (id) {
		console.log(id);
		contatosAPI.deletarContato(id).success(function (data) {
			console.log("Meliante de id " + id + " deletado com sucesso");	
			carregarContatos();
			$("#idDoModal").modal("hide");	
		}).error(function (data, status) {
			console.log("Meliante de id " + id + " NÃO deletado com sucesso");	
		});
	};

	carregarContatos();
	carregaOperadoras();
});