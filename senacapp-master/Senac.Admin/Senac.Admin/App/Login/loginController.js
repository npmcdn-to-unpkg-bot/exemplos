(function () {
    "use strict";

    var senacApp = angular.module('senacApp');

    senacApp.controller('loginController', ['$scope', '$location', 'loginService', function ($scope, $location, loginService) {

        $scope.autenticar = function () {
            loginService.auntenticarUsuario($scope.txtUsuario, $scope.txtSenha).then(function (data) {

            });
        };

        $scope.procurarUsuario = function () {
            loginService.procurarUsuario($scope.txtIdUsuario).then(function (data) {
                 
                if (data.data.Senha == null || data.data.Senha == '') {
                    console.log("Não tem senha");

                    sessionStorage.idUsuario = data.data.UsuarioID;
                    //console.log(data.data.UsuarioID);
                    $location.path("/redefinirSenha");
                }



            });
        };

    }]);
}());