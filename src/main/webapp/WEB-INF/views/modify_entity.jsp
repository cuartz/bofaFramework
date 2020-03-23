<!DOCTYPE HTML>
<html lang="en">
  <head>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="assets/style.css" rel="stylesheet" type="text/css" />
  </head>
  <body ng-app="windApp">
    <div ng-controller="EntityCtrl" class="container">
      <form ng-submit="modifyEntity()" name="messageForm">     
      <hr />
      <p ng-repeat="(key,value) in entity">
     	<span>{{key}} :</span> <input type="text" ng-model="entity[key]" />
      </p>
      <button ng-disabled="entity.id.length === 0">Save</button>
      </form>
    </div>
    
    <script src="libs/sockjs/sockjs.min.js" type="text/javascript"></script>
    <script src="libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>
    <script src="libs/angular/angular.min.js"></script>
    <script src="libs/lodash/dist/lodash.min.js"></script>
    <script src="app/app.js" type="text/javascript"></script>
    <script src="app/controllers.js" type="text/javascript"></script>
    <script src="app/services.js" type="text/javascript"></script>
  </body>
</html>
