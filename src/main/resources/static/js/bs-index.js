layui.use(['element', 'table', 'layer', 'form', 'laypage'], function () {
    var table1 = layui.table;
    var table2 = layui.table;
    var form2 = layui.form;

    //监听提交
    form2.on('submit(save)', function (data) {
        params = data.field;
        submit($, params);
        return false;
    });

    //表格初始化
    table2.render({
        elem: '#datagrid2'
        , url: '/category/list'//数据请求url
        , toolbar: '#toolbar2'
        , title: '分类管理'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'categoryId', title: '类型ID', width: 120, fixed: 'left', unresize: true, sort: true}
            , {field: 'categoryName', title: '类型', width: 200, edit: 'text'}
            , {fixed: 'right', title: '操作', toolbar: '#bar2', width: 250}
        ]]
        , page: true
        , height: 520
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        }
        , done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度

            console.log(res);

            //得到当前页码
            console.log(curr);

            //得到数据总量
            console.log(count);
        }
    });

    table1.render({
        elem: '#datagrid1'
        , url: '/page/data'//数据请求url
        , toolbar: '#toolbar1'
        , title: '商品管理'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true}
            , {field: 'username', title: '用户名', width: 120, edit: 'text'}
            , {
                field: 'email', title: '邮箱', width: 150, edit: 'text', templet: function (res) {
                    return '<em>' + res.email + '</em>'
                }
            }
            , {field: 'sex', title: '性别', width: 80, edit: 'text', sort: true}
            , {field: 'city', title: '城市', width: 180}
            , {field: 'sign', title: '签名', width: 180}
            , {field: 'experience', title: '积分', width: 80, sort: true}
            , {field: 'ip', title: 'IP', width: 120}
            , {field: 'logins', title: '登入次数', width: 100, sort: true}

            , {fixed: 'right', title: '操作', toolbar: '#bar1', width: 250}
        ]]
        , page: true
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析据列表数
            };
        }
    });
    //头工具栏事件
    table1.on('toolbar', function (obj) {
        // var checkStatus = table1.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'addData1':
                layer.open({
                    type: 1
                    , area: ['400px', '300px']
                    , content: $('#add-form-div1').html()
                    , anim: 4
                    , yes: function (index, layuio) {
                        layer.msg(index);
                    }
                });
                break;
            case 'addData2':

                layer.open({
                    type: 1
                    , area: ['400px', '200px']
                    , content: $('#add-form-div2').html()
                    , anim: 4
                    , yes: function (index, layuio) {
                        layer.msg(index);
                    }
                });
                break;
        }
        ;
    });

    //监听工具条
    table1.on('tool', function (obj) {
        var data = obj.data;
        // var id=obj.config.id;
        if (obj.event === 'del1') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit1') {
            layer.alert('编辑行：<br>' + JSON.stringify(data))
        }
    });

    //监听工具条
    table2.on('tool', function (obj) {
        // var data = obj.data;
        //删除
        if (obj.event === 'del2') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                $.ajax({
                    type: 'POST',
                    url: '/category/del?id='+obj.data.categoryId,
                    success: function(count){//数据重载
                        if (count>0){
                            table2.reload('datagrid2', {
                                url: '/category/list'
                                , where: {} //设定异步数据接口的额外参数
                            })
                        }else{
                            layer.msg("操作失败");
                        }
                    },
                    dataType: "json"
                });
                layer.closeAll();
            });
        }
        // else if (obj.event === 'edit2') {//编辑
        //
        //     layer.open({
        //         type: 1
        //         , area: ['400px', '200px']
        //         , content: $('#add-form-div2').html()
        //         , anim: 4
        //         ,success: function(layero, index){
        //             // layero.$('#categoryName').val('dfdf');
        //             // layer.msg($('#categoryName').val());
        //             $('#categoryName').val("@{fdsfsdf}");
        //         }
        //     });
        //
        // }
    });

    // 单元格编辑
    table2.on('edit', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.value); //得到修改后的值
        console.log(obj.field); //当前编辑的字段名
        console.log(obj.data); //所在行的所有相关数据
        $.ajax({
            type: 'POST',
            url:'/category/edit',
            data: obj.data,
            success: function(){
                //回调函数
            },
            dataType: 'json'
        });
    });

    //监听表格复选框选择
    table1.on('checkbox', function (obj) {
        console.log(obj)
    });


    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //提交
    function submit($, params) {
        $.post('/category/add', params, function (res) {
            if (res.status == 1) {
                layer.msg(res.message, {icon: 1}, function (index) {
                    layer.closeAll(); //关闭信息框
                })
            } else if (res.status == 2) {
                layer.closeAll(); //关闭信息框
                window.location.href = '/err';
                layer.msg(res.message, function () {

                });
            } else {

                layer.msg(res.message, function () {
                    table.reload('datagrid2', {
                        url: '/page/data'
                        , where: {} //设定异步数据接口的额外参数
                    });
                });
                layer.closeAll(); //关闭信息框

            }

        }, 'json');
    }



});

function showOp1() {
    document.getElementById("option1").style.display = "block";
    document.getElementById("option2").style.display = "none";
    document.getElementById("option3").style.display = "none";
    document.getElementById("option4").style.display = "none";
}

function showOp2() {
    document.getElementById("option2").style.display = "block";
    document.getElementById("option1").style.display = "none";
    document.getElementById("option3").style.display = "none";
    document.getElementById("option4").style.display = "none";
}

function showOp3() {
    document.getElementById("option3").style.display = "block";
    document.getElementById("option1").style.display = "none";
    document.getElementById("option2").style.display = "none";
    document.getElementById("option4").style.display = "none";
}

function showOp4() {
    document.getElementById("option4").style.display = "block";
    document.getElementById("option2").style.display = "none";
    document.getElementById("option3").style.display = "none";
    document.getElementById("option1").style.display = "none";
}

