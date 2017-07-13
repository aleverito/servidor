'use strict';

app.factory('CursoService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllCursos: loadAllCursos,
                getAllCursos: getAllCursos,
                crearCurso: crearCurso
            };

            return factory;
            
            function loadAllCursos() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.API_CURSOS)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all courses');
                            $localStorage.cursos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading courses');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllCursos(){
                return $localStorage.cursos;
            }

            function crearCurso(curso) {
                console.log('Crear Curso');
                var deferred = $q.defer();
                
                $http.get(urls.API_CURSOS+"save/", {params:curso})
                    .then(
                        function (response) {
                            loadAllCursos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating course : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);