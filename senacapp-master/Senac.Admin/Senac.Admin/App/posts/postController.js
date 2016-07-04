(function () {
    "use strict";
    var senacApp = angular.module('senacApp');
    
    senacApp.controller('postController', ['$scope', '$location', 'postService', function ($scope, $location, postService) {

        $scope.ListarPost = function () {
            postService.listarPost().then(function (data) {
                console.log(data);
                $scope.quantidadePost = data.data.length;
                $scope.listaDePost = data.data;

            }, function () {

                alert("Erro ao carregar os posts");

            });
        };
        $scope.abrirModal = function (id) {
            $scope.id = id;
            $("#myModal").modal("show");
        };
        $scope.DeletarPost = function () {
            console.log($scope.id);
            postService.deletarPost($scope.id).then(function () {
                $("#modalDelete").modal("show");
                $("#myModal").modal("hide");
                $scope.ListarPost();
            });
        };

        $scope.ListarPost();
    }]);
}());