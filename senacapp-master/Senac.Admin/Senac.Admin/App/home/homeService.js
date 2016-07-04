(function () {
    "use strict";

    var senacApp = angular.module('senacApp');

    senacApp.factory('homeService', ['$http', function ($http) {        

        var listarUsuarios = function () {
            return $http.get('http://senacxamarin.azurewebsites.net/api/usuario');
        };

        return {
            listarUsuarios: listarUsuarios
        };

    }]);
}());