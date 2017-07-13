'use strict';

app.factory('CicloService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllCiclos: loadAllCiclos,
                getAllCiclos: getAllCiclos,
                crearCiclo: crearCiclo
            };

            return factory;
            
            function loadAllCiclos() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.API_CICLOS)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all ciclos');
                            $localStorage.ciclos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading ciclos');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllCiclos(){
                return $localStorage.ciclos;
            }

            function crearCiclo(ciclo) {
                console.log('Crear Ciclo');
                var deferred = $q.defer();
                
                $http.get(urls.API_CICLOS+"save/", {params:ciclo})
                    .then(
                        function (response) {
                            loadAllCiclos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating ciclo : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);