$(() => {
	var userId
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
				userId = res.msg.id
			} else {
				location.href = 'login.html'
			}
		}
	})
	$.ajax({
		type: "get",
		data: {
			mode: 'list'
		},
		url: ip_addr + "/shipping",
		dataType: "json",
		xhrFields: {
			withCredentials: true
		},
		success: function(res) {
			if (res.status === 0) {
				var list = res.data.list
				for (let i = 0; i < list.length; i++) {
					const tr = $('<tr></tr>')
					tr.addClass('next-table-row')
					var content =
						`<td class="next-table-cell first" role="gridcell">
										<div class="next-table-cell-wrapper">${list[i].receiver_name}</div>
									</td>
									<td class="next-table-cell" role="gridcell">
										<div class="next-table-cell-wrapper"><span>${list[i].receiver_province} ${list[i].receiver_city} ${list[i].receiver_district}</span></div>
									</td>
									<td class="next-table-cell" role="gridcell">
										<div class="next-table-cell-wrapper">${list[i].receiver_address}</div>
									</td>
									<td class="next-table-cell" role="gridcell">
										<div class="next-table-cell-wrapper" >${list[i].receiver_zip}</div>
									</td>
									<td class="next-table-cell" role="gridcell">
										<div class="next-table-cell-wrapper" ><span>${list[i].receiver_mobile}<br></span></div>
									</td>
									<td class="next-table-cell" role="gridcell">
										<div class="next-table-cell-wrapper">
											<div class="tAction">
												</span><a data-sid=${list[i].id} class="t-delete">删除</a>
											</div>
										</div>
									</td>
									<td class="next-table-cell last" role="gridcell">
										<div class="next-table-cell-wrapper" data-next-table-row="0">
											<div><span class="t-default">默认地址</span></div>
										</div>
									</td>`
					tr.html(content)
					$('.next-table-body').append(tr)
				}
			}
		}
	})


	//提交地址的表单
	layui.use('form', function() {
		var form = layui.form
		//监听提交
		form.on('submit(submit-addr)', function(data) {
			var address = data.field
			address.userId = userId
			$.ajax({
				type: "get",
				data: address,
				url: ip_addr + "/shipping?mode=add",
				dataType: "json",
				xhrFields: {
					withCredentials: true
				},
				success: function(res) {
					if (res.status === 0) {
						alert('添加地址成功~~~')
						location.reload()
					}
				}
			})

			return false;
		})
	})


	$('.next-table-body').on('click', '.t-delete', function() {
		var obj=$(this).parents('.next-table-row')
		$.ajax({
			type: 'get',
			data: {
				mode: 'del',
				shippingId: $(this).data('sid')
			},
			url: ip_addr + '/shipping',
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			},
			success: function(res) {
				obj.remove()
			}
		})
	})



})
