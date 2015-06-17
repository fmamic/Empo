angular.module('taggy.images', [
    'ui.router', 'angularFileUpload'
]).config(function config($stateProvider) {
    $stateProvider.state('images', {
        url: '/images',
        views: {
            "navbar": {
                templateUrl: 'main/navbar.tpl.html'
            },
            "main": {
                controller: 'ImagesCtrl',
                templateUrl: 'images/images.tpl.html'
            },
            "footer": {
                templateUrl: 'main/footer.tpl.html'
            }
        },
        data: {pageTitle: 'Images'}
    });
}).controller('ImagesCtrl', function PersonController($scope, FileUploader) {
    $scope.uploader = new FileUploader();
});
