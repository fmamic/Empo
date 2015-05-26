var app = angular.module('ngBoilerplate', [
    'templates-app',
    'templates-common',
    'ngBoilerplate.home',
    'ngBoilerplate.about',
    'ngBoilerplate.profile',
    'ngBoilerplate.person',
    'ngBoilerplate.images',
    'ngBoilerplate.tag',
    'ngBoilerplate.search',
    'ui.router',
    'chart.js'
])

    .config(function myAppConfig($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');
    })

    .run(function run() {
    })

    .controller('AppCtrl', function AppCtrl($scope) {
        $scope.$on('$stateChangeSuccess', function (event, toState) {
            if (angular.isDefined(toState.data.pageTitle)) {
                $scope.pageTitle = toState.data.pageTitle;
            }
        });
    })
    .controller('SearchCtrl', function SearchCtrl($scope, $state, $http, $location) {
        $scope.selected = undefined;

        $scope.onSelect = function ($item, $model) {
            $state.go('profile.detail', {id: $model.id}, { reload: true });
        };

        $scope.persons = {};

        $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/user/all').
            success(function(data, status, headers, config) {
                $scope.persons = data;
            }).
            error(function(data, status, headers, config) {
            });

        $scope.tags = [{
            id: 0,
            name: 'Java',
            tagType: 'Tehnology',
            displayName: 'Java Programming Language',
            description: 'Open source programming language'
        }];

        $scope.badges = [{
            id: 0,
            name: 'Java Certified Associate',
            tag: 1,
            badgeType: 'Tehnology',
            level: 1,
            description: 'Java low certification level'
        }];

        $scope.projects = [{
            id: 0,
            description: 'Very big project with multiple data modules',
            name: 'RBA web portal',
            fromDate: '11.2.2014.',
            toDate: '3.3.2015.'
        }];
    });
