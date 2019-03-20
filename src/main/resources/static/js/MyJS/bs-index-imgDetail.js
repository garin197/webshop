layui.use(['layer','table'],function(){
    $.ajax({
        type: 'post',
        url: '/product/imgList',
        datatype: 'json',
        data: {'productId':$('#input').val()},
        success: function (res) {
            //动态添加tr td
            var td_cover;
            for (var i=0;i<res.data.length;i++){
                var data=res.data[i];
                if (data.imgType == '1') {
                    td_cover = "<td><i class='layui-icon layui-icon-ok' style='font-size: 30px; color: #1E9FFF;'></i></td>";
                } else {
                    td_cover = "<td><i class='layui-icon ayui-icon-close' style='font-size: 30px; color: #c2c2c2;'></i></td>";
                }
                var tr = $(['<tr id="tr-"' + data.imgId + '>',
                    '<td><img id="img'+data.imgId+'" src="/' + data.imgUrl + '" style="width: 100%;height:auto" onmouseover="showImgTipsOnTableDetail(img'+data.imgId+')" onmouseout="closeImgTips()"/></td>',
                    td_cover,
                    '</tr>'].join(''));
                $('#img-detail-table-body').append(tr);

            }
        }
    });

});

function showImgTipsOnTableDetail(id) {
    /**
     * 图用tips放大，响应onmouseover
     */
    var tips2;
    var src = $(id).attr("src");
    tips2 = layer.tips('<div><img style="width:580px;height: 460px" src="' + src + '"/></div>', id
        , {tips: 2, time: 0, area: ['0px', '0px'], anim: -1});
}

function closeImgTips() {
    layer.closeAll();
}