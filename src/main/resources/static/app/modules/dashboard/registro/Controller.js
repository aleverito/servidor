var log = {};
log.ok= "color: #14f546;padding: 2px;";
log.info = "color: #18e8ff;padding: 2px;";
log.warn = "color: #ffe608;padding: 2px;";
log.error = "color: #ffffff;	background: #d41103;padding: 2px;";
log.fx = "color:white;padding: 2px;";
log.wait = "color: #1c89bd;background: #19252b;padding:2px";
(function(){
'use strict';
angular.module('app').controller('MaestroController',MaestroController);
MaestroController.$inject = ['MaestroService', '$modal','$scope','Upload', '$timeout','Flash','ngDialog'];
function MaestroController( MaestroService, $modal,$scope , Upload , $timeout ,Flash,ngDialog) {
	
		var self = this;
		self.user = {};
		self.users=[];
		self.smartTablePageSize=10;
		self.submit = submit;
		self.getAllUsers = getAllUsers;
		self.createUser = createUser;
		$scope.filter = {};
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
		self.rowCollection = getAllUsers();
		$scope.$on('actualizar', function() {
				$scope.clearFilter();
		});
		$scope.clearFilter = function(){
			MaestroService.loadAllUsers().then(function(){
				var v = getAllUsers();
				console.log(v,v.length);
				self.rowCollection=[];
				for(var i=0;i<v.length;i++)
					self.rowCollection.push(v[i]);
			},function(){});
		};
		$scope.clickToOpen = function (id) {
			ngDialog.open({
				template: '/app/modules/dashboard/configFotoUsuario/'+id,
				controller:'DialogCtrl',
				controllerAs:'ctrlUpload',
				className: 'ngdialog-theme-default p-t-0 p-b-0'
			});
		};
		
		function submit() {
			console.log('%c[INFO]%cSubmitting... ',log.info,log.fx);
			if (self.user.id === undefined || self.user.id === null) {
				console.log('%c[INFO]Guardando [nuevo] Maestro. %o',log.wait, self.user);
				createUser(self.user);
			} else {
				updateUser(self.user, self.user.id);
				console.log('%c[INFO]%cUser updated with id '+ self.user.id,log.info,log.fx);
			}
			
		}

		function createUser(user) {
			console.log('%c[INFO]About to create user',log.info);
			MaestroService.createUser(user)
				.then(
					function (response) {
						console.log('%c[OK]%cMaestro creado Exitosamente!!',log.ok,log.fx);
						$scope.$broadcast('actualizar');
//                        self.successMessage = 'User created successfully';
//                        self.errorMessage='';
						Flash.create('success', 'Maestro creado exitosamente', 'small-text');
						self.done = true;
						self.user={};
						$scope.myForm.$setPristine();
					},
					function () {
						console.error('%c[ERROR]Error mientras creaba Maestro',log.error);
						Flash.create('danger', 'Error mientras creando Maestro: '+errResponse.data.errorMessage, 'small-text');
					}
				);
		}


		function updateUser(user, id){
			console.log('%c[INFO]%cAbout to update user',log.info,log.fx);
			MaestroService.updateUser(user, id)
				.then(
					function (response){
						console.log('%c[OK]%cMaestro actualizado Correctamente',log.ok,log.fx);
						Flash.create('info', 'Maestro actualizado Correctamente!: ', 'large-text');
						$scope.$broadcast('actualizar');
						self.done = true;
						$scope.myForm.$setPristine();
					},
					function(errResponse){
						console.error('%c[ERROR]Error mientras editaba maestro ' + id + ', Error :' + errResponse.data,log.error);
						Flash.create('danger', 'Error mientras editaba maestro ' + id + ', Error :' + errResponse.data.errorMessage, 'large-text');
					}
				);
		}


		function removeUser(rda){
			console.log('%c[INFO]%cAcerca para borrar Maestro con rda %s',log.info,log.fx,rda);
			MaestroService.removeUser(rda).then(
					function(){
						$scope.$broadcast('actualizar');
						console.log('%c[OK]%cMaestro '+rda + ' borrado correctamente',log.ok,log.fx);
					},
					function(errResponse){
						Flash.create('danger', 'Error mientras eliminaba Maestro: '+rda +', Error :'+errResponse.data, 'large-text');
						console.error('%cError mientras eliminaba Maestro  '+rda +', Error :'+errResponse.data,log.error);
					}
				);
		}


		function getAllUsers(){
			return MaestroService.getAllUsers();
		}

		function editUser(id) {
			MaestroService.getUser(id).then(
				function (user) {
					self.user = user;
					self.user.rda_old=user.rda;
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
						console.error('%c[ERROR]Error mientras editaba usuario ' + id + ', Error :' + errResponse.data,log.error);
				}
			);
		}
		function reset(){
			self.user={};
			$scope.myForm.$setPristine(); //reset Form
		}
}
})();

	angular.module('app').controller("DialogCtrl",DialogCtrl);
	DialogCtrl.$inject = ["$scope",'ngDialog','$uploadImage','Upload','$timeout','$http','$window','Flash','toastr','toastrConfig'];
	function DialogCtrl($scope,ngDialog,$uploadImage,Upload,$timeout,$http,$window,flash,toastr,toastrConfig){
		$scope.options = {
			      autoDismiss: false,
			      positionClass: 'toast-bottom-full-width',
			      type: 'warning',
			      timeOut: '5000',
			      extendedTimeOut: '2000',
			      allowHtml: false,
			      closeButton: false,
			      tapToDismiss: true,
			      progressBar: true,
			      newestOnTop: true,
			      maxOpened: 0,
			      preventDuplicates: false,
			      preventOpenDuplicates: true,
			      title: "Algun titulo de ejemplo",
			      msg: "Escriba su mensaje aqui"
			    };
		$scope.guardarDisable=true;
		$scope.hacer = function(){
			$scope.guardarDisable=false;
		}
		var self = this;
			self.upload = function(dataUrl,name,idusuario){
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
					url: '/api/usuario/upload/photo/'+idusuario,
					enctype:'multipart/form-data',
					data: {
		                file: blob,
						foto:dataUrl2,
						nombre:name
					}
				}).then(function (response) {
					$scope.openToast("Cargar foto de Perfil","Se almaceno correctamente la Foto!","success");
					$timeout(function(){
						$scope.guardarDisable=true;
//						self.result = response.data;
					},$scope.options.timeOut);
				}, function (response) {
					if (response.status > 0) self.errorMsg = response.status 
						+ ': ' + response.data;
				}, function (evt) {
					self.progress = parseInt(100.0 * evt.loaded / evt.total);
				});
			}
			$scope.download = function(id){
				$scope.downloadDisable='disabled';
				$http.get('/api/download/user/'+id).then(
				function(response){
					switch(response.status){
						case 200:
							$window.open(response.data,'windowLoadImage','height=300,width=400');
							$scope.downloadDisable='';
							break;
						case 204:
							$scope.openToast("Recurso no asignado","El archivo de imagen no existe!","info");
							$timeout(function(){
								$scope.downloadDisable='';
							},$scope.options.timeOut);
							break;
					}
				},function(e){
					console.error(e);
				});
			}
			var openedToasts = [];
			$scope.openToast = function (title,msg,type) {
				angular.extend(toastrConfig, $scope.options);
				openedToasts.push(toastr[type](msg,title));
		    };
			$scope.ok=function(){
				ngDialog.close();
			},
			$scope.cancel=function(){
				ngDialog.close();
			}
	}
