/* 底部脚本 */
$(() => {
	var array
	var carouselArray
	//回到顶部
	$(window).scroll(function() {
		//为了保证兼容性，这里取两个值，哪个有值取哪一个
		//scrollTop就是触发滚轮事件时滚轮的高度
		let scrollDis = document.documentElement.scrollTop || document.body.scrollTop
		if (scrollDis < 800) {
			$('#top').hide()
		} else {
			$('#top').show()
		}
		if (scrollDis < 500) {
			$(".side-menu-tree").slideUp();
		} else{
			$(".side-menu-tree").slideDown();
		}
	})
	var currentPosition, timer
	var speed = 20
	$('#top').click(() => {
		timer = setInterval(goTotop, 1);
	})
	function goTotop() {
		currentPosition = document.documentElement.scrollTop || document.body.scrollTop
		currentPosition -= speed //speed变量
		if (currentPosition > 0) {
			window.scrollTo(0, currentPosition)
		} else {
			window.scrollTo(0, 0)
			clearInterval(timer)
		}
	}
	//加载分类选项
	$.ajax({
		type: "get",
		data: {
			mode: 'get_category',
			category_id:0
		},
		url: ip_addr + "/manage/category",
		dataType: "json",
		xhrFields: {
			withCredentials: true
		},
		success: function(res) {
			var category_list=res.msg.data
			console.log(category_list)
			for(let i=0;i< category_list.length;i++){
				const dt=$('<dt></dt>')
				dt.addClass('category-item')
				var content=`<a class="category" data-category-id=${category_list[i].id} href="javascript:;">${category_list[i].name}</a>`
				dt.html(content)
				$('.side-menu-tree').append(dt)
			}
			
		}
	})
	
	
	//加载商品
	$.ajax({
		type: "get",
		data: {
			pageNum: 1,
			pageSize: 10,
			mode: 'list'
		},
		url: ip_addr + "/manage/product",
		dataType: "json",
		xhrFields: {
			withCredentials: true
		},
		success: function(res) {
			console.log(res)
			array = res.msg.list
			carouselArray=res.msg.list
			$('.img1').prop('src',`${img_addr}${array[0].mainImage}`)
			$('.img2').prop('src',`${img_addr}${array[1].mainImage}`)
			$('.img3').prop('src',`${img_addr}${array[2].mainImage}`)
			for (let i = 0; i < array.length; i++) {
				const div = $('<div></div>')
				div.addClass('good-item')
				var content =`<div>
								<img class="good-img" src="${img_addr}${array[i].mainImage}" />
							</div>
							<div class="good-info">
								<div class="good-name">${array[i].name}</div>
								<div class="good-price">￥${array[i].price}</div>
							</div>`
				div.html(content)
				$('#good-box').append(div)
			}
		}
	})
	//点击轮播
	$('#index-carousel>div').click(function() {
		location.href="detail.html?good_id="+carouselArray[$(this).index()].id
	})
	//点击商品
	$('#good-box').on('click', 'div', function() {
		var index=$(this).index()
		location.href="detail.html?good_id="+array[index].id
	})
	//分类
	$('.side-menu-tree').on('click','.category',function(){
		console.log($(this).data('category-id'))
		$.ajax({
			type: "get",
			data: {
				mode:'get_product_id',
				category_id:$(this).data('category-id')
			},
			url: ip_addr + "/manage/product",
			dataType: "json",
			xhrFields: {
				withCredentials: true
			},
			success: function(res) {
				$('#good-box').html('')
				array = res.data.list
				for (let i = 0; i < array.length; i++) {
					const div = $('<div></div>')
					div.addClass('good-item')
					var content =`<div>
									<img class="good-img" src="${img_addr}${array[i].mainImage}" />
								</div>
								<div class="good-info">
									<div class="good-name">${array[i].name}</div>
									<div class="good-price">￥${array[i].price}</div>
								</div>`
					div.html(content)
					$('#good-box').append(div)
				}
			}
		})
	})
	
	
})
