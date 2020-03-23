(function(angular) {
  angular.module("windApp.controllers").controller("ChatCtrl", function($scope, ChatService) {
    $scope.messages = [];
    $scope.message = "";
    $scope.max = 140;
    
    $scope.addMessage = function() {
      ChatService.send($scope.message);
      $scope.message = "";
    };
    
    ChatService.receive().then(null, null, function(message) {
      $scope.messages.push(message);
    });
  });
})(angular);
/*
(function(angular) {
	  angular.module("windApp.controllers").controller("EntityCtrl", function($scope, EntityService) {
	    $scope.entity = {"id":"1","name":"Carlos"};
	    
	    $scope.modifyEntity = function() {
	        EntityService.send($scope.entity);
	        //$scope.message = "";
	      };
	    
	    EntityService.receive().then(null, null, function(entityMessage) {
	      $scope.entity=entityMessage;
	    });
	    
	    EntityService.initialize($scope.entity);
	  });
	})(angular);
*/
(function(angular) {	  
	angular.module("windApp.controllers").controller("EntityCtrl", function($scope, EntityService) {	    
		$scope.entity = {"id":1,"classRoom":null,"name":"Carlos","age":20,"windId":"1"};	    
		$scope.modifyEntity = function() {	       
			EntityService.send($scope.entity);	      
			};	   
			EntityService.receive().then(null, null, function(entityMessage) {	      
				$scope.entity=entityMessage;	   
				});	    
			EntityService.initialize($scope.entity,"com.project.demo.model.Student");
			$scope.filterHiddens = function(items) {    
				var result = {};    
				angular.forEach(items, function(value, key) {        
					if (value && !value.hasOwnProperty('windId')) {            
						result[key] = value;        
						}   
					});    
					return result;
					};	  
			});	
				})(angular);

var app = angular.module('app', []);

app.directive('dynamic', function ($compile) {
  return {
    restrict: 'A',
    replace: true,
    link: function (scope, ele, attrs) {
      scope.$watch(attrs.dynamic, function(html) {
        ele.html(html);
        $compile(ele.contents())(scope);
      });
    }
  };
});



function MyController($scope) {
  $scope.click = function(arg) {
    alert('Clicked ' + arg);
  }
  $scope.html = '<a ng-click="click(1)" href="#">Click me</a>';
}