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
      type:'post'
      ,datatype:'json'
      ,url:'/product/getCart'
      ,success:function (res) {
          alert()
          $('#cartlist').append('<div class="orderListItem">\n' +
              '        <table class="orderListItemTable" orderStatus="waitReview" oid="9733">\n' +
              '            <tr class="orderListItemFirstTR">\n' +
              '                <td colspan="2">\n' +
              '                    <b>2019-02-28 14:11:11</b>\n' +
              '                    <span>订单号: 201902281411116043057\n' +
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
              '                    <a class="deleteOrderLink" oid="9733" href="#nowhere">\n' +
              '                        <span  >删除</span>\n' +
              '                    </a>\n' +
              '\n' +
              '                </td>\n' +
              '            </tr>\n' +
              '\n' +
              '            <tr class="orderItemProductInfoPartTR" >\n' +
              '                <td class="orderItemProductInfoPartTD"><img width="80" height="80" src="img/productSingle_middle/3533.jpg"></td>\n' +
              '                <td class="orderItemProductInfoPartTD">\n' +
              '                    <div class="orderListItemProductLinkOutDiv">\n' +
              '                        <a href="foreproduct?pid=353">科沃斯地宝扫地机器人家用智能擦窗机器人玻璃双星拍档朵朵&amp;窗宝</a>\n' +
              '                        <div class="orderListItemProductLinkInnerDiv">\n' +
              '                            <img src="/static/images/creditcard.png" title="支持信用卡支付">\n' +
              '                            <img src="/static/images/7day.png" title="消费者保障服务,承诺7天退货">\n' +
              '                            <img src="/static/images/promise.png" title="消费者保障服务,承诺如实描述">\n' +
              '                        </div>\n' +
              '                    </div>\n' +
              '                </td>\n' +
              '                <td  class="orderItemProductInfoPartTD" width="100px">\n' +
              '\n' +
              '                    <div class="orderListItemProductOriginalPrice">￥10,660.00</div>\n' +
              '                    <div class="orderListItemProductPrice">￥9,061.00</div>\n' +
              '\n' +
              '\n' +
              '                </td>\n' +
              '\n' +
              '\n' +
              '                <td valign="top" rowspan="1" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">\n' +
              '                    <span class="orderListItemNumber">1</span>\n' +
              '                </td>\n' +
              '                <td valign="top" rowspan="1" width="120px" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">\n' +
              '                    <div class="orderListItemProductRealPrice">￥9,061.00</div>\n' +
              '                    <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>\n' +
              '                </td>\n' +
              '                <td valign="top" rowspan="1" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">\n' +
              '\n' +
              '                    <a href="forereview?oid=9733">\n' +
              '                        <button  class="orderListItemReview">评价</button>\n' +
              '                    </a>\n' +
              '                    <a href="forealipay?oid=7383&total=448.2">\n' +
              '                        <button class="orderListItemConfirm">付款</button>\n' +
              '                    </a>\n' +
              '                    <a href="foreconfirmPay?oid=6845">\n' +
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
  });
})
