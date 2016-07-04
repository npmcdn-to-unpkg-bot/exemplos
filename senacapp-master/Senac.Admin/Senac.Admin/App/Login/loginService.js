(function () {
    "use strict";

    var senacApp = angular.module('senacApp');

    senacApp.factory('loginService', ['$http', function ($http) {

        var autenticarUsuario = function (cpf, senha) {
            //variaveis para autenticação
            var parametros = {
                client_id: 'senacapp',
                username: cpf,
                password: senha,
                grant_type: 'password'
            };

            return $http.post('http://senacxamarin.azurewebsites.net/api/token', parametros);
        };


        var procurarUsuario = function (cpf) {
            return $http.get('http://senacxamarin.azurewebsites.net/api/usuario?cpf=' + cpf);
        };

        var editarSenha = function (id, senha) {

            var parametros = {
                UsuarioID: id,
                Senha: senha
            };

            return $http.put('http://senacxamarin.azurewebsites.net/api/usuario', parametros);
        };

        return {
            autenticarUsuario: autenticarUsuario,
            procurarUsuario: procurarUsuario,
            editarSenha: editarSenha
        };

    }]);
}());