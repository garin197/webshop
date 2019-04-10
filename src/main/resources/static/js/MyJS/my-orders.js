/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

// 请求订单信息
$(function () {
    layui.use(['layer'],function(){

    });

    $.ajax({
        type: 'post'
        , datatype: 'json'
        , url: '/product/myorders'
        , success: function (res) {
            for (var i = 0; i < res.data.length; i++) {

                $('#myorderlist').append('<div class="orderListItem" id="cartList-div">\n' +
                    '<table class="orderListItemTable" orderStatus="waitReview" pid="' +res.data[i].productId + '">\n' +
                    '                <input type="hidden" id="orderId" name="orderId" value="'+res.data[i].orderId+'">\n' +
                    '                <input type="hidden" id="orderitemId" name="orderitemId" value="'+res.data[i].orderItemId+'">\n' +
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
                    '                        <a class="deleteOrderLink" pid="' + res.data[i].productId +'" oid="'+res.data[i].orderId+'" oiid="'+res.data[i].orderItemId+'"  href="#nowhere">\n' +
                    '                            <span>删除</span>\n' +
                    '                        </a>\n' +
                    '\n' +
                    '                    </td>\n' +
                    '                </tr>\n' +
                    '\n' +
                    '                <tr class="orderItemProductInfoPartTR">\n' +
                    '                    <td class="orderItemProductInfoPartTD"><img width="80" height="80" id="productimg" src=""></td>\n' +
                    '                    <td class="orderItemProductInfoPartTD">\n' +
                    '                        <div class="orderListItemProductLinkOutDiv">\n' +
                    '                            <a href="#" onclick="productDetail('+res.data[i].productId+')">' + res.data[i].product.productName + '</a>\n' +
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
                    '                        <a href=forereview?pid=' + res.data[i].productId + '" style="display: none">\n' +
                    '                        <button class="orderListItemReview">评价</button>\n' +
                    '                        </a>\n' +
                    '                        <a href=forereview?pid=' + res.data[i].productId + '">\n' +
                    '                        <button class="orderListItemConfirm">付款</button>\n' +
                    '                        </a>\n' +
                    '                        <a href=forereview?pid=' + res.data[i].productId + '" style="display: none">\n' +
                    '                        <button class="orderListItemConfirm">确认收货</button>\n' +
                    '                        </a>\n' +
                    '\n' +
                    '\n' +
                    '                    </td>\n' +
                    '\n' +
                    '                </tr>\n' +
                    '</table>'+
                    '            </div>');

                //加载图片
                $.ajax({
                    type:'post',
                    datatype:'json',
                    url:'/product/getImgUrl',
                    data:{"pid":res.data[i].productId},
                    success:function (res) {
                        if (res!=null){
                            $('#productimg').attr("src" ,"/"+res);
                        }
                    }
                })

            }
        }
    });
})


function productDetail(pid){
    layer.closeAll();
    parent.location.href="/product/index_product_detail?pid="+ pid;
}