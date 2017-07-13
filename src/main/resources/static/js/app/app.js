var app = angular.module('crudApp',['ui.router','ngStorage','ngFileUpload','ngImgCrop']);

app.constant('urls', {
    BASE: '/',
    USER_SERVICE_API : '/api/user/'
});

app.config(['$stateProvider', '$urlRouterProvider','$locationProvider',
    function($stateProvider, $urlRouterProvider,$locationProvider) {
        $stateProvider
        .state('home', {
            url: '/',
            templateUrl: 'partials/list',
            controller:'UserController',
            controllerAs:'ctrl',
            resolve: {
                users: function ($q, UserService) {
                    console.log('Load all users');
                    var deferred = $q.defer();
                    UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }
            }
        });
        $urlRouterProvider.otherwise('/');
        $locationProvider.html5Mode(true);
    }]);

