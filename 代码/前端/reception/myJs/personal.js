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
				$('#username').text(res.msg.username)
			} else {
				location.href = 'index.html'
			}
		}
	})

	$.ajax({
		type: "get",
		data: {
			mode: 'list'
		},
		url: ip_addr + "/order",
		dataType: "json",
		xhrFields: {
			withCredentials: true
		},
		success: function(res) {
			if (res.status === 0) {
				var list = res.data.list
				for (let i = list.length-1; i >=0 ; i--) {
					const div = $('<div></div>')
					div.addClass('order-item')
					var content = `<div class="order-title">
												<span class="order-date">${list[i].createTime}</span>
												<span>订单号：<span class="order-number">${list[i].orderNo}</span></span>
											</div>
											<div class="order-content">
												<a href="detail.html?good_id=${list[i].orderItemVoList[0].productId}" class="order-img"><img src="${img_addr}${list[i].orderItemVoList[0].productImage}"/></a>
												<ul>
													<li>
														<a href="detail.html?good_id=${list[i].orderItemVoList[0].productId}" class="order-name">${list[i].orderItemVoList[0].productName}</a>
														<p class="sub-name">${list[i].orderItemVoList[0].description}</p>
													</li>
													<li>
														<span class="order-price">￥${list[i].orderItemVoList[0].totalPrice}</span>
													</li>
													<li>
														<span class="order-quantity">×${list[i].orderItemVoList[0].quantity}</span>
													</li>
													<li>
														<span class="order-pay">￥${list[i].payment}</span>
													</li>
													<li>
														<div class="order-state">${judgeStatus(list[i].status,list[i].orderNo)}</div>
														<div><a href="order_detail.html?orderNo=${list[i].orderNo}"class="order-detail"} >订单详情</a></div>
													</li>
													<li>
														<div><a href="javascript:;"class="order-del">删除</a></div>
													</li>
												</ul>
											</div>`
					div.html(content)
					$('.order-list-items').append(div)
				}

			}
		}
	})
	//判断商品付款状态
	function judgeStatus(status,orderNo){
		if(status===1){
			return '<a target=_blank href=pay.html?orderNo='+orderNo+'>未付款</a>'
		}
		else if(status===2)
		{
			return '付款成功'
		}
		else if(status===3){
			return '已发货'
		}
	}
	
	
})
