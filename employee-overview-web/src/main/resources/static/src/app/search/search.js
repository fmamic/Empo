angular.module('ngBoilerplate.search', [
    'ui.router', 'chart.js', 'infinite-scroll'
])
    .config(function config($stateProvider) {
        $stateProvider.state('search', {
            url: '/search',
            views: {
                "main": {
                    controller: 'SearchResultsCtrl',
                    templateUrl: 'search/search.tpl.html'
                }
            },
            data: { pageTitle: 'Search result'}
        }).state('search.param', {
            url:'/:param',
            views: {
                "main": {
                    controller: 'SearchResultsCtrl',
                    templateUrl: 'search/search.tpl.html'
                }
            },
            data: { pageTitle: 'Search result' }
        });
    }).controller('SearchResultsCtrl', function TagController($scope, $http, $location) {
        $scope.person = {};
        $scope.location = $location.absUrl().split('/')[0];


        $http.post('/search/user', {firstName: 'Filip', lastName: 'Mamic'});

        $scope.eventLog = [
            {
                name: "John Doe",
                description: "Certification description s known as prototypical inheritance, and child scopes prototypically"
            },
            {
                name: "Java",
                description: "Java tag"

            },
            {
                name: "Badge",
                description: "Badge of honor"
            },
            {
                name: "Badge",
                description: "Badge of honor"
            },
            {
                name: "Badge",
                description: "Badge of honor"
            }
        ];

        $scope.eventLog.rest = [
            {
                name: "Badge",
                description: "Badge of honor"
            },
            {
                name: "Badge",
                description: "Badge of honor"
            },
            {
                name: "Badge",
                description: "Badge of honor"
            },
            {
                name: "Badge",
                description: "Badge of honor"
            },
            {
                name: "Badge",
                description: "Badge of honor"
            },
            {
                name: "Badge",
                description: "Badge of honor"
            },
            {
                name: "Badge",
                description: "Badge of honor"
            },
            {
                name: "Badge",
                description: "Badge of honor"
            }
        ];

        $scope.numberLoaded = 0;

        $scope.loadMore = function () {
            var last = $scope.eventLog.rest.length - 1;

            for (var i = $scope.numberLoaded; (i <= $scope.numberLoaded + 5) && i <= last; i++) {
                $scope.eventLog.push($scope.eventLog.rest[$scope.numberLoaded]);
            }

            $scope.numberLoaded += 5;
        };

        $http.get('http://api.randomuser.me/').
            success(function (data) {
                var user = data.results[0].user;
                $scope.person.picture = user.picture.thumbnail;
            }).
            error(function (data, status, headers, config) {
            });
    });
