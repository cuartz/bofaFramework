<!DOCTYPE HTML>
<html lang="en">
   <head>
      <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
      <link href="../assets/style.css" rel="stylesheet" type="text/css" />
   </head>
   <body ng-app="windApp">
      <div ng-controller="EntityCtrlDynamic" class="container">
         <p ng-repeat="(key,value) in filterHiddens(entity)">        <span>{{key}} :</span><span>{{value}}</span>        </p>
      </div>
      <script src="../libs/sockjs/sockjs.min.js" type="text/javascript"></script>    
      <script src="../libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>    
      <script src="../libs/angular/angular.min.js"></script>   
      <script src="../libs/lodash/dist/lodash.min.js"></script>   
      <script src="../app/app.js" type="text/javascript"></script>   
      <script src="../app/controllers.js" type="text/javascript"></script>   
      <script type="text/javascript">(function(angular) {	  
      angular.module("windApp.controllers").controller("EntityCtrlDynamic", function($scope, EntityService) {	    
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
       if (!value.hasOwnProperty('windId')) {            
       result[key] = value;        
       }    
       });    
       return result;};	  
       });	
       })(angular);</script>    
       <script src="../app/services.js" type="text/javascript"></script>  
   </body>
</html>