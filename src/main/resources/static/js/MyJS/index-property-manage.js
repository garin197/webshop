/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

layui.use(['layer', 'table'], function () {
    var table = layui.table;
    var cid = $("#product-category-property-id").val();
    var pid = $("#product-property-id").val();

    // 异步加载商品名--开始
    // $.ajax({
    //     type: 'post',
    //     datatype: 'json',
    //     url: '/product/bs-index-product-property-manage-getpName',
    //     ascyn: true,
    //     data: {"pid":pid},
    //     success: function (res) {
    //     }
    // });
    // 异步加载商品名--结束

    //初始化商品属性设置值得表格--开始
    var table_property=table.render({
        elem: '#datagridPropertymanage'
        , url: '/property/valList?cid=' + cid+'&pid='+pid
        , title: '/page/bg-login'
        // , toolbar: '#toolbar-property'
        , cols:
            [[
                {type: 'checkbox', fixed: 'left'}//多选框列
                , {field: 'propertyId', title: 'ID', width: 180, fixed: 'left', unresize: true, sort: true}
                , {field: 'propertyName', title: '属性名', width: 220,}
                , {field: 'propertyValue', title: '属性值', width: 220, edit: 'text'}

                // , {fixed: 'right', title: '操作', toolbar: '#propertyBar'}
            ]]
        , page: true
    });
    //初始化商品属性设置值得表格--结束


    //单元格编辑****属性管理表格--开始
    //edit（表格的filter值）
    table.on('edit(datagridPropertymanage)', function (obj) {

        $.ajax({
            type: 'POST',
            url: '/property/value',
            data: {
                "propertyId":obj.data.propertyId,
                "productId":$('#product-property-id').val(),
                "propertyValue":obj.data.propertyValue
            },
            sussess: function (flag) {
                //回调函数
                // if (flag > 0) {
                //     layer.msg("修改成功|success ");
                // } else {
                //     layer.msg("修改失败|failed ");
                // }
                // table.reload('datagrid1');
                layer.msg(flag)
            }
        });
    });
    //单元格编辑****属性管理表格--结束


    // form.on('submit(saveProperty)', function (data) {
    //     layer.msg(data.field.propertyName);
    //     if (data.field.propertyName == "") {
    //         layer.tips("当前为空", '#propertyName', {icon: 2, tips: 1, time: 2000});
    //         return;
    //     }
    //     $.ajax({
    //         type: 'post',
    //         datatype: 'json',
    //         url: '/property/add?cid=' + cid,
    //         data: {"propertyName": data.field.propertyName}
    //     });
    // });

    table.on('toolbar', function (obj) {
        switch (obj.event) {
            case 'addProperty':
                layer.open({
                    type: 1,
                    area: ['400px', '200px'],
                    content: $('#add-property-div').html()
                });
                break;
        }
    });

});