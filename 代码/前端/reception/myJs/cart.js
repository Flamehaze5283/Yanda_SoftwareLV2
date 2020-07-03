$(() => {

	//商品数量在输入时的正则判断替换
	$('.number-input').keyup(function() {
		if (this.value.length == 1) {
			this.value = this.value.replace(/[^1-9]/g, '1')
		} else {
			this.value = this.value.replace(/\D/g, '')
		}
	})
	$('.number-input').change(function() {
		if (this.value == '') {
			this.value = 1
		}
	})

	//获取购物车列表
	$.ajax({
		type: 'get',
		data: {
			mode: 'list'
		},
		url: ip_addr + '/ShoppingCartServlet',
		dataType: 'json',
		xhrFields: {
			withCredentials: true
		},
		success: function(res) {
			let total_price = 0 //总价格
			$('#total-sum').text(res.data.length)
			for (let i = 0; i < res.data.length; i++) {
				$.ajax({
					async: false,
					type: 'get',
					data: {
						productId: res.data[i].id,
						mode: 'detail'
					},
					url: ip_addr + "/manage/product",
					dataType: 'json',
					xhrFields: {
						withCredentials: true
					},
					success: function(result) {
						//动态产生列表
						const div = $('<div></div>')
						div.addClass('item')
						var content =
							`<label class="checkbox">
								<input type="checkbox" class="vam" data-productid=${res.data[i].id} ${res.data[i].checked?'checked':''} />
							</label>
							<div class="good-area">
								<a href="detail.html?good_id=${res.data[i].id}" class="p-img"><img src=${img_addr}${result.msg.mainImage} alt=""></a>
								<ul>
									<li><a href="detail.html?good_id=${res.data[i].id}" class="p-name">${result.msg.name}</a>
										<p class="p-info">${result.msg.subtitle}</p>
									</li>

									<li>
										<div class="p-price" ><span>¥&nbsp;${result.msg.price}</span></div>
									</li>

									<li>
										<div class="number-cont">
											<span class="cut btn">-</span>
											<input class="number-input" data-productid=${res.data[i].id} maxlength="4" name="num-good" value="${res.data[i].quantity}">
											<span class="add btn">+</span>
										</div>
									</li>

									<li class="p-price-total">
										¥&nbsp;<span class='mytotal' data-theprice=${result.msg.price}>${new Decimal(res.data[i].quantity).mul(new Decimal(result.msg.price))}</span>
									</li>

									<li>
										<a href="javascript:;" data-productid=${res.data[i].id} class="p-del">删除</a>
									</li>
								</ul>
							</div>`

						div.html(content)
						$('.items-box').append(div)
						//添加结束
						//计算价格
						if (res.data[i].checked) {
							total_price = new Decimal(total_price).add(new Decimal(new Decimal(res.data[i].quantity).mul(new Decimal(
								result.msg.price))))
						}
						//将价格添加到总价上
						$('#total-price').text(total_price)
						//判断是否全选
						if (!res.data[i].checked) {
							$('#selectAll').prop('checked', false)
						}
					}
				})
			}

			//产生列表结束
			isEmpty()
		}
	})

	//向服务器发送选中请求
	function selectRequest(ProductId) {
		$.ajax({
			type: 'get',
			data: {
				productId: ProductId,
				mode: 'select'
			},
			url: ip_addr + '/ShoppingCartServlet',
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			},
			success: function(res) {
				let total = 0
				let total_num = 0
				for (let i = 0; i < $('.vam').length; i++) {
					if ($($('.vam')[i]).prop('checked')) {
						total_num++
						total = new Decimal(total).add(new Decimal($($('.mytotal')[i]).text()))
					}
					$('#total-price').text(total)
					$('#total-sum').text(total_num)
				}
			}
		})
	}
	//像服务器发送未选中请求
	function unSelectRequest(ProductId) {
		$.ajax({
			type: 'get',
			data: {
				productId: ProductId,
				mode: 'un_select'
			},
			url: ip_addr + '/ShoppingCartServlet',
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			},
			success: function(res) {
				let total = 0
				let total_num = 0
				for (let i = 0; i < $('.vam').length; i++) {
					if ($($('.vam')[i]).prop('checked')) {
						total_num++
						total = new Decimal(total).add(new Decimal($($('.mytotal')[i]).text()))
					}
					$('#total-price').text(total)
					$('#total-sum').text(total_num)
				}
			}
		})
	}

	//全选
	$('#selectAll').change(() => {
		if ($('#selectAll').prop('checked')) {
			for (let i = 0; i < $('.vam').length; i++) {
				$($('.vam')[i]).prop('checked', true)
				//向服务器发送全选请求
				selectRequest($($('.vam')[i]).data('productid'))
			}

		} else {
			for (let i = 0; i < $('.vam').length; i++) {
				$($('.vam')[i]).prop('checked', false)
				//向服务器发送未选中请求
				unSelectRequest($($('.vam')[i]).data('productid'))
			}
		}
	})
	//为每个选择事件添加change
	$('.items-box').on('change', '.vam', function() {
		$('#selectAll').prop('checked', true)
		for (let j = 0; j < $('.vam').length; j++) {
			if (!$($('.vam')[j]).prop('checked')) {
				$('#selectAll').prop('checked', false)
				break
			}
		}
		if ($(this).prop('checked')) {
			selectRequest($(this).data('productid'))
		} else {
			unSelectRequest($(this).data('productid'))
		}

	})

	function delProduct(ProductId) {
		$.ajax({
			async: false,
			type: 'get',
			data: {
				productId: ProductId,
				mode: 'delete_product'
			},
			url: ip_addr + '/ShoppingCartServlet',
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			}
		})
	}

	//数量改变后
	function Update(ProductId, Count, obj) {
		$.ajax({
			type: 'get',
			data: {
				mode: 'update',
				productId: ProductId,
				count: Count
			},
			url: ip_addr + '/ShoppingCartServlet',
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			},
			success: function() {
				var priceSpan = obj.parents('ul').children('.p-price-total').children()
				var thePrice = priceSpan.data('theprice')
				var pre = priceSpan.text()
				var current = new Decimal(thePrice).mul(new Decimal(Count))
				priceSpan.text(current)
				if (obj.parents('.item').children('.checkbox').children('.vam').prop('checked')) {
					$('#total-price').text(new Decimal($('#total-price').text()).add(new Decimal(current).sub(new Decimal(pre))))
				}
			}
		})

	}


	//数量改变
	$('.items-box').on('change', '.number-input', function() {
		Update($(this).data('productid'), $(this).val(), $(this))
	})
	//数量的加减
	$('.items-box').on('click', '.btn', function() {
		var cur = $(this).siblings('.number-input').val()
		if ($(this).hasClass('add')) {
			cur++;
		} else {
			if (cur > 1) {
				cur--;
			}
		}
		$(this).siblings('.number-input').val(cur)
		Update($(this).siblings('.number-input').data('productid'), cur, $(this))
	})

	//删除操作
	//删除每一项item
	$('.items-box').on('click', '.p-del', function() {
		delProduct($(this).data('productid'))
		$(this).parents('.item').remove()
		let total = 0
		let total_num = 0
		for (let i = 0; i < $('.vam').length; i++) {
			if ($($('.vam')[i]).prop('checked')) {
				total_num++
				total = new Decimal(total).add(new Decimal($($('.mytotal')[i]).text()))
			}
			$('#total-price').text(total)
			$('#total-sum').text(total_num)
		}
		if(isEmpty()){
			$('#total-price').text('0')
			$('#total-sum').text('0')
		}
	})
	//清空所有项items-box
	$('#p-del-all').click(function() {
		for (let j = 0; j < $('.p-del').length; j++) {
			delProduct($($('.p-del')[j]).data('productid'))
		}
		$('.items-box').empty()
		$('#total-price').text('0')
		$('#total-sum').text('0')
		isEmpty()
	})

	//items-box为空时
	function isEmpty() {
		if ($('.items-box').children('.item').length === 0) {
			const img = $('<img />')
			img.attr('src', 'images/emptyCart.png')
			img.css({
				'display': 'block',
				'margin': '10px auto'
			})
			$('.items-box').append(img)
			return true
		}
	}

	//点击结算
	$('.pay-bill').click(function() {
		let order_list = []
		for (let i = 0; i < $('.vam').length; i++) {
			if ($($('.vam')[i]).prop('checked')) {
				let order = {}
				order.good_id = $($('.vam')[i]).data('productid')
				order.good_num = $($('.number-input')[i]).val()
				order_list.push(order)
			}
		}
		sessionStorage.setItem('order_list', JSON.stringify(order_list))
		if (order_list.length === 0) {
			alert('请添加商品')
		} else {
			location.href = "confirm_order.html"
		}
	})

})
