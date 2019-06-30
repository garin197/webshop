/**
 * 设置用户登录状态下的header栏显示
 */
function ff() {

    if ($('#login-username-span').text() != '') {
        document.getElementById("out").style.display = 'block';
        document.getElementById("register-span").style.display = 'none';
        document.getElementById("login-username").style.display = 'none';
    }
}

//响应工具栏搜索的按钮
$(function () {

    $('#search-btn').click(
        function () {
            var statement = $('#input-search').val();
            if (statement != "") {
                window.location.href = '/page/s_by_prdct/' + statement
            }
        }
    );
    $('#input-search').keydown(
        function (event) {
            if (event.keyCode == 13) {
                $('#search-btn').click();
            }
        }
    );
});


/**
 * 展示购物车弹出层
 */
function showshoppingcart_prodDetl() {
    layer.open({
        type: 2
        , content: '/page/shopingcart'
        , area: [document.documentElement.clientWidth + 'px', document.documentElement.clientHeight + 'px']
        , maxmin: true
        , title: "购物车"
    });
}

/**
 *
 */
function logout() {
    $.ajax({
        type: 'post',
        url: '/user/logout',
        success: function (res) {
            document.getElementById("out1").style.display = 'none';
            document.getElementById("register-span1").style.display = 'block';
            window.location.reload();
            $('#login-username-span').text("请登录");
        }
    });
}

layui.use(['layer'], function () {

    //判断并且设置可购买状态--开始
    var stock = parseInt($('#Stock').text());
    if (stock <= 0) {
        $('#suddenlyBuy').attr("disabled", "disabled");
        $('#addToBuyCar').attr("disabled", "disabled");
    } else {
        $('#suddenlyBuy').attr("disabled", false);
        $('#addToBuyCar').attr("disabled", false);
    }
    //判断并且设置可购买状态--结束

    //加载评论数--start
    //todo 2019/04/15
    var pid = $("#hidden_input_productId").val();
    $.get(
        '/product/review/26868',
        {"pid": pid},
        function (res) {
            $("#review-count").text("(" + res + ")");
        }
    );
    //加载评论数--end

    //加载属性-start
    $.get(
        '/product/property/get',
        {"pid": pid},
        function (res) {
            for (var i = 0; i < res.data.length; i++) {
                $('#property-div').append(
                    '<span>' + res.data[i].propertyName + ":" + res.data[i].propertyValue + '</span>'
                );
            }
        }
    );
    //加载属性-end

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

//提交用户信息表单
function submit_user_info() {
    if ($("#address").val() == "") {
        $("#address").val().focus();
        return false;
    }
    if ($("#receiver").val() == "") {
        $("#receiver").val().focus();
        return false;
    }
    if ($("#mobile").val() == "") {
        $("#mobile").val().focus();
        return false;
    }
    $.ajax({
        type: 'post'
        , datatype: 'json'
        , url: '/product/buy'
        , data: {
            "address": $("#address").val(),
            "post": $("#post").val(),
            "receiver": $("#receiver").val(),
            "mobile": $("#mobile").val(),
            "comment": $("#comment").val(),
            "num": $('.productNumberSetting', parent.document).val(),
            "pname": $(".productTitle", parent.document).text(),
            "money": $(".promotionPrice", parent.document).text(),
            "imgUrl": $("#img-detail-0", parent.document)[0].src,
            "productId": $('#hidden_input_productId', parent.document).val()
        }
        , success: function (res) {
            if (res == "success") {
                layer.closeAll();
                layer.open({
                        title: '我的订单',
                        area: [document.documentElement.clientWidth + 'px', document.documentElement.clientHeight + 'px'],
                        type: 2,
                        content: '/page/myorders'
                    }
                );
                // parent.location.href="/page/myorders";
            } else {

            }
        }
    });
}

//响应我的订单按钮
function MyOrder_prodDetl() {
    var page = "/user/forecheckLogin";
    $.ajax({
        type: 'post'
        , url: page
        , datatype: 'json'
        , data: {'uri': window.location.href}
        , success: function (result) {
            if (result == "success")
                layer.open({
                        title: '我的订单',
                        area: [document.documentElement.clientWidth + 'px', document.documentElement.clientHeight + 'px'],
                        type: 2,
                        content: '/page/myorders'
                    }
                );
            else {

                // 提示登录
                layer.closeAll();
                window.location.href = '/user/login';
            }
        }

    });
}

//展示评论--start
function showReview() {
    layer.open({
        type: 1,
        title: "累计评论",
        content: $('#review-content-script').html(),
        // data:{'uid':uid,"ordercreatedate":ordercreatedate,"productName":productName},
        area: ['600px', '400px'],
        shadeClose: true,
        scollBar: true
    });

    var pid = $("#hidden_input_productId").val();
    $.get(
        '/product/review/get',
        {"pid": pid},
        function (res) {
            for (var i = 0; i < res.data.length; i++) {

                $('#review-content-div').append(
                    '<p style="font-family: 楷体;background-color: #ADC8E6">' + "用户:" + res.data[i].userName + "   " + res.data[i].createDate + '  </p>\n' +
                    '        <p>' + res.data[i].content + '</p>'
                );
            }
        }
    );

}

//展示评论--end

//校验输入--start
//输入非法字符 phoneNum.length == 0
function checkPhone() {
    var phone = document.getElementById("mobile");
    var phoneNum = phone.value;
    if (phoneNum.length == 0) {
            layer.tips('警告: 请输入正确的11位手机号码！', "#tr_phone", {tips: 3});
    }
    if (phoneNum.length == 1) {
        if (phoneNum == '0') {
            layer.tips('输入非法', "#tr_phone", {tips: 3});
        }
    }
    if (phoneNum.length>11){
        phone.value=phoneNum.substring(0,11);
    }
}

//检查邮政编码
function checkPost() {
    var post=document.getElementById("post");
    var postNumber=post.value;
    if (postNumber.length==0) {
        layer.tips('警告: 请输入正确的6位邮政号码！', "#tr_phone", {tips: 3});
    }
    if (postNumber.length>6){
        post.value=postNumber.substring(0,6);
    }
}

//校验输入--end