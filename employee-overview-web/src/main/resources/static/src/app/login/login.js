angular.module('taggy.login', [
    'ui.router'
]).config(function config($stateProvider) {
    $stateProvider.state('login', {
        url: '/login',
        views: {
            "main": {
                controller: 'LoginCtrl',
                templateUrl: 'login/login.tpl.html'
            }
        },
        data: {pageTitle: 'Login'}
    });
}).controller('LoginCtrl', function PersonController($scope, $http, $location, $modal) {
    $modal.open({
        animation: $scope.animationsEnabled,
        templateUrl: 'login/loginModal.tpl.html',
        controller: 'LoginModalInstanceCtrl',
        keyboard: false,
        backdrop: 'static'
    });
}).controller('LoginModalInstanceCtrl', function($scope, $http, AUTH) {
    $scope.authUrl = AUTH.TASHI_URL + 'client_id=' + AUTH.CLIENT_ID + '&scope=' + AUTH.SCOPE + '&redirect_uri=' + AUTH.REDIRECT_URL + '&response_type=' + AUTH.RESPONSE;
});

