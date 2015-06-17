angular.module('taggy.tag', [
    'ui.router', 'chart.js'
])
    .config(function config($stateProvider) {
        $stateProvider.state('tag', {
            url: '/tag',
            views: {
                "navbar": {
                    templateUrl: 'main/navbar.tpl.html'
                },
                "main": {
                    controller: 'TagCtrl',
                    templateUrl: 'tag/tag.tpl.html'
                },
                "footer": {
                    templateUrl: 'main/footer.tpl.html'
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
