
login.controller("loginCtrl", ['$rootScope', '$scope', '$state', '$location', 'loginService', 'Flash','apiService',
function ($rootScope, $scope, $state, $location, loginService, Flash, apiService) {
        var vm = this;

        vm.getUser = {};
        vm.setUser = {};
        vm.signIn = true;

        //access login
        vm.login = function (data) {
        	apiService.authentication(data.Username,data.Password).then(function(response){
        		console.log(response);
        		$state.go('app.dashboard');
        	},function(e){
        		console.error(e);
        		Flash.create('danger', 'Invalido Username o Password', 'large-text');
        	});
//            if (data.Username == "admin") {
//                if (data.Password == "admin") {
//                }
//                else
//            }
//            else
//                Flash.create('danger', 'Invalid Username', 'large-text');
        };

        //get registration details
        vm.register = function () {
            if (vm.setUser.confirmPassword == vm.setUser.Password){
                loginService.registerUser(vm.setUser).then(function (response) {
                    if (response.message == 'success')
                console.log('after post>>',response);
                //if (response.length > 0)
                //    $state.go('app');
                //else
                //    Flash.create('danger', 'Invalid Credentials', 'large-text');
            });
            }
        };

    }]);

