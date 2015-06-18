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
    'ui.router',
    'ngStorage',
    'chart.js'

]).config(function myAppConfig($stateProvider, $urlRouterProvider, $httpProvider, AUTH) {

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
                    $location.path('/home');
                }
                return $q.reject(response);
            }
        };
    }]);

    $urlRouterProvider.otherwise('/home');

}).controller('AppCtrl', function AppCtrl($scope, $localStorage, $state, $rootScope, $location, $http, USER_ENV, TAGGY_ENV, userService) {

    $rootScope.$on("$locationChangeStart", function (event, next, current) {
        if (next.indexOf("access_token") != -1) {
            $localStorage.token = $location.$$path.split('&')[1].split('=')[1];
        }
    });

    $scope.$on('$stateChangeSuccess', function (event, toState) {
        if (!$localStorage.token) {
            $location.path(AUTH.TASHI_URL + 'client_id=' + AUTH.CLIENT_ID + '&scope=' + AUTH.SCOPE + '&redirect_uri=' + AUTH.REDIRECT_URL + '&response_type=' + AUTH.RESPONSE);
        } else {
            $location.path('/home');
        }

        if (angular.isDefined(toState.data.pageTitle)) {
            $scope.pageTitle = toState.data.pageTitle;
        }
    });

    $scope.currentUser = userService.currentUser;

    $scope.logout = function() {

    };

    $http.get(USER_ENV.TASHI_USERS_URL + '/search/getCurrentUser').success(function (data) {
        userService.currentUser.username = data._embedded.users[0].username;

        $http.get(TAGGY_ENV.SERVER_URL + '/search/user/username/' + userService.currentUser.username).success(function(data) {
            userService.currentUser.form = data;
        });
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
}).service('userService', function() {
    this.currentUser = {};
});
