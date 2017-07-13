'use strict';

app.factory('MaestroService', ['$localStorage', '$http', '$q', 'urls','Upload',
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
                console.log('%c[INFO]%cFetching all users',log.info,log.fx);
                var deferred = $q.defer();
                $http.get(urls.API_MAESTRO)
                    .then(
                        function (response) {
                            console.log('%c[OK]%cFetched successfully all users',log.ok,log.fx);
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('%c[ERROR]%cError while loading users',log.error,log.fx);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllUsers(){
                return $localStorage.users;
            }

            function getUser(id) {
                console.log('%c[INFO]%cFetching User with id :'+id,log.info,log.fx);
                var deferred = $q.defer();
                $http.get(urls.API_MAESTRO + id)
                    .then(
                        function (response) {
                            console.log('%c[OK]%cFetched successfully User with id :'+id,log.ok,log.fx);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('%c[ERROR]%cError while loading user with id :'+id,log.error,log.fx);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createUser(user) {
                console.log('%c[INFO]%cCreating User',log.info);
                var deferred = $q.defer();
                
                $http.post(urls.API_MAESTRO, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('%c[ERROR]%cError while creating User : '+errResponse.data.errorMessage,log.error,log.fx);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateUser(user, id) {
                console.log('%c[INFO]%cActulizando Maestro con id '+id,log.info,log.fx);
                var deferred = $q.defer();
                $http.put(urls.API_MAESTRO + id, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('%c[ERROR]%cError mientras actualizaba Maestro con id :'+id,log.error,log.fx);
                            console.warn(errResponse);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeUser(id) {
                console.log('%c[INFO]%cBorrando Maestro con id '+id,log.info,log.fx);
                var deferred = $q.defer();
                $http.delete(urls.API_MAESTRO + id)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('%c[ERROR]%cError mientras de borraba Maestro con id :'+id,log.error,log.fx);
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