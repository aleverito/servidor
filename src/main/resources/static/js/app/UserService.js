'use strict';
app.constant('urls', {
    BASE: '/',
    USER_SERVICE_API : '/api/user/',
    MAESTRO_SERVICE_API : '/api/maestro/'
});
app.factory('UserService',
    ['$localStorage', '$http', '$q', 'urls','Upload',
        function ($localStorage, $http, $q, urls,Upload) {

            var factory = {
                loadAllUsers: loadAllUsers,
                getAllUsers: getAllUsers,
                getUser: getUser,
                createUser: createUser,
                updateUser: updateUser,
                removeUser: removeUser
            };

            return factory;
            
            function loadAllUsers() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all users');
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading users');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllUsers(){
                return $localStorage.users;
            }

            function getUser(id) {
                console.log('Fetching User with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully User with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createUser(user) {
                console.log('Creating User');
                var deferred = $q.defer();
                
                $http.post(urls.USER_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating User : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateUser(user, id) {
                console.log('Updating User with id '+id);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating User with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeUser(id) {
                console.log('Removing User with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing User with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            function renderImage(dataUrl,name){
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
            }

        }
    ]);