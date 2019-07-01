

// 请求购物车信息
$(function () {
    layui.use(['layer'], function () {

    });

    $.ajax({
        type: 'post'
        , datatype: 'json'
        , url: '/product/getCart'
        , success: function (res) {
            for (var i = 0; i < res.length; i++) {

                $('#cartlist').append('<div class="orderListItem" id="cartList-div">\n' +
                    '        <table class="orderListItemTable" orderStatus="waitReview" pid="' + res[i].productId + '">\n' +
                    '<input type="hidden" id="pid' + i + '" value="' + res[i].productId + '">' +
                    '<input type="hidden" id="num' + i + '" value="' + res[i].num + '">' +
                    '<input type="hidden" id="code' + i + '" value="' + res[i].orderCartCode + '">' +
                    '            <tr class="orderListItemFirstTR">\n' +
                    '                <td colspan="2">\n' +
                    '                    <b>' + res[i].addDate + '</b>\n' +
                    '                    <span id="ordercartcode">订单号: ' + res[i].orderCartCode + '\n' +
                    '\t\t\t\t\t</span>\n' +
                    '                </td>\n' +
                    '                <td  colspan="2"><img width="13px" src=""></td>\n' +
                    '                <td colspan="1">\n' +
                    '                    <a class="wangwanglink" href="#nowhere">\n' +
                    '                        <div class="orderItemWangWangGif"></div>\n' +
                    '                    </a>\n' +
                    '\n' +
                    '                </td>\n' +
                    '                <td class="orderItemDeleteTD">\n' +
                    '                    <a class="deleteOrderLink" pid="' + res[i].productId + '" href="#" onclick="delCartItem(' + res[i].productId + ')">\n' +
                    '                        <span  >删除</span>\n' +
                    '                    </a>\n' +
                    '\n' +
                    '                </td>\n' +
                    '            </tr>\n' +
                    '\n' +
                    '            <tr class="orderItemProductInfoPartTR" >\n' +
                    '                <td class="orderItemProductInfoPartTD"><img width="80" height="80" src="' + res[i].imgUrl + '"></td>\n' +
                    '                <td class="orderItemProductInfoPartTD">\n' +
                    '                    <div class="orderListItemProductLinkOutDiv">\n' +
                    '                        <a href="#" onclick="productDetail(' + res[i].productId + ')">' + res[i].productName + '</a>\n' +
                    '                        <div class="orderListItemProductLinkInnerDiv">\n' +
                    '                            <img src="/static/images/creditcard.png" title="支持信用卡支付">\n' +
                    '                            <img src="/static/images/7day.png" title="消费者保障服务,承诺7天退货">\n' +
                    '                            <img src="/static/images/promise.png" title="消费者保障服务,承诺如实描述">\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </td>\n' +
                    '                <td  class="orderItemProductInfoPartTD" width="100px">\n' +
                    '\n' +
                    // '                    <div class="orderListItemProductOriginalPrice">￥10,660.00</div>\n' +
                    '                    <div class="orderListItemProductPrice">￥' + res[i].money + '</div>\n' +
                    '\n' +
                    '\n' +
                    '                </td>\n' +
                    '\n' +
                    '\n' +
                    '                <td valign="top" rowspan="1" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">\n' +
                    '                    <span class="orderListItemNumber">' + res[i].num + '</span>\n' +
                    '                </td>\n' +
                    '                <td valign="top" rowspan="1" width="120px" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">\n' +
                    '                    <div class="orderListItemProductRealPrice">￥' + res[i].money * res[i].num + '</div>\n' +
                    '                    <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>\n' +
                    '                </td>\n' +
                    '                <td valign="top" rowspan="1" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">\n' +
                    '\n' +
                    '                    <a href="forereview?pid=' + res[i].productId + '" id="review" style="display: none">\n' +
                    '                        <button  class="orderListItemReview">评价</button>\n' +
                    '                    </a>\n' +
                    // forealipay?pid=' + res[i].productId + '&total=' + res[i].money * res[i].num + '
                    '                    <a href="#" id="alipay" onclick="alipay(' + i + ')">\n' +
                    '                        <button class="orderListItemConfirm">立即购买</button>\n' +
                    '                    </a>\n' +
                    '                    <a href="foreconfirmPay?pid=' + res[0].productId + '" id="firmPay" style="display: none">\n' +
                    '                        <button class="orderListItemConfirm">确认收货</button>\n' +
                    '                    </a>\n' +
                    '\n' +
                    '\n' +
                    '                </td>\n' +
                    '\n' +
                    '            </tr>\n' +
                    '\n' +
                    '\n' +
                    '        </table>\n' +
                    '\n' +
                    '    </div>')
            }
        }
    });

})

function alipay(i) {
    item = i;
    layer.open({
        type: 1
        , title: '输入收货地址'
        , area: ['600px', '560px']
        , content: $('#buyonescript').html()
        , success: function (layero, index) {


        }
    });
}

var item;

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
            "num": $('#num' + item).val(),
            "code": $('#code' + item).val(),
            "productId": $('#pid' + item).val(),

            // "pname": $(".productTitle", parent.document).text(),
            // "money": $(".promotionPrice", parent.document).text(),
            // "imgUrl": $("#img-detail-0", parent.document)[0].src,
            // "productId": $('#hidden_input_productId', parent.document).val()
        }
        , success: function (res) {
            // 跳到订单页面
            if (res == "success") {
                layer.closeAll();
                layer.open({
                        title: '我的订单',
                        area: [document.documentElement.clientWidth + 'px', document.documentElement.clientHeight + 'px'],
                        type: 2,
                        content: '/page/myorders'
                    }
                );

                //删除购物车记录 res[i].orderCartCode
                $.ajax({
                    type: 'post',
                    url: '/product/delCartItem',
                    data: {
                        "orderCartCode": $('#code', parent.document).val(),
                        "productId": $('#pid', parent.document).val()
                    }
                })


            } else {

            }
        }
    });
}

function productDetail(pid) {
    layer.closeAll();
    parent.location.href = "/product/index_product_detail?pid=" + pid;
}

function delCartItem(pid) {
    //删除购物车记录 res[i].orderCartCode
    $.ajax({
        type: 'post',
        url: '/product/delCartItem',
        data: {
            // "orderCartCode": $('#code', parent.document).val(),
            "pid": pid
        },
        success:function (res) {
            if (res=="success"){
                window.location.reload();
            }
        }
    });
}


//校验输入--start
//输入非法字符 phoneNum.length == 0
function checkPhone() {
    var phone = document.getElementById("mobile");
    var phoneNum = phone.value;
    if (phoneNum.length == 0) {
        layer.tips('警告: 请输入正确的11位手机号码！', "#tr_phone", {tips: 3});
        phone.value=phoneNum.substring(0,11);
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
        layer.tips('警告: 请输入正确的6位邮政号码！', "#tr_post", {tips: 3});
        post.value=postNumber.substring(0,6);
    }
    if (postNumber.length>6){
        post.value=postNumber.substring(0,6);
    }
}

//校验输入--end