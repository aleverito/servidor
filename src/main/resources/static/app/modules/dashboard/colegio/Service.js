'use strict';

app.factory('ColegioService',
    ['$localStorage', '$http', '$q', 'urls','Upload',
        function ($localStorage, $http, $q, urls,Upload) {

            var factory = {
                loadAllColegios: loadAllColegios,
                getAllColegios: getAllColegios,
                crearColegio: crearColegio
            };

            return factory;
            
            function loadAllColegios() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.API_COLEGIOS)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all users');
                            $localStorage.colegios = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading users');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllColegios(){
                return $localStorage.colegios;
            }

            function crearColegio(colegio) {
                console.log('Crear Colegio');
                var deferred = $q.defer();
                
                $http.get(urls.API_COLEGIOS+"save/", {params:colegio})
                    .then(
                        function (response) {
                            loadAllColegios();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating User : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);