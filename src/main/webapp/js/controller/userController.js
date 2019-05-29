 //控制层 
app.controller('userController' ,function($scope,$controller,userService){	
	
	$controller('baseController',{$scope:$scope});//继承
 
	//获取验证码
	$scope.getcode=function(){
		userService.getcode($scope.entity.phone).success(
			function(response){
				if (response.success) {
					$scope.code=response.message;
				} else {
					alert("验证码获取错错误");
				}
			}
		)	
	}
	//用户注册
	$scope.userRegister=function(){
		if ($scope.password!=$scope.entity.password) {
			alert("两次密码输入不一致");
			return ;
		}
		if ($scope.verificationCode==null) {
			alert("验证码不能为空");
			return ;
		}
		if ($scope.verificationCode!=$scope.code) {
			alert("验证码错误");
			return ;
		}
		userService.userRegister($scope.entity).success(
			function(response){
				alert(response.message)
				if (response.success) {
					window.location.href="login.html";
				} 
			}
		);	
	}
	
	$scope.showName=function(){
		userService.showName().success(
			function(response){
				$scope.loginName=response.username;
			}
		);
	}
    
});	