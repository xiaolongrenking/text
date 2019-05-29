//服务层
app.service('userService',function($http){
	//获取验证码
	this.getcode=function(phone){
		return $http.post('../user/code.do?phone='+phone)
	}
	//注册
	this.userRegister=function(entity){
		return $http.post('../user/register.do',entity)
	}
	//读取列表数据绑定到表单中
	this.showName=function(){
		return $http.get('../user/findLoginUser.do');		
	}
});
