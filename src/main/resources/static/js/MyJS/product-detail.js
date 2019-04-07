/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
/**
 * 设置用户登录状态下的header栏显示
 */
function ff() {
    if ($('#login-username-span1').text() != '') {
        document.getElementById("out1").style.display='block';
        document.getElementById("register-span1").style.display='none';
        document.getElementById("login-username1").style.display='none';
    }
}

/**
 * 展示购物车弹出层
 */
function showshoppingcart1(){
    layer.open({
        type:2
        ,content:'/page/shopingcart'
        ,area:[document.documentElement.clientWidth+'px',document.documentElement.clientHeight+'px']
        ,maxmin:true
    });
}

/**
 *
 */
function logout(){
    $.ajax({
        type:'post',
        url:'/user/logout',
        success:function(res){
            document.getElementById("out1").style.display='none';
            document.getElementById("register-span1").style.display='block';
            window.location.reload();
            $('#login-username-span').text("请登录");
        }
    });
}

layui.use(['layer'], function () {

    //判断并且设置可购买状态--开始
    var stock = parseInt($('#Stock').text());
    if (stock<=0){
        $('#suddenlyBuy').attr("disabled","disabled");
        $('#addToBuyCar').attr("disabled","disabled");
    }else{
        $('#suddenlyBuy').attr("disabled",false);
        $('#addToBuyCar').attr("disabled",false);
    }
    //判断并且设置可购买状态--结束


    // 加载指定的商品--开始

    // 加载产品的所有图片--开始
    $.ajax({
        type: "post",
        datatype: 'json',
        url: '/product/imgList',
        data: {"productId": $('#hidden_input_productId').val()},
        success: function (res) {
            //请求成功后回调，动态替换图片,注意前台最多展示5张图片，且仅有5张图片
            var size = res.data.length;
            var t = $('#product-img-div');
            for (var i = 0; i < size; i++) {
                $('#img-detail-' + i).attr('src', '/' + res.data[i].imgUrl);//设置小图片的url
                $('#img-detail-' + i).attr('onmouseover', 'Magnify_img("' + res.data[i].imgUrl + '")');//设置鼠标悬停的响应事件
                if (i == 0) {//默认设置第一个小图片为大图片
                    $('#img-detail-max').attr('src', '/' + res.data[i].imgUrl);//设置展示的大图片
                }
            }
        }
    });
    // 加载产品的所有图片--结束

    // 加载指定的商品--结束

});

//放大图片到指定区域
function Magnify_img(url) {
    $('#img-detail-max').attr('src', '/' + url);
}