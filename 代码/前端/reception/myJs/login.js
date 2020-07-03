$(document).ready(function() {
	
	$('.am-form-field').focus(function() {
		this.select()
	})
	$('#username_input').blur(function() {
		if (this.value == "") {
			$(this).css({
				'border': '1px solid #ff0000'
			})
			$('#user_p').text("请输入用户名")
		} else {
			$('#user_p').text("")
			$(this).css({
				'border': ''
			})
		}
	})
	$('#password_input').blur(function() {
		if (this.value == "") {
			$(this).css({
				'border': '1px solid #ff0000'
			})
			$('#password_p').text("密码不能为空")
		} else {
			$('#password_p').text("")
			$(this).css({
				'border': ''
			})
		}
	})
	
	
	$("#login_btn").click(function(){
		var username = $("#username_input").val()
		var password = $("#password_input").val()
		if (username == '' | password == '') {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请输入用户名或密码！', {
					area: ['200px', '50px'],
					time: 1000
				});
			});
		} else {
			//登录对接
			$.ajax({
				type: "get",
				data: {
					username: username,
					password: password,
					mode: 'login'
				},
				url: ip_addr + "/user",
				dataType: "json",
				xhrFields: {
					withCredentials: true
				},
				success: function(res) {
					if (res.status == 0) {
						location.href='index.html'
					} else {
						layui.use('layer', function() {
							var layer = layui.layer;
							layer.open({
								title: false,
								content: "用户名或密码错误",
								icon: 5,
								area: ['200px', '120px'],
							});
						});
					}
				}
			})
		}
	
	})
	//登录按钮绑定回车键
	$(document).keydown(function(e){
	    var curKey = e.keyCode
	    if(curKey == 13){
			$('#login_btn').click()
	    }
	})
})
