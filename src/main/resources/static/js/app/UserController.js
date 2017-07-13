(function(){
'use strict';
angular.module('app').controller('UserController',UserController);
UserController.$inject = ['UserService', '$scope','Upload', '$timeout'];
function UserController( UserService, $scope , Upload , $timeout ) {
        var self = this;
        self.user = {};
        self.users=[];

        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
        self.editUser = editUser;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.DNI = /^\d{7}([-]\w{1})?$/;
        
        // ***** Subir imagen al uri
        self.upload = function (dataUrl, name) {
        	var blob = Upload.dataUrltoBlob(dataUrl, name);
        	console.log(blob);
        	var canvas = document.createElement('canvas');
        	

        	var img = document.createElement("img");
        	img.src= dataUrl;

        	var ctx = canvas.getContext("2d");
        	ctx.drawImage(img, 0, 0);

        	var MAX_WIDTH = 800;
        	var MAX_HEIGHT = 600;
        	var width = img.width;
        	var height = img.height;

        	if (width > height) {
        	  if (width > MAX_WIDTH) {
        	    height *= MAX_WIDTH / width;
        	    width = MAX_WIDTH;
        	  }
        	} else {
        	  if (height > MAX_HEIGHT) {
        	    width *= MAX_HEIGHT / height;
        	    height = MAX_HEIGHT;
        	  }
        	}
        	canvas.width = width;
        	canvas.height = height;
        	var ctx = canvas.getContext("2d");
        	ctx.drawImage(img, 0, 0, width, height);

        	var dataUrl2 = canvas.toDataURL("image/png");
	        Upload.upload({
	            url: '/api/saveImage/',
	            enctype:'multipart/form-data',
	            data: {
	                file: blob,
	                foto:dataUrl2,
	                nombre:name
	            },
	        }).then(function (response) {
	            $timeout(function () {
	            	self.result = response.data;
	            });
	        }, function (response) {
	            if (response.status > 0) self.errorMsg = response.status 
	                + ': ' + response.data;
	        }, function (evt) {
	        	self.progress = parseInt(100.0 * evt.loaded / evt.total);
	        });
        };
        
        
        function submit() {
            console.log('Submitting');
            if (self.user.id === undefined || self.user.id === null) {
                console.log('Saving New User', self.user);
                createUser(self.user);
            } else {
                updateUser(self.user, self.user.id);
                console.log('User updated with id ', self.user.id);
            }
        }

        function createUser(user) {
            console.log('About to create user');
            UserService.createUser(user)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        self.successMessage = 'User created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.user={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateUser(user, id){
            console.log('About to update user');
            UserService.updateUser(user, id)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        self.successMessage='User updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating User');
                        self.errorMessage='Error while updating User '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeUser(id){
            console.log('About to remove User with id '+id);
            UserService.removeUser(id)
                .then(
                    function(){
                        console.log('User '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing user '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllUsers(){
            return UserService.getAllUsers();
        }

        function editUser(id) {
        	
            self.successMessage='';
            self.errorMessage='';
            UserService.getUser(id).then(
                function (user) {
                    self.user = user;
                },
                function (errResponse) {
                    console.error('Error while removing user ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.user={};
            $scope.myForm.$setPristine(); //reset Form
        }
}
})();