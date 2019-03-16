layui.use(['element', 'table', 'layer', 'form', 'laypage','upload'], function () {
    var table = layui.table;
    // var table2 = layui.table;
    var form = layui.form;
    var upload=layui.upload;

    //表格初始化
    table.render({
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
        , height: 530
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

    table.render({
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
    table.on('toolbar', function (obj) {
        switch (obj.event) {//匹配lay-event
            case 'addData1':
                var clientheight=document.documentElement.clientHeight;//获取浏览器页面高度
                var frameHeight=clientheight+'px';
                var index=layer.open({
                    type: 2
                    , area: [frameHeight,frameHeight]
                    , content: '/page/iframe-table1-add'
                    , anim: 4
                    ,title: '添加商品'
                    ,maxmin: true
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
                    , title: '添加分类'
                    , yes: function (index, layuio) {
                    }
                });
                break;
            case 'reloadOnTable2'://table2重载事件
                table.reload('datagrid2', {
                    page: 1,
                    url: '/category/list'
                    , height: 580
                });
                break;
            case 'searchCategory'://table2搜索
                // var type = $(this).data('type');
                if ($('#SearchCategory').val() == '') {
                    layer.msg("不为空", {icon: 2})
                    return;
                } else {
                    table.reload('datagrid2', {
                        page: 1,
                        url: '/category/search',
                        where: {
                            statement: $('#SearchCategory').val()
                        },
                        height: 580,
                        success: function (res) {
                            $('#SearchCategory').val("")
                        }
                    })
                    break;
                }
        }
    });

    //监听 表格内工具条
    table.on('tool', function (obj) {
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
        if (obj.event === 'del2') {
            layer.confirm('真的删除行么', function (index) {//删除
                obj.del();
                $.ajax({
                    type: 'POST',
                    url: '/category/del?id=' + obj.data.categoryId,
                    success: function (count) {//数据重载
                        if (count > 0) {
                            table.reload('datagrid2', {
                                url: '/category/list'
                                , where: {} //设定异步数据接口的额外参数
                                , height: 580
                            })
                        } else {
                            layer.msg("操作失败");
                        }
                    },
                    dataType: "json"
                });
                layer.closeAll();
            });
        }
    });

    // //监听 表格内工具条
    // table.on('tool', function (obj) {
    //
    // });

    // 单元格编辑
    table.on('edit', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.value); //得到修改后的值
        console.log(obj.field); //当前编辑的字段名
        console.log(obj.data); //所在行的所有相关数据
        $.ajax({
            type: 'POST',
            url: '/category/edit',
            data: obj.data,
            success: function (flag) {
                //回调函数
                if (flag > 0) {
                    layer.msg("修改成功|success ")
                }
            },
            dataType: 'json'
        });
    });

    //监听表格复选框选择
    table.on('checkbox', function (obj) {
        console.log(obj)
    });

    //监听table2提交
    form.on('submit(saveCategory)', function (data) {
        params = data.field;
        submit($, params);
        return false;
    });
    //提交
    function submit($, params) {
        $.post('/category/add', params, function (res) {
            if (res.status == 1) {
                layer.msg(res.message, {icon: 3});
            } else if (res.status == 2) {
                layer.closeAll(); //关闭信息框
                window.location.href = '/err';
                layer.msg(res.message,{icon:2});
            } else {

                layer.msg(res.message, function () {
                    table.reload('datagrid2', {
                        url: '/category/list'
                        , where: {} //设定异步数据接口的额外参数
                        , height: 580
                    });
                });
                layer.closeAll(); //关闭信息框

            }

        }, 'json');
        $('#categoryName').val('');
    }

    //普通图片上传
    upload.render({
        elem: '#singleImgBtn' //绑定前端的上传按钮
        ,url: ''        //上传的地址
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#singleImg').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var singleImgText = $('#singleImgText');
            singleImgText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            singleImgText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    //多图片上传
    upload.render({
        elem: '#multiImgBtn'
        ,url: '/page'
        ,multiple: true
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#mutiImg').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
            });
        }
        ,done: function(res){
            //上传完毕
        }
    });

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

