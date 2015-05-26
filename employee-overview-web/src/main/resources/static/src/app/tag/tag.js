angular.module('ngBoilerplate.tag', [
    'ui.router', 'chart.js'
])
    .config(function config($stateProvider) {
        $stateProvider.state('tag', {
            url: '/tag',
            views: {
                "main": {
                    controller: 'TagCtrl',
                    templateUrl: 'tag/tag.tpl.html'
                }
            },
            data: {pageTitle: 'Tag'}
        });
    })
    .controller('TagCtrl', function TagController($scope, $http, $location) {
        $scope.location = $location.absUrl().split('/')[0];
        $scope.openTag = function (id) {
            $state.go('tag');
        };
    });
