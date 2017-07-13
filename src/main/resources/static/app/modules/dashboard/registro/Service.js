'use strict';

app.factory('RegistroService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllRegistros: loadAllRegistros,
                getAllRegistros: getAllRegistros,
                crearRegistro: crearRegistro
            };

            return factory;
            
            function loadAllRegistros() {
                console.log('Fetching all registros');
                var deferred = $q.defer();
                $http.get(urls.API_REGISTROS)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all registros');
                            $localStorage.registros = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading registros');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllRegistros(){
                return $localStorage.registros;
            }

            function crearRegistro(registro) {
                console.log('Crear Registro');
                var deferred = $q.defer();
                
                $http.get(urls.API_REGISTROS+"save/", {params:registro})
                    .then(
                        function (response) {
                            loadAllRegistros();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating registro : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);