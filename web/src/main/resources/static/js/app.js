angular.module('users', ['ngRoute']).config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'user-page.html',
        controller: 'userCtr'
    })
}).controller('userCtr', function ($scope, $http) {
    $http.get('users').success(function (data) {
    	//data/["_embedded"].users
        $scope.userList = data;
    });
});