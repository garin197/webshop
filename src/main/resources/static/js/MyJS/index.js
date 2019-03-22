// 主页js



layui.use(['carousel', 'layer'], function () {
    var carousel = layui.carousel;
    carousel.render({
        elem: '#carousel'
        , width: '100%' //设置容器宽度
        , arrow: 'always' //始终显示箭头
        , height: "500px"
        ,interval: 3000
        //,anim: 'updown' //切换动画方式
        ,autoPlay:true

    });

    //轮播的连接地址
    $('#a-carousel-img-1').attr("href","/page/err");
    $('#a-carousel-img-2').attr("href","/page/err");
    $('#a-carousel-img-3').attr("href","/page/err");
});
