/*
 * Copyright © github@garin197 五邑大学
 */

$(function () {
    // 商品数标记
    layui.use(['layer', 'flow'], function () {
        var flow = layui.flow;
        //layui信息流加载--start
        flow.load({
            elem: '#container' //指定列表容器
            , done: function (page, next) { //到达临界点（默认滚动触发），触发下一页

                var lis = [];
                //page从1开始
                //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                $.get(
                    '/product/s_by_prdct/' + $('#s').val(),
                    {"page": page, "rows": 5},
                    function (res) {

                        if (res.data.length == 0) {
                            alert("没有该关键字的相关商品");
                            return false;
                        }
                        // 动态拼接页面元素--start

                        lis.push('<div class="eachHomepageCategoryProducts layui-row layui-col-space1">\n');

                        layui.each(res.data, function (index, item) {
                            row =//自定义商品展示块
                                // '<div class="htmleaf-container">' +
                                // '<div class="eachHomepageCategoryProducts layui-row layui-col-space1">\n' +
                                '<div class="productItem layui-col-md3">' +
                                '<a href="/product/index_product_detail?pid=' + item.productId + '"><img id="img-' + item.productId + '" width="100px" src="' + item.imgUrl + '"></a>' +
                                '<a class="productItemDescLink" href="/product/index_product_detail?pid=' + item.productId + '">' +
                                '<span class="productItemDesc">' +
                                item.productName +
                                '</span>' +
                                '</a>' +
                                '<span class="productPrice">' +
                                '￥' + item.promotePrice +
                                '</span>' +
                                // '</div>' +
                                // '</div>' +
                                '</div>';

                            lis.push(row);

                        });
                        lis.push('</div>');

                        //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                        //count为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                        next(lis.join(''), false);//page < res.count

                        //加载图片--start
                        layui.each(res.data, function (index, item) {

                            $.post(
                                '/product/getImgUrl',
                                {"pid": item.productId},
                                function (ires) {
                                    $('#img-' + item.productId).attr("src", "../../" + ires);
                                });
                        });
                        //加载图片--end
                    });

                // 动态拼接页面元素--end
            }
        });
        //layui信息流加载--end

    });

    // 请求参数c的商品列表--start
    $.ajax({
        url: '/product/s_by_cat/' + $('#c').val(),
        type: 'get',
        datatype: 'json',
        success: function (res) {

        }

    });
    // 请求参数c的商品列表--end
})


// 搜索结果页面--start
function f() {
    if ($('#login-username-span').text() != '') {
        document.getElementById("out").style.display = 'block';
        document.getElementById("register-span").style.display = 'none';
        document.getElementById("login-username").style.display = 'none';
    }
}

// 搜索结果页面--end

// 登出--start
function logout() {
    $.ajax({
        type: 'post',
        url: '/user/logout',
        success: function (res) {
            document.getElementById("out").style.display = 'none';
            document.getElementById("register-span").style.display = 'block';
            window.location.reload();
            $('#login-username-span').text("请登录");
        }
    });
}

// 登出--end

//展示购物车弹出层--start
function showshoppingcart_searchRes() {
    layer.open({
        type: 2
        , content: '/page/shopingcart'
        , title: "购物车"
        , area: [document.documentElement.clientWidth + "px", document.documentElement.clientHeight + 'px']
        , maxmin: true
    });
}

//展示购物车弹出层--end

//响应我的订单按钮--start
function MyOrder_searchRes() {
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

//响应我的订单按钮--end

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
            if (event.keyCode==13){
                $('#search-btn').click();
            }
        }
    );

});