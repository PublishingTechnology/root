'use strict';

/**
 * App Module 
 */

var viewsAdminApp = angular.module('viewsAdminApp', []);

viewsAdminApp.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}
	]);

viewsAdminApp.controller('AdminCtrl',
		function ($scope, $http) {
	$scope.viewId = null;
	$scope.viewDetails = null;
	
	$scope.domainCIType = "viewcis";
	$scope.contentSrvcUrl = "http://localhost:8080/content-srvc/";
	
	// Load the requested data from the content-srvc
	$scope.loadView = function () {
	    // Retrieve the selected viewId
		$scope.viewId = " ?? ";
		$scope.viewName = " ?? ";
		
	    if ($scope.viewIdInput != null && $scope.viewIdInput != "") {
		
			var srvcCall = $scope.contentSrvcUrl + $scope.domainCIType + "/" + $scope.viewIdInput;
	    	$http.get(srvcCall)
	        .success(function (data) {
	            $scope.viewDetails = data;
	            $scope.viewId = data.id;
	            $scope.viewName = data.viewName;
	        })
	        .error(function (data, status, headers, config) {
	            $scope.errorMessage = "Couldn't load the requested View [ " + $scope.viewIdInput + ", error # " + status;
	        });
	    } else {
	    	// No viewID supplied
	    	$scope.viewIdInput = "Please supply a view Id!";
	    	
	    }
	}			
}
);

