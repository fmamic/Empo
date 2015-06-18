angular.module('taggy.home', [
    'ui.router',
    'plusOne',
    'chart.js', 'infinite-scroll'
]).config(function config($stateProvider) {
    $stateProvider.state('home', {
        url: '/home',
        views: {
            "navbar": {
                templateUrl: 'main/navbar.tpl.html'
            },
            "main": {
                controller: 'HomeCtrl',
                templateUrl: 'home/home.tpl.html'
            },
            "footer": {
                templateUrl: 'main/footer.tpl.html'
            }
        },
        data: {pageTitle: 'Home'}
    });
}).controller('HomeCtrl', function HomeController($scope, $http, $location) {
    $scope.person = {};
    $scope.location = $location.absUrl().split('/')[0];

    $scope.eventLog = [];

    $http.get($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/all/actionInfo').
        success(function (data) {
            $.each(data, function(index, value) {

                if(value.action.indexOf("ADD") >= 0 && value.type.indexOf("UserBadge") >= 0) {
                    $scope.eventLog.push({ userId: value.userId,  name: value.userName, date: value.timestamp, description: value.entityName + " has been added to " + value.userName });
                }

            });
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

