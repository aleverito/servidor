(function(){
'use strict';
angular.module('app').controller('EstudianteController',UserController);
UserController.$inject = ['UserService', '$scope','Upload', '$timeout','$filter','Flash','ngDialog'];
function UserController( UserService, $scope , Upload , $timeout ,$filter,Flash,ngDialog) {
        var self = this;
        self.user = {};
        self.users=[];
        self.smartTablePageSize=10;

        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
        self.editUser = editUser;
        self.reset = reset;
        self.registro = registro;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.DNI = /^\d{7}([-]\w{1})?$/;
        self.RUDE = /^\d{20}(\w{1})$/;
        self.rowCollection = getAllUsers();
        $scope.$on('actualizar', function() {
			$scope.clearFilter();
		});
		$scope.clearFilter = function(){
			UserService.loadAllUsers().then(function(){
				var v = getAllUsers();
				console.log(v,v.length);
				self.rowCollection=[];
				for(var i=0;i<v.length;i++)
					self.rowCollection.push(v[i]);
			},function(){});
		};
        
        
        function submit() {
            console.log('Submitting');
            if (self.user.id === undefined || self.user.id === null) {
            	console.log('%c[INFO]Guardando [nuevo] Estudiante. %o',log.wait, self.user);
                createUser(self.user);
            } else {
                updateUser(self.user, self.user.id);
                console.log('%c[INFO]%cEstudiante updated with id '+ self.user.id,log.info,log.fx);
            }
        }

        function createUser(user) {
        	console.log('%c[INFO]creando estudiante...',log.info);
            UserService.createUser(user)
                .then(
                    function (response) {
                    	console.log('%c[OK]%cEstudiante creado Exitosamente!!',log.ok,log.fx);
                        $scope.$broadcast('actualizar');
                        Flash.create('success', 'Maestro creado exitosamente', 'small-text');
                        self.done = true;
                        self.user={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                    	console.error('%c[ERROR]Error mientras creaba Estudiante',log.error);
						Flash.create('danger', 'Error mientras creando Estudiante: '+errResponse.data.errorMessage, 'small-text');
                    }
                );
        }


        function updateUser(user, id){
            console.log('About to update user');
            UserService.updateUser(user, id)
                .then(
                    function (response){
                    	console.log('%c[OK]%cEstudiante Actualizado correctamente',log.ok,log.fx);
						Flash.create('info', 'Estudiante actualizado Correctamente!: ', 'large-text');
						$scope.$broadcast('actualizar');
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                    	console.error('%c[ERROR]Error mientras editaba estudiante ' + id + ', Error :' + errResponse.data,log.error);
						Flash.create('danger', 'Error mientras editaba estudiante ' + id + ', Error :' + errResponse.data.errorMessage, 'large-text');
                    }
                );
        }


        function removeUser(id){
        	console.log('%c[INFO]%cAcerca para borrar Maestro con rda %s',log.info,log.fx,id);
            UserService.removeUser(id)
                .then(
                    function(){
                    	$scope.$broadcast('actualizar');
						console.log('%c[OK]%cEstudiante '+id + ' borrado correctamente',log.ok,log.fx);
                    },
                    function(errResponse){
                    	Flash.create('danger', 'Error mientras eliminaba Estudiante: '+id+', Error :'+errResponse.data, 'large-text');
						console.error('%cError mientras eliminaba Estudiante  '+id +', Error :'+errResponse.data,log.error);
                    }
                );
        }


        function getAllUsers(){
            return UserService.getAllUsers();
        }

        function editUser(id) {
            UserService.getUser(id).then(
                function (user) {
                	self.user = user;
					self.user.rude_old=user.rude;
					self.user.ci_old=user.ci;
                },
                function (errResponse) {
                	if(errResponse.status==404){
						console.error('%c[ERROR] ERROR recurso no encontrado'+errResponse,log.error);
						Flash.create('danger', 'ERROR recurso no encontrado: '+errResponse.status, 'large-text');
					}
					else if(errResponse.status==401){
						console.error('%c[ERROR] El servidor no esta Disponible o no hay conexion de Internet',log.error);
						Flash.create('danger', 'El servidor no esta Disponible o no hay conexion de Internet: '+errResponse.status, 'large-text');
					}else
						console.error('%c[ERROR]Error mientras editaba estudiante ' + id + ', Error :' + errResponse.data,log.error);
                }
            );
        }
        function reset(){
            self.user={};
            $scope.myForm.$setPristine(); //reset Form
        }
        function registro(user){
        	$scope.estudiante=user;
        	$scope.reg={};
        	$scope.reg.id_est=user.id;
        	ngDialog.open({
			    template: "app/modules/dashboard/registro/inscribir.html",
			    scope:$scope,
			    controller: ['$scope','$rootScope','ColegioService','CicloService','CursoService','RegistroService', function($scope,$rootScope, ColegioService, CicloService, CursoService, RegistroService) {
			    	ColegioService.loadAllColegios().then(function(result){
			    		$scope.colegios=ColegioService.getAllColegios();
			    	},	function(error){ console.error(error); 	});
			    	
			    	
			    	$scope.$on('onRefreshColegios',function(event, args){
			    		ColegioService.loadAllColegios().then(function(result){
				    		$scope.colegios=ColegioService.getAllColegios();
				    	},	function(error){ console.error(error); 	});
			    	});
			    	
			    	
			    	
			    	$scope.adicionarColegio= function(){
			    		var dialog2=ngDialog.open({
						    template: "app/modules/dashboard/colegio/adicionar.html",
						    scope:$scope,
						    controller: ['$scope','ColegioService', function($scope, ColegioService) {
						    	$scope.adicionar = function(nombre){
						    		ngDialog.close(dialog2.id);
				    				ColegioService.crearColegio({"nombre": nombre}).then(function(result){
				    					$rootScope.$broadcast('onRefreshColegios');
				    				},
				    				function(error){
				    					console.error(error);
				    				});
						    				
						    	}
						    }]
						});
			    		console.log(dialog2);
			    	}
			    	$scope.f=function(){
			    		console.log($scope.colegio);
			    	}
			    	CicloService.loadAllCiclos().then(function(result){
			    		$scope.ciclos=CicloService.getAllCiclos();			    		
			    	},
			    	function(error){  	});
			    	$scope.refrescarCiclos = function(){
			    		$scope.ciclos = [];
    					$scope.ciclos = CicloService.getAllCiclos();
			    	};
			    	$scope.adicionarCiclo = function(){
			    		var dialog3 = ngDialog.open({
			    			template: "app/modules/dashboard/ciclo/adicionar.html",
			    			scope:$scope,
			    			controller: ['$scope', 'CicloService', function ($scope, CicloService){
			    				$scope.adicionar = function(nombre){
			    					ngDialog.close(dialog3.id);
			    					CicloService.crearCiclo({"nombre": nombre}).then(function(result){
			    						$scope.ciclos = [];
			    						$scope.ciclos = CicloService.getAllCiclos();
			    					},
			    					function(error){
			    						console.error(error);
			    					});
			    				}
			    			}]
			    		})
			    	}
			    	CursoService.loadAllCursos().then(function(result){
			    		$scope.cursos=CursoService.getAllCursos();			    		
			    	},
			    	function(error){
			    		
			    	});
			    	$scope.refrescarCursos = function(){
			    		$scope.cursos = [];
    					$scope.cursos = CursoService.getAllCursos();
			    	};
			    	$scope.adicionarCurso = function(){
			    		var dialog4 = ngDialog.open({
			    			template: "app/modules/dashboard/curso/adicionar.html",
			    			scope:$scope,
			    			controller: ['$scope', 'CursoService', function ($scope, CursoService){
			    				$scope.adicionar = function(nombre){
			    					ngDialog.close(dialog4.id);
			    					CursoService.crearCurso({"nombre": nombre}).then(function(result){
			    						$scope.cursos = [];
			    						$scope.cursos = CursoService.getAllCursos();
			    					},
			    					function(error){
			    						console.error(error);
			    					});
			    				}
			    			}]
			    		})
			    	}
//			    	$scope.registros=RegistroService.getAllRegistros();
			    	$scope.adicionarRegistro = function(){
			    		console.log($scope.reg);
			    					RegistroService.crearRegistro($scope.reg).then(function(result){
			    							console.log(result);
			    							$scope.reg={};
			    					},
			    					function(e){
			    						Flash.create('danger', e.data.errorMessage + " El estudiante ya fue inscrito, intente con otros parametros!", 'small-text');
			    						console.error(e);
			    					});
			    	}
			    	
			    }]
			});
        	
        			
        }
}
})();