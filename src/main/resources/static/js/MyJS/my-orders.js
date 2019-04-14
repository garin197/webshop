// 请求订单信息
$(function () {
    layui.use(['layer'], function () {
    });
    $.ajax({
        type: 'post'
        , datatype: 'json'
        , url: '/product/myorders'
        , success: function (res) {
            for (var i = 0; i < res.data.length; i++) {

                $('#myorderlist').append('<div class="orderListItem" id="cartList-div' + i + '">\n' +
                    '<table class="orderListItemTable" orderStatus="waitReview" pid="' + res.data[i].productId + '">\n' +
                    '                <input type="hidden" id="orderId" name="orderId" value="' + res.data[i].orderId + '">\n' +
                    '                <input type="hidden" id="orderitemId" name="orderitemId" value="' + res.data[i].orderItemId + '">\n' +
                    '                <tr class="orderListItemFirstTR">\n' +
                    '                    <td colspan="2">\n' +
                    '                        <b>' + res.data[i].order.createDate + '</b>\n' +
                    '                        <span>订单号: ' + res.data[i].order.orderCode + '\n' +
                    '\t\t\t\t\t</span>\n' +
                    '                    </td>\n' +
                    '                    <td colspan="2"><img width="13px" src=""></td>\n' +
                    '                    <td colspan="1">\n' +
                    '                        <a class="wangwanglink" href="#nowhere">\n' +
                    '                            <div class="orderItemWangWangGif"></div>\n' +
                    '                        </a>\n' +
                    '\n' +
                    '                    </td>\n' +
                    '                    <td class="orderItemDeleteTD">\n' +
                    '                        <a id="deleteOrder' + i + '" class="deleteOrderLink" onclick="delOrderItem(' + res.data[i].orderId + "," + res.data[i].orderItemId + "," + i + ')" pid="' + res.data[i].productId + '" oid="' + res.data[i].orderId + '" oiid="' + res.data[i].orderItemId + '"  href="#">\n' +
                    '                            <span>删除</span>\n' +
                    '                        </a>\n' +
                    '\n' +
                    '                    </td>\n' +
                    '                </tr>\n' +
                    '\n' +
                    '                <tr class="orderItemProductInfoPartTR">\n' +
                    '                    <td class="orderItemProductInfoPartTD"><img width="80" height="80" id="productimg' + i + '" src=""></td>\n' +
                    '                    <td class="orderItemProductInfoPartTD">\n' +
                    '                        <div class="orderListItemProductLinkOutDiv">\n' +
                    '                            <a href="#" onclick="productDetail(' + res.data[i].productId + ')">' + res.data[i].product.productName + '</a>\n' +
                    '                            <div class="orderListItemProductLinkInnerDiv">\n' +
                    '                                <img src="/static/images/creditcard.png" title="支持信用卡支付">\n' +
                    '                                <img src="/static/images/7day.png" title="消费者保障服务,承诺7天退货">\n' +
                    '                                <img src="/static/images/promise.png" title="消费者保障服务,承诺如实描述">\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </td>\n' +
                    '                    <td class="orderItemProductInfoPartTD" width="100px">\n' +
                    '\n' +
                    '                        <div class="orderListItemProductPrice">￥' + res.data[i].product.promotePrice + '</div>\n' +
                    '\n' +
                    '\n' +
                    '                    </td>\n' +
                    '\n' +
                    '\n' +
                    '                    <td valign="top" rowspan="1" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">\n' +
                    '                        <span class="orderListItemNumber">' + res.data[i].number + '</span>\n' +
                    '                    </td>\n' +
                    '                    <td valign="top" rowspan="1" width="120px"\n' +
                    '                        class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">\n' +
                    '                        <div class="orderListItemProductRealPrice">￥' + res.data[i].product.promotePrice * res.data[i].number + '</div>\n' +
                    '                        <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>\n' +
                    '                    </td>\n' +
                    '                    <td valign="top" rowspan="1" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">\n' +
                    '\n' +
                    '                        <a id="review' + i + '" href="#" onclick="review('+res.data[i].userId+","+""+')">\n' +
                    '                        <button class="orderListItemReview">评价</button>\n' +
                    '                        </a>\n' +
                    '                        <a id="pay' + i + '" onclick="onPay(' + res.data[i].orderId + "," + res.data[i].product.promotePrice * res.data[i].number +
                    "," + "'" + res.data[i].user.userName + "'" + "," + "'" + res.data[i].order.address + "'" + "," + res.data[i].order.phone + "," + res.data[i].productId + "," + res.data[i].number + ')" href="#" >\n' +
                    '                        <button class="orderListItemConfirm">付款</button>\n' +
                    '                        </a>\n' +
                    '                        <a id="delivered' + i + '" href="#" onclick="delivered(' + res.data[i].orderId + ')">\n' +
                    '                        <button class="orderListItemConfirm">确认收货</button>\n' +
                    '                        </a>\n' +
                    '                        <a id="tip' + i + '" href="#" onclick="tips()">\n' +
                    '                        <span>催卖家发货</span>\n' +
                    '                        </a>\n' +
                    '\n' +
                    '\n' +
                    '                    </td>\n' +
                    '\n' +
                    '                </tr>\n' +
                    '</table>' +
                    '            </div>');

                //加载订单项图片
                $.ajax({
                    type: 'post',
                    datatype: 'json',
                    url: '/product/getImgUrl',
                    async: false,
                    data: {"pid": res.data[i].productId},
                    success: function (res) {
                        if (res != null) {
                            $('#productimg' + i).attr("src", "/" + res);
                        }
                    }
                });

                // 改显示的显示，改隐藏的隐藏
                if (res.data[i].order.status == '未付款') {//未付款
                    $('#delivered' + i).hide();
                    $('#tip' + i).hide();
                    $('#review' + i).hide();
                }else if(res.data[i].order.status == "已收货"){//已收货
                    $('#delivered' + i).hide();
                    $('#pay' + i).hide();
                    $('#tip' + i).hide();
                } else {//已付款
                    if (res.data[i].order.deliver == "已发货") {
                        $('#pay' + i).hide();
                        $('#review' + i).hide();
                        $('#deleteOrder' + i).hide();
                        $('#tip' + i).hide();

                    } else {//未发货
                        $('#pay' + i).hide();
                        $('#delivered' + i).hide();
                        $('#review' + i).hide();
                        $('#deleteOrder' + i).hide();

                    }
                }

            }
        }
    });
})

