layui.use(['layer', 'form'], function () {
    layui.use('table', function () {
        var table = layui.table;
        var form=layui.form;
        var cid=$("#cid").val();
        layer.msg(cid);
        table.render({
            elem: '#datagridProperty'
            , url: '/property/list?cid='+cid
            , title: '用户数据表'
            ,toolbar: '#toolbar-property'
            , cols:
                [[
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'propertyId', title: 'ID', width: 180, fixed: 'left', unresize: true, sort: true}
                    , {field: 'propertyName', title: '属性名', width: 220, edit: 'text'}
                    , {fixed: 'right', title: '操作', toolbar: '#propertyBar'}
                ]]
            , page: true
        });

        form.on('submit(saveProperty)',function(data){
            layer.msg(data.field.propertyName);
            if (data.field.propertyName==""){
                layer.tips("当前为空",'#propertyName',{icon:2,tips:1,time:2000});
                return ;
            }
            $.ajax({
                type:'post',
                datatype: 'json',
                url: '/property/add?cid='+cid,
                data: {"propertyName": data.field.propertyName}
            });
        });

        table.on('toolbar',function (obj) {
            switch (obj.event) {
                case 'addProperty':
                    layer.open({
                        type:1,
                        area: ['400px','200px'],
                        content: $('#add-property-div').html()
                    });
                    break;
            }
        });


        //监听行工具事件
        //tool(lay-filter过滤组件值)
        //不加过滤lay-filter值默认响应所有的tool点击事件
        table.on('tool', function (obj) {
            var data = obj.data;
            if (obj.event === 'delProperty') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    $.ajax({
                        type: 'post',
                        url:'/property/del?id='+obj.data.propertyId,
                        success: function(msg){
                            layer.msg(msg);
                        }
                    });
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.prompt({
                    formType: 2
                    , value: data.email
                }, function (value, index) {
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }
        });


    });
});