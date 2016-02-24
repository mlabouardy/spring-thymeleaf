angular.module('myApp', [ 'ui.bootstrap' ]).controller('MainCtrl',
		function($scope, $uibModal, $http, $location){
			$http.get('data/dial.json').then(function(data) {
				$scope.dials = data.data;
			});

			$scope.create = function() {
				var createModal = $uibModal.open({
					animation : true,
					templateUrl : 'views/create-modal.html',
					controller : 'CreateCtrl',
					resolve : {
						dials : function() {
							return $scope.dials;
						}
					}
				});

				createModal.result.then(function(user) {
					$scope.user = {
						username : user.username,
						description : user.description,
						phone : user.code + user.phone,
						sex : user.sex
					}
					
					$http.post('/users', JSON.stringify($scope.user)).then(function() {
						console.log('hello');
						toastr.success('Account has been created !', 'Whatsapp Finder');
					});
				});
			}
		}).controller('CreateCtrl', function($scope, $uibModalInstance, dials) {
	$scope.dials = dials;
	
	$scope.user={
			sex:"Male",
			description:"None description is available",
			username:"Username",
			phone:"874444512",
			code:"+33"
	}
	$scope.create = function() {
		$uibModalInstance.close($scope.user);
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});