// 父页面重定向到指定商品详情页面
function productDetail(pid) {
    layer.closeAll();
    parent.location.href = "/product/index_product_detail?pid=" + pid;
}

//删除订单项
function delOrderItem(orderId, orderItemId, index) {
    $.ajax({
        type: 'post',
        datatype: 'json',
        url: '/product/delOrderItem',
        data: {
            "orderId": orderId,
            "orderItemId": orderItemId
        },
        success: function (res) {
            if (res == "success") {
                $('#cartList-div' + index).remove();
            }
        }
    })
}

//付款
function onPay(orderId, momey, userName, address, phone, pid, number) {
    layer.open({
        type: 1,
        content: $('#pay-script').html()
        , area: ['400px', '450px'],
        title: "pay",
        shadeClose: true,
        success: function () {
            $('#pay-money').val("合计:￥" + momey);
            $('#pay-userName').val("收货人:" + userName);
            $('#pay-address').val("收货地:" + address);
            $('#pay-phone').val("手机号:" + phone);

            $('#pay-comfirm-btn').click(function () {
                $.ajax({
                    type: 'post',
                    datatype: 'json',
                    url: '/product/onPay',
                    data: {
                        "orderId": orderId,
                        "pid": pid,
                        "num": number
                    },
                    success: function (res) {
                        if (res == "success") {
                            layer.closeAll();
                            window.location.reload();
                        } else if (res == "failed") {
                            layer.msg("库存不足,交易失败");
                        }

                    }
                })
            });
        }
    });


}

function tips() {
    layer.msg("你想多了", {anim: 1});
}

//确认收货
function delivered(oid) {
    $.post(
        '/product/delivered/' + oid,
        function (res) {
            if (res == "success")
                window.location.reload();
        },
        'json'
    );
}