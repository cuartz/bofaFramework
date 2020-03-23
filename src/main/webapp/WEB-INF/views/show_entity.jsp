<!DOCTYPE HTML>
<html lang="en">
  <head>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="assets/style.css" rel="stylesheet" type="text/css" />
  </head>
  <body ng-app="windApp">
    <div ng-controller="EntityCtrl" class="container">
           <p ng-repeat="(key,value) in filterHiddens(entity)">
        <span>{{key}} :</span><span>{{value}}</span>
        </p>
<!--         <span>{{student.id}}</span> -->
<!--         <span>{{student.name}}</span> -->
<!--         <span>{{student.age}}</span> -->
<!--         <span>{{student.classRoom}}</span> -->
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
