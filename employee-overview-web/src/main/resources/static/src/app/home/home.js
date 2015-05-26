angular.module('ngBoilerplate.home', [
    'ui.router',
    'plusOne',
    'chart.js', 'infinite-scroll'
]).config(function config($stateProvider) {
    $stateProvider.state('home', {
        url: '/home',
        views: {
            "main": {
                controller: 'HomeCtrl',
                templateUrl: 'home/home.tpl.html'
            }
        },
        data: {pageTitle: 'Home'}
    });
}).controller('HomeCtrl', function HomeController($scope, $http, $location) {
    $scope.person = {};
    $scope.location = $location.absUrl().split('/')[0];

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
}).controller("Bar2Ctrl", function ($scope) {
    $scope.labels = ['John', 'John', 'John', 'John', 'John', 'John', 'John'];
    $scope.series = ['Series A'];

    $scope.data = [
        [65, 59, 80, 81, 56, 55, 40]
    ];
}).controller("DoughnutCtrl", function ($scope) {
    $scope.labels = ["Java", "Spring", "Hibernate"];
    $scope.data = [120, 80, 30];
});

