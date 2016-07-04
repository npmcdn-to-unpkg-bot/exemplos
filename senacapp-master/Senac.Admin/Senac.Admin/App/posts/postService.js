(function () {
    "use strict";

    var senacApp = angular.module('senacApp');

    senacApp.factory('postService', ['$http', function ($http) {

        var listarPost = function () {
            return $http.get('http://senacxamarin.azurewebsites.net/api/Post');
        };
        var deletarPost = function (postId) {
            return $http.delete('http://senacxamarin.azurewebsites.net/api/Post?PostID='+postId);
        }
        return {
            listarPost: listarPost,
            deletarPost: deletarPost
        };
    }]);
}());