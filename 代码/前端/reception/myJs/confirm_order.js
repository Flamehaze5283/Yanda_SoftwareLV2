$(() => {
	var order_list = JSON.parse(sessionStorage.getItem('order_list'))
	let total_price = 0
	var list = []
	var shippingId
	$.ajax({
		type: 'get',
		data: {
			mode: 'list'
		},
		url: ip_addr + '/shipping',
		dataType: 'json',
		xhrFields: {
			withCredentials: true
		},
		success: function(res) {
			if (res.status === 0) {
				list = res.data.list
				shippingId = list[0].id
				for (let i = 0; i < list.length; i++) {
					const div = $('<div></div>')
					div.addClass('addr-item')
					div.attr('data-shippingid', `${list[i].id}`)
					var content =
						`<div class="default-tips">默认地址</div>
								<div class="name-phone">${list[i].receiver_name} ${list[i].receiver_mobile}</div>
								<hr />
								<div class="address">${list[i].receiver_province} ${list[i].receiver_city} ${list[i].receiver_district} ${list[i].receiver_address}</div>`
					div.html(content)
					$('.addr-items').append(div)
				}
				$('.order-confirm-addr .confirmAddr-addr').text(
					`${list[0].receiver_province} ${list[0].receiver_city} ${list[0].receiver_district} ${list[0].receiver_address}`
				)
				$('.order-confirm-user .confirmAddr-addr').text(`${list[0].receiver_name} ${list[0].receiver_mobile}`)

			}
			else{
				alert('请添加地址')
				location.href='address.html'
			}
		}
	})

	for (let i = 0; i < order_list.length; i++) {
		$.ajax({
			async: false,
			type: 'get',
			data: {
				productId: order_list[i].good_id,
				mode: 'detail'
			},
			url: ip_addr + '/manage/product',
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			},
			success: function(res) {
				var div = $('<div></div>')
				div.addClass('order-item')

				var content =
					`<img src=${img_addr}${res.msg.mainImage} />
						<ul>
							<li>
								<span class="order-name">${res.msg.name}</span>
								<p class="order-subname">${res.msg.subtitle}</p>
							</li>
							<li>
								<span class="order-price">￥${res.msg.price}</span>
							</li>
							<li>
								<span class="order-quantity">×${order_list[i].good_num}</span>
							</li>
							<li>
								<span class="order-pay">￥${new Decimal(order_list[i].good_num).mul(new Decimal(res.msg.price))}</span>
							</li>
						</ul>`

				div.html(content)
				$('.order-items').append(div)
				//计算总价格
				total_price = new Decimal(total_price).add(new Decimal(new Decimal(order_list[i].good_num).mul(new Decimal(
					res.msg.price))))
				$('.payment').text(total_price)
			}
		})
	}


	$('.submit').click(function() {
		if (document.referrer === 'http://127.0.0.1:8848/reception/cart.html') {
			$.ajax({
				type: 'get',
				data: {
					mode: 'create',
					flag: 1,
					shipping_id: shippingId,
					payment: $('.payment').text(),
					comment: $('.comment').val()
				},
				url: ip_addr + '/order',
				dataType: 'json',
				xhrFields: {
					withCredentials: true
				},
				success: function(res) {
					window.open('pay.html?orderNo=' + res.data.orderNo, '_blank')
					location.href = 'cart.html'
				}
			})
		} else {
			$.ajax({
				type: 'get',
				data: {
					mode: 'create',
					flag: 0,
					product_id: order_list[0].good_id,
					quantity: order_list[0].good_num,
					shipping_id: shippingId,
					/* 改 */
					payment: $('.payment').text(),
					comment: $('.comment').val()
				},
				url: ip_addr + '/order',
				dataType: 'json',
				xhrFields: {
					withCredentials: true
				},
				success: function(res) {
					window.open('pay.html?orderNo=' + res.data.orderNo, '_blank')
					location.href = 'index.html'
				}
			})
			
		}
	})

	$('.addr-items').on('click', '.addr-item', function() {
		for (let i = 0; i < $('.addr-item').length; i++) {
			$($('.addr-item')[i]).css('border', '2px dashed #666')
		}
		$(this).css('border', '2px dashed #ff0000')
		$('.order-confirm-addr .confirmAddr-addr').text($(this).children('.address').text())
		shippingId = $(this).data('shippingid')
		$('.order-confirm-user .confirmAddr-addr').text($(this).children('.name-phone').text())

	})

})
