angular.module('ngBoilerplate.images', [
    'ui.router', 'angularFileUpload'
]).config(function config($stateProvider) {
    $stateProvider.state('images', {
        url: '/images',
        views: {
            "main": {
                controller: 'ImagesCtrl',
                templateUrl: 'images/images.tpl.html'
            }
        },
        data: {pageTitle: 'Images'}
    });
}).controller('ImagesCtrl', function PersonController($scope, FileUploader) {
    $scope.uploader = new FileUploader();
});
