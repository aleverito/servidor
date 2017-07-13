(function(){
	'use strict';
	angular.module('app').factory('$uploadImage',upload)
	upload.$inject = ['Upload'];
	function upload(Upload){
		
		// ***** Subir imagen al uri
        var upload = function (dataUrl, name,idusuario) {
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
	        return Upload.upload({
	            url: '/api/usuario/upload/photo/'+idusuario,
	            enctype:'multipart/form-data',
	            data: {
//	                file: blob,
	                foto:dataUrl2,
	                nombre:name
	            },
	        });
        };
        return {
			upload:upload
		};
	}
})();