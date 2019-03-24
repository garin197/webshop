// 主页js


//加载layui组件--开始
layui.use(['carousel', 'layer'], function () {
    var carousel = layui.carousel;//轮播组件
    carousel.render({
        elem: '#carousel'
        , width: '100%' //设置容器宽度
        , arrow: 'always' //始终显示箭头
        , height: "500px"
        , interval: 3000
        //,anim: 'updown' //切换动画方式
        , autoPlay: true

    });

    //轮播的连接地址--开始
    $('#a-carousel-img-1').attr("href", "/page/err");
    $('#a-carousel-img-2').attr("href", "/page/err");
    $('#a-carousel-img-3').attr("href", "/page/err");
    //轮播的连接地址--结束

    //动态拼接--加载首页展示的商品--开始
    var categoryName;

    // 加载电器-开始
    categoryName='电器';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                    res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#dianqi').append(row);//想服装栏添加数据
            }
        },
        error:function () {

        }
    });

    // 加载电器-结束


    // 加载服装--开始
    categoryName='服装';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                        res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#fuzhuang').append(row);//想服装栏添加数据
            }
        },
        error:function () {
            
        }
    });
    // 加载服装--结束

    // 加载食品饮料--开始
    categoryName='食品饮料';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                    res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#shipinyinliao').append(row);//想服装栏添加数据
            }
        },
        error:function () {

        }
    });
    // 加载食品饮料--结束

    // 加载鞋靴-开始
    categoryName='鞋靴';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                    res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#xiexue').append(row);//想服装栏添加数据
            }
        },
        error:function () {

        }
    });
    // 加载鞋靴-结束

    // 加载手机数码-开始
    categoryName='手机数码';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                    res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#shoujishuma').append(row);//想服装栏添加数据
            }
        },
        error:function () {

        }
    });
    // 加载手机数码-结束

    // 加载生活家居-开始
    categoryName='生活家居';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                    res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#shenghuojiaju').append(row);//想服装栏添加数据
            }
        },
        error:function () {

        }
    });
    // 加载生活家居-结束

    // 加载手表-开始
    categoryName='手表';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                    res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#shoubiao').append(row);//想服装栏添加数据
            }
        },
        error:function () {

        }
    });
    // 加载手表-结束

    // 加载化妆品-开始
    categoryName='化妆品';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                    res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#huazhuangpin').append(row);//想服装栏添加数据
            }
        },
        error:function () {

        }
    });
    // 加载化妆品-结束

    // 加载箱包-开始
    categoryName='箱包';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                    res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#xiangbao').append(row);//想服装栏添加数据
            }
        },
        error:function () {

        }
    });
    // 加载箱包-结束

    // 加载饰品-开始
    categoryName='饰品';
    $.ajax({
        type:'post',//以post的方法发送请求
        datatype:'json',//接受json数据
        data:{"page":1,"limit":5,"categoryName":categoryName},//每页响应的分类加载5个商品
        url:'/product/index_product_list',//请求数据的地址
        ascyn:true,//采用异步请求方式
        success:function (res) {//请求成功回调函数
            var row;
            // 动态拼接
            for (var i = 0; i < res.data.length; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="/product/index_product_detail?pid='+res.data[i].productId+'"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="/product/index_product_detail?pid='+res.data[i].productId+'">' +
                    '<span class="productItemDesc">' +
                    res.data[i].product.productName +
                    '</span>' +
                    '</a>' +
                    '<span class="productPrice">' +
                    '￥' + res.data[i].product.promotePrice +
                    '</span>' +
                    '</div>' +
                    '</div>';
                $('#shipin').append(row);//想服装栏添加数据
            }
        },
        error:function () {

        }
    });
    // 加载饰品-结束

    //加载首页展示的商品--结束


});
//加载layui组件--结束