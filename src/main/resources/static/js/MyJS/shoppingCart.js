/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

// 请求购物车信息
$(function () {
    $.ajax({
        type: 'post'
        , datatype: 'json'
        , url: '/product/getCart'
        , success: function (res) {
            for (var i = 0; i < res.length; i++) {

                $('#cartlist').append('<div class="orderListItem" id="cartList-div">\n' +
                    '        <table class="orderListItemTable" orderStatus="waitReview" pid="' + res[i].productId + '">\n' +
                    '            <tr class="orderListItemFirstTR">\n' +
                    '                <td colspan="2">\n' +
                    '                    <b>' + res[i].addDate + '</b>\n' +
                    '                    <span>订单号: ' + res[i].orderCartCode + '\n' +
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
                    '                    <a class="deleteOrderLink" pid="' + res[i].productId +'" href="#nowhere">\n' +
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
                    '                        <a href="'+res[i].productUri+'">' + res[i].productName + '</a>\n' +
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
                    '                    <a href="forealipay?pid=' + res[i].productId + '&total=' + res[i].money * res[i].num + '" id="alipay">\n' +
                    '                        <button class="orderListItemConfirm">付款</button>\n' +
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
