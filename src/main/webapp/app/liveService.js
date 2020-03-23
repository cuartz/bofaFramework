
(function(angular, SockJS, Stomp, _, undefined) {
	  angular.module("windApp.services").service("EntityService", function($q, $timeout) {
	    

		  var service = {};
	    service.RECONNECT_TIMEOUT = 30000;
	    service.SOCKET_URL = "/wind/entities";
	    service.ENTITY_TOPIC = "/topic/entity";
	    
	    service.receive = function() {
	      return listener.promise;
	    };
	    
	    var reconnect = function(socketService,entity,entityName,listener) {
	      $timeout(function() {
	    	  socketService=initialize(entity,entityName,listener);
	      }, this.RECONNECT_TIMEOUT);
	    };
	    
	    var getEntityMessage = function(data) {
	      var message = JSON.parse(data);
	      /*, out = {};
	      out.message = message.message;
	      out.time = new Date(message.time);
	      if (_.contains(messageIds, message.id)) {
	        out.self = true;
	        messageIds = _.remove(messageIds, message.id);
	      }*/
	      return message;
	    };
	    
	    var startListener = function(socket,listener,windId,entityName) {
	      socket.stomp.subscribe(service.ENTITY_TOPIC+"/"+entityName+"/"+windId, function(data) {
	        listener.notify(getEntityMessage(data.body));
	      });
	    };
	    
	    service.initialize = function(windIdParam,entityNameParam) {
	    	//if (!listener){
	    		//var listener = $q.defer()
	    	//}
		    var socketService = {
		    	socket : {
			      client: null,
			      stomp: null
	    		},
			    listener : $q.defer(),
			    windId:windIdParam,
			    entityName:entityNameParam
			};
		    socketService.socket.stomp = Stomp.over(new SockJS(service.SOCKET_URL));
		    socketService.socket.stomp.connect({},  function(frame) {

	            socketService.socket.stomp.subscribe(service.ENTITY_TOPIC+"/"+socketService.entityName+"/"+socketService.windId, function(data){
	            	socketService.listener.notify(getEntityMessage(data.body));
	            });
	        });
		    		
		    		
		    		
		    		//startListener(socketService.socket,socketService.listener,socketService.windId,socketService.entityName));
	      //socket.stomp.onclose = reconnect(socketService,windId,entityName,listener);
	      socketService.receive = function() {
		      return this.listener.promise;
		    };
	      return socketService;
	    };
	    
	    
	    return service;
	  });
	})(angular, SockJS, Stomp, _);