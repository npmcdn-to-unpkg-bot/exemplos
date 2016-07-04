(function () {
    "use strict";

    var senacApp = angular.module('senacApp');

    senacApp.controller('homeController', ['$scope', '$location', 'homeService', function ($scope, $location, homeService) {
        //create a message to display in our view 

        $scope.ListarUsuarios = function () {
            homeService.listarUsuarios().then(function (data) {
                console.log(data);
                $scope.listaDeUsuarios = data.data;
                $scope.quantidade = data.data.length;
                console.log($scope.quantidade);
            }, function () {
                alert('Infelizmente ocorreu um erro, tente novamente mais tarde.');
            });
        };
        $scope.abrirModal = function (usuario) {            
            
            $scope.nomeUsuarioChat = usuario;
            $("#myModal").modal("show");
        };
        $scope.fecharModal = function () {            
            $("#myModal").modal("toggle");
            return false;
        };
        $scope.ListarUsuarios();
    }]);
}());