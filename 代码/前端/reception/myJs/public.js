$(() => {
	$.ajax({
		type: "get",
		data: {
			mode: 'get_user_info'
		},
		url: ip_addr + "/user",
		dataType: "json",
		xhrFields: {
			withCredentials: true
		},
		success: function(res) {
			if (res.status === 0) {
				$('#login').css('display', 'none')
				$('#register').css('display', 'none')
				$('#userself').css('display', '')
				$('#personal').attr('href', 'personal.html')
				$('#cart').attr('href', 'cart.html')
			} else {
				$('#login').css('display', '')
				$('#register').css('display', '')
				$('#userself').css('display', 'none')
				$('#personal').attr('href', 'login.html')
			}
		}
	})
	//退出登录
	$('.logout').click(() => {
		$.ajax({
			type: 'get',
			data: {
				mode: 'logout'
			},
			url: ip_addr + '/user',
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			},
			success: function(res) {
				alert('退出成功')
				location.reload()
			}
		})
	})
})
