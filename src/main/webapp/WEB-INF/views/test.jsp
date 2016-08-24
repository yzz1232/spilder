<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>
<body>

<div ng-app="myApp" ng-controller="customersCtrl"> 

<table>
  <tr ng-repeat="x in names">
    <td>{{ x.companyName }}</td>
    <td>{{ x.year }}</td>
    <td>{{ x.jobName }}</td>
    <td>{{ x.address }}</td>
    <td>{{ x.salary }}</td>
  </tr>
</table>

</div>

<script>
var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    $http.get("/splider/getAllInfo.do")
    .success(function (response) {$scope.names = response;});
	
});
</script>

</body>
</html>
