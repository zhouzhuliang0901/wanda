var baseurl="http://zwdt.huangpuqu.sh.cn:8080/ac";
//var baseurl="http://hengshui.5uban.com/xhac";
//登陆类型选择
app.controller('loginType', function($state, $scope, appData) {
	$scope.operation = "请选择登录方式";
	$scope.idcardLogin = function() {
		$state.go("idcardLogin");
	}
	$scope.accountLogin = function() {
		$state.go("accountLogin");
	}
});
//身份证登陆
app.controller('idcardLogin', function($scope, $state, appData,$http) {
	$scope.isAlert=false;
	$scope.confirm=function(){
		$scope.isAlert=false;
	}
	$scope.idcardLogin = function(info) {
// 		console.log("这里会判断服务器是否有该身份证，是否签到，签到跳到签退页面，构造备注和id假数据");
// 		appData.note="备注假数据";
// 		appData.userId="145245";
// 		$state.go("signIn");
		if(info) {
			$http.jsonp(
				baseurl+"/aci/workPlatform/selfmUser/getUserByident.do",{
					params:	{
						jsonpCallback: "JSON_CALLBACK",
						identityNo:info.Number
					},
				}
			)
			.success(function(res){
				if(res.type=="success"){
					appData.userId=res.data.id;
					appData.userName=res.data.loginName;
					if(res.signInFlag.substring()=="true"){
						//appData.signInTime=res.signInTime.hours+":"+res.signInTime.minutes+":"+res.signInTime.seconds;
						//appData.signOutTime=res.signOutTime.hours+":"+res.signOutTime.minutes+":"+res.signOutTime.seconds;
						appData.note=res.context;//注意编码？？
						appData.signInTime=new Date(res.signIntime.time).toLocaleTimeString();
						if(res.signOutFlag.substring()=="true"){
							appData.signOutTime=new Date(res.signOutTime.time).toLocaleTimeString();
						}
						$state.go("signOut");
					}else{
						$state.go("signIn");
					}
				}else if(res.type=="fail"){
					$scope.isAlert=true;
					$scope.messge=res.msg;
				}
			})
			.error(function(){
			});
		} else {
			$scope.isAlert=true;
			$scope.messge="未获取身份信息";			
		}
	}
});
//账号登陆
app.controller("accountLogin",function($scope, $http,$state,appData){
	$scope.operation="请输入用户名密码";
	$scope.isAlert=false;
	$scope.confirm=function(){
		$scope.isAlert=false;
	}
	$scope.accountlogin=function(){
		//输入验证
		if($("#username").val()==""||$("#username").val()==null||$("#username").val()==undefined){
			$scope.isAlert=true;
			$scope.message="用户名不能为空";
			return;
		}
		if($("#password").val()==""||$("#password").val()==null||$("#password").val()==undefined){
			$scope.isAlert=true;
			$scope.message="密码不能为空";
			return;
		}
// 		console.log("这里会判断服务器是否有该身份证，是否签到，签到跳到签退页面，构造备注和id假数据");
// 		appData.note="备注假数据";
// 		appData.userId="145245"; 
// 		$state.go("signIn");
		$http.jsonp(
			baseurl+"/aci/workPlatform/selfmUserSign/userSignLogin.do"
			//"http://zwdt.huangpuqu.sh.cn:8080/ac/aci/workPlatform/selfmUserSign/userSignLogin.do"
			
			,{
				params:	{
					usercode:$("#username").val(),
					password:$("#password").val(),
					jsonpCallback: "JSON_CALLBACK"
				},
			}
		)
		.success(function(res){
			if(res.type=="loginSuccess"){
				appData.userId=res.data.id;
				appData.userName=res.data.loginName;
				console.log(res.signInFlag==true);
				if(res.signInFlag.substring()=="true"){
					//appData.signInTime=res.signIntime.hours+":"+res.signIntime.minutes+":"+res.signIntime.seconds;
					appData.note=res.context;//注意编码？？
					appData.signInTime=new Date(res.signIntime.time).toLocaleTimeString();
					if(res.signOutFlag.substring()=="true"){
						appData.signOutTime=new Date(res.signOutTime.time).toLocaleTimeString();
					}
					//appData.signOutTime=res.signOutTime.hours+":"+res.signOutTime.minutes+":"+res.signOutTime.seconds;
					$state.go("signOut");
				}else{
					$state.go("signIn");
				}
			}else if(res.type=="loginFail"){
				$scope.isAlert=true;
				$scope.message=res.msg;
			}
		})
		.error(function(){
		})
	}
});
//签到
app.controller("signIn",function($scope,$interval,$http,$state,appData){
	$scope.isAlert=false;
	$scope.confirm=function(){
		$scope.isAlert=false;
	}
	$scope.theTime = new Date().toLocaleTimeString();
    $interval(function () {
        $scope.theTime = new Date().toLocaleTimeString();
    }, 1000);
	$scope.signin=function(){
// 		console.log("这里签到请求,这里构造了假数据，数据从服务器拿");
// 		appData.signInTime=$scope.theTime;
// 		$state.go("signOut");
// 		return;
		$http.jsonp(
			baseurl+"/aci/workPlatform/selfmUserSign/saveSignInTime.do",{
				params:	{
					jsonpCallback: "JSON_CALLBACK",
					id:appData.userId,
					name:appData.userName,
					signPlace:appData.uniqueId
				},
			}
		)
		.success(function(res){
			if(res.type=="success"){
				appData.signInTime=new Date(res.data.dtSignIn.time).toLocaleTimeString();
				$state.go("signOut");
			}else{
				$scope.isAlert=true;
				$scope.message="签到失败,请重试";
			}
		})
		.error(function(){
		});
	}
});
app.controller("signOut",function($scope,$http,appData,$interval){
	$scope.signoutTime=appData.signOutTime;
	
	$scope.isAlert2=false;
	$scope.confirm=function(){
		$scope.isAlert2=false;
	}
	
	$scope.isAlert=false;
	$scope.signinTime=appData.signInTime;
	$scope.note=appData.note;
	
	$scope.theTime = new Date().toLocaleTimeString();
	$interval(function () {
	    $scope.theTime = new Date().toLocaleTimeString();
	}, 1000);
	
	//退签
	$scope.signout=function(){
// 		console.log("这里发退签请求");
// 		return;
		$http.jsonp(
			baseurl+"/aci/workPlatform/selfmUserSign/updateSignOutTime.do",{
				params:	{
					jsonpCallback: "JSON_CALLBACK",
					id:appData.userId,
					signPlace:appData.uniqueId
				},
			}
		)
		.success(function(res){
			if(res.type=="success"){
				$scope.isAlert2=true;
				$scope.message=res.msg;
				$scope.signoutTime=new Date(res.data.dtSignOut.time).toLocaleTimeString();
			}else if(res.type=="fail"){
				$scope.isAlert2=true;
				$scope.message=res.msg;
			}
		})
		.error(function(){
		});
	}
	
	//修改备注
	$scope.saveNote=function(){
// 		console.log("这里发修改备注请求,成功修改页面信息");
// 		appData.note=$scope.note=$("#note").val();
// 		$scope.isAlert=false;
// 		return;
		$http.jsonp(
			baseurl+"/aci/workPlatform/selfmUserSign/updateSignContext.do",{
				params:	{
					jsonpCallback: "JSON_CALLBACK",
					id:appData.userId,
					stContext:$("#note").val()
				},
			}
		)
		.success(function(res){
			if(res.type=="success"){
				appData.note=$scope.note=$("#note").val();
				$scope.isAlert=false;
			}else if(res.type=="fail"){
			}
		})
		.error(function(){
		});
	}
	
	
	$scope.__open = function(){
		$scope.isAlert = true;
	}
	$scope.__close = function(){
		$scope.isAlert = false;
	}
});