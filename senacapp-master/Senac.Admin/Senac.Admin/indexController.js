(function () {
    "use strict";

    var senacApp = angular.module('senacApp');

    senacApp.controller('indexController', ['$scope', '$location', 'homeService', 'postService', function ($scope, $location, homeService, postService) {

        $scope.ListarUsuarios = function () {
            homeService.listarUsuarios().then(function (data) {
                $scope.quantidade = data.data.length;
                console.log($scope.quantidade);
            }, function () {
                alert('Infelizmente ocorreu um erro, tente novamente mais tarde.');
            });
        };

        $scope.ListarUsuarios();

        $scope.ListarPost = function () {
            postService.listarPost().then(function (data) {              
                $scope.quantidadePost = data.data.length;           

            }, function () {

                alert("Erro ao carregar os posts");

            });
        };

        $scope.ListarPost();

        if ($location.$$url == "/home") {
            $('#navegacao li').removeClass('active');
            $scope.classeHome = "active";
        } else if ($location.$$url == "/post") {
            $('#navegacao li').removeClass('active');
            $scope.classePost = "active";
        }

        $('#navegacao li').click(function () {
            $('#navegacao li').removeClass('active');
            $(this).addClass('active');
            $("#searchText").focus();
        });

        console.log($location.$$url);
    }]);
}());