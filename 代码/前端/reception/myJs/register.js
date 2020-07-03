$(() => {
	$('#che_agree').click(() => {
		if ($('#che_agree').prop("checked")) {
			$('#register_btn').removeAttr("disabled")
		} else {
			$('#register_btn').attr('disabled', true)
		}
	})

	$('.am-form-field').focus(function() {
		this.select()
	})
	$('.inputvalue').blur(function() {
		if ($(this).val() == '') {
			$(this).css('border', '1px solid red')
			$(this).siblings(".inputValue").show()
		} else {
			$(this).css('border', '')
			$(this).siblings(".inputValue").hide()
		}
	})
	$('#username').blur(function(){
		if($(this).val()!=''){
			$.ajax({
				type:'get',
				data:{
					mode:'check_valid',
					str:$(this).val(),
					type:'username'
				},
				url:ip_addr+'/user',
				dataType:'json',
				xhrFields:{
					withCredentials:true
				},
				success:function(res){
					console.log(res)
					if(res.status===1){
						alert('用户名已存在')
						$('#username').val('')
						$('#username').focus()
					}
				}
			})
		}
	})


	$('#register_btn').click(() => {
		var username = $('#username').val()
		var phone = $('#phone').val()
		var password = $('#password').val()
		var repassword = $('#repassword').val()
		var email = $('#email').val()
		var question = $('#question').val()
		var answer = $('#answer').val()

		if (username == '' || phone == '' || password == '' || repassword == '' || email == '') {
			layui.use('layer', function() {
				var layer = layui.layer
				layer.open({
					title: false,
					content: "请输入必要的信息",
					icon: 2,
					area: ['200px', '120px']
				});
			});
		} else {
			if (password != repassword) {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('两次输入的密码不一样！', {
						area: ['300px', '50px'],
						time: 1000
					});
				});
			} else {
				$.ajax({
					type: "get",
					data: {
						username: username,
						password: password,
						email: email,
						phone: phone,
						question: question,
						answer: answer
					},
					url: ip_addr + "/user?mode=register",
					dataType: "json",
					xhrFields: {
						withCredentials: true
					},
					success: function(msg) {
						window.location.href = 'regedit2.html'
					}
				})

			}

		}

	})
	


})
