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
            for (var i = 0; i < res.count; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="xxxxx?pid=89"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="xxxxx?pid=89">' +
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
            for (var i = 0; i < res.count; i++) {
                row =//自定义商品展示块
                    '<div class="productItem layui-col-md3">' +
                    '<a href="xxxxx?pid=89"><img width="100px" src="'+res.data[i].imgUrl+'"></a>' +
                    '<a class="productItemDescLink" href="xxxxx?pid=89">' +
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


    //加载首页展示的商品--结束


});
//加载layui组件--结束