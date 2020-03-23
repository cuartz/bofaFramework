(function(angular, SockJS, Stomp, _, undefined) {
  angular.module("windApp.services").service("ChatService", function($q, $timeout) {
    
    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, messageIds = [];
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/wind-demo/entities";
    service.CHAT_TOPIC = "/topic/message";
    service.CHAT_BROKER = "/app/entities";
    
    service.receive = function() {
      return listener.promise;
    };
    
    service.send = function(message) {
      var id = Math.floor(Math.random() * 1000000);
      socket.stomp.send(service.CHAT_BROKER, {
        priority: 9
      }, JSON.stringify({
        message: message,
        id: id
      }));
      messageIds.push(id);
    };
    
    var reconnect = function() {
      $timeout(function() {
        initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    var getMessage = function(data) {
      var message = JSON.parse(data), out = {};
      out.message = message.message;
      out.time = new Date(message.time);
      if (_.contains(messageIds, message.id)) {
        out.self = true;
        messageIds = _.remove(messageIds, message.id);
      }
      return out;
    };
    
    var startListener = function() {
      socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
        listener.notify(getMessage(data.body));
      });
    };
    
    var initialize = function() {
      socket.client = new SockJS(service.SOCKET_URL);
      socket.stomp = Stomp.over(socket.client);
      socket.stomp.connect({}, startListener);
      socket.stomp.onclose = reconnect;
    };
    
    initialize();
    return service;
  });
})(angular, SockJS, Stomp, _);


(function(angular, SockJS, Stomp, _, undefined) {
	  angular.module("windApp.services").service("EntityService", function($q, $timeout) {
	    
	    var service = {}, listener = $q.defer(), socket = {
	      client: null,
	      stomp: null,
	      entity:null,
	      entityName:null
	    };
	    
	    service.RECONNECT_TIMEOUT = 30000;
	    service.SOCKET_URL = "/wind-demo/entities";
	    service.ENTITY_TOPIC = "/topic/entity";
	    service.ENTITY_BROKER = "/app/entities";
	    
	    service.receive = function() {
	      return listener.promise;
	    };
	    
	    var reconnect = function() {
	      $timeout(function() {
	        initialize();
	      }, this.RECONNECT_TIMEOUT);
	    };
	    
	    service.send = function(message) {
	        //var id = Math.floor(Math.random() * 1000000);
	        socket.stomp.send(service.ENTITY_BROKER, {
	          priority: 9
	        }, JSON.stringify(message));//{ message: message}));
	       // messageIds.push(id);
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
	    
	    var startListener = function() {
	      socket.stomp.subscribe(service.ENTITY_TOPIC+"/"+entityName+"/"+entity.windId, function(data) {
	        listener.notify(getEntityMessage(data.body));
	      });
	    };
	    
	    service.initialize = function(varEntity,name) {
	      entity=varEntity;
	      entityName=name;
	      socket.client = new SockJS(service.SOCKET_URL);
	      socket.stomp = Stomp.over(socket.client);
	      socket.stomp.connect({}, startListener);
	      socket.stomp.onclose = reconnect;
	    };
	    
	    
	    return service;
	  });
	})(angular, SockJS, Stomp, _);