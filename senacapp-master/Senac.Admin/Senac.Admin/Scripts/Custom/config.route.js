(function () {
    "use strict";

    var senacApp = angular.module('senacApp', ['ngRoute']);

    senacApp.config(function ($routeProvider) {
        $routeProvider

        .when('/', {
            templateUrl: 'app/login/login.html',
            controller: 'loginController'
        })

        .when('/login', {
            templateUrl: 'app/login/login.html',
            controller: 'loginController'
        })

        .when('/home', {
            templateUrl: 'app/home/home.html',
            controller: 'homeController'
        })
        .when('/post', {
            templateUrl: 'app/posts/post.html',
            controller: 'postController'
        })

        .when('/redefinirSenha', {
            templateUrl: 'app/login/redefinirSenha.html',
            controller: 'redefinirSenhaController'
        })

    });
}());
