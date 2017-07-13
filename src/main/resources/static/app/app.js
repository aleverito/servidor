var app = angular.module('app', 
['ui.router', 'ui.bootstrap','smart-table', 'flash','ngStorage','ngFileUpload','ngImgCrop','ui.sortable','ngDialog','ngAnimate','toastr',
    //main modules
    'login', 'dashboard']);

app.config(['$httpProvider','$stateProvider', '$locationProvider', '$urlRouterProvider', function ($httpProvider,$stateProvider, $locationProvider, $urlRouterProvider, $modalInstance) {
//	$locationProvider.html5Mode(true);
    //IdleScreenList
   

    $stateProvider
       .state('app', {
           url: '/app',
           templateUrl: 'app/common/app.html',
           controller: 'appCtrl',
           controllerAs: 'vm',
           data: {
               pageTitle: 'Login'
           }
       });

    $urlRouterProvider.otherwise('login');

    //$urlRouterProvider.otherwise('app/dashboard');
    //$urlRouterProvider.otherwise('/app/dashboard');
}]);

// set global configuration of application and it can be accessed by injecting appSettings in any modules
app.constant('appSettings', appConfig);
app.constant('urls', {
    BASE: '/',
    API_ESTUDIANTE : '/api/estudiante/',
    API_MAESTRO : '/api/maestro/',
    API_CURSOS: '/api/curso/',
    API_COLEGIOS:'/api/colegio/',
    API_CICLOS:'/api/ciclo/',
    API_REGISTROS:'/api/registro/'
});