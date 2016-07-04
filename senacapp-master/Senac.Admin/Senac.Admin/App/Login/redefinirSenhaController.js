(function () {
    "use strict";

    var senacApp = angular.module('senacApp');

    senacApp.controller('redefinirSenhaController', ['$scope', '$location', 'loginService', function ($scope, $location, loginService) {

        $scope.salvarSenha = function () {
            if ($scope.txtSenha == $scope.txtConfirmacaoSenha) {
                console.log("Senha correta")
                loginService.editarSenha(sessionStorage.idUsuario, $scope.txtSenha).then(function (data) { });
                
                $location.path("/home");

            } else {
                console.log("Senha incorreta")                
            }
        };

    }]);
}());