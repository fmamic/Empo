var app = angular.module('taggy', [
    'templates-app',
    'templates-common',
    'taggy.home',
    'taggy.about',
    'taggy.profile',
    'taggy.person',
    'taggy.images',
    'taggy.tag',
    'taggy.search',
    'taggy.login',
    'ui.router',
    'ngStorage',
    'chart.js'

]).config(function myAppConfig($stateProvider, $urlRouterProvider, $httpProvider) {

    $httpProvider.interceptors.push(['$q', '$location', '$localStorage', function($q, $location, $localStorage) {
        return {
            'request': function (config) {
                config.headers = config.headers || {};
                if ($localStorage.token) {
                    config.headers.Authorization = 'Bearer ' + $localStorage.token;
                }
                return config;
            },
            'responseError': function(response) {
                if(response.status === 401 || response.status === 403) {
                    $location.path('/login');
                }
                return $q.reject(response);
            }
        };
    }]);

    $urlRouterProvider.otherwise('/login');

}).controller('AppCtrl', function AppCtrl($scope, $localStorage, $state, $rootScope, $location) {

    $rootScope.$on("$locationChangeStart", function(event, next, current) {
        if(next.indexOf("access_token") != -1) {
            $localStorage.token = $location.$$path.split('&')[1].split('=')[1];
        }
    });

    $scope.$on('$stateChangeSuccess', function (event, toState) {
        if (!$localStorage.token) {
            $location.path('/login');
            return;
        }

        if (angular.isDefined(toState.data.pageTitle)) {
            $scope.pageTitle = toState.data.pageTitle;
        }
    });

}).controller('SearchCtrl', function SearchCtrl($scope, $state, $http, $location) {
    $scope.selected = undefined;

    $scope.onSelect = function ($item, $model) {
        $state.go('profile.detail', {id: $model.id}, {reload: true});
    };

    $scope.persons = {};

    $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/user/all').
        success(function (data) {
            $scope.persons = data;
        }).
        error(function (data, status, headers, config) {
        });
});
