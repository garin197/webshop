layui.use(['element', 'table', 'layer', 'form', 'laypage', 'upload'], function () {
    var table = layui.table;
    var form = layui.form;
    var upload = layui.upload;
    var imgLimit = 0;
    var imgUploaded = 0;
    var hasCreateProduct = false;
    var proId = -1;
    var myIndex;
    var myFileNametype = "iNdeX_";

    //表格初始化
    table.render({//属性管理表
        elem: '#datagrid2'
        , url: '/category/list'//数据请求url
        , toolbar: '#toolbar2'
        , title: '分类管理'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'categoryId', title: '类型ID', width: 120, fixed: 'left', unresize: true, sort: true}
            , {field: 'categoryName', title: '类型', width: 300, edit: 'text'}
            , {fixed: 'right', title: '操作', toolbar: '#categoryBar', width: 480}
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
        , url: '/product/list'//数据请求url
        , toolbar: '#toolbar1'
        , title: '商品管理'
        , cols: [[//templet之定义数据模板
            {
                field: 'productId', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true
            }
            , {
                fixed: 'image', title: '封面', width: 80, templet: function (data) {//
                    return '<img src="'+data.imgUrl.toString()+'" style="width:50px;height: 40px"/>';
                }
            }
            , {
                field: 'productName', title: '商品名称', width: 250, edit: 'text', templet: function (data) {
                    return data.product.productName.toString();
                }
            }
            , {
                field: 'subTitle', title: '小标题', width: 250, edit: 'text', sort: true, templet: function (data) {
                    return data.product.subTitle.toString();
                }
            }
            , {
                field: 'categoryName', title: '分类', width: 80, sort: true, templet: function (data) {
                    return data.product.category.categoryName.toString();
                }
            }
            , {
                field: 'originalPrice', title: '原价', width: 80, edit: 'text', templet: function (data) {
                    return data.product.originalPrice.toString();
                }
            }
            , {
                field: 'promotePrice', title: '现价', width: 80, edit: 'text', templet: function (data) {
                    return data.product.promotePrice.toString();
                }
            }
            , {
                field: 'stock', title: '库存', width: 80, sort: true, edit: 'text', templet: function (data) {
                    return data.product.stock.toString();
                }
            }
            , {
                field: 'createDate', title: '创建日', width: 172, unresize: true, templet: function (data) {
                    return data.product.createDate.toString();
                }
            }
            , {
                fixed: 'right', title: '操作', toolbar: '#bar1', width: 240, templet: function (data) {
                }
            }
            // , {field: 'logins', title: '登入次数', width: 100, sort: true,}
            // , {
            //     field: 'email', title: '邮箱', width: 150, edit: 'text', templet: function (res) {
            //         return '<em>' + res.email + '</em>'
            //     }
            // }
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
                var clientheight = document.documentElement.clientHeight;//获取浏览器页面高度
                var frameHeight = clientheight + 'px';
                var index = layer.open({
                    type: 2
                    , area: [frameHeight, frameHeight]
                    , content: '/page/iframe-table1-add'
                    , anim: 4
                    , title: '添加商品'
                    , maxmin: true
                    , scrollbar: true
                    , success: function (layero, index) {
                        //载入分类信息到选择框
                        //不知道为什么写在这里不行，所有载入信息写到页面里了
                    }
                    ,end:function(){
                        table.reload('datagrid1');
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
        //商品管理第一层表格
        if (obj.event === 'del1') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit1') {
            layer.alert('编辑行：<br>' + JSON.stringify(data))
        }
        //分类管理第一层表格
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
        } else if (obj.event == 'manage') {
            //属性管理
            layer.open({
                type: 2,
                area: ['1000px', '700px'],
                anim: 4,
                scrollbar: true,
                title: obj.data.categoryName,
                content: '/page/iframe-table2-property-manage?id=' + obj.data.categoryId
            });
        }
    });


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

    //提交图片的表单
    // form.on('submit(saveImg)',function(data){
    //     if (imgLimit==0){
    //         layer.tips('来几张图片','#multiImgBtn',{
    //             anim:6,tips:3
    //         });
    //         return false
    //     }
    //     $.ajax({
    //         type:'post',
    //         url: '/product/add',
    //         data: data.field,
    //         success: function(res){
    //             idOnImg=res.productId;
    //             ImguploadList.data={'id':idOnImg};
    //             layer.load(); //上传loading
    //             ImguploadList.upload();
    //         }
    //     });
    //
    //
    // });

    //提交
    function submit($, params) {
        $.post('/category/add', params, function (res) {
            if (res.status == 1) {
                layer.msg(res.message, {icon: 3});
            } else if (res.status == 2) {
                layer.closeAll(); //关闭信息框
                window.location.href = '/err';
                layer.msg(res.message, {icon: 2});
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

    $('#uploadAction').click(function(){
        if (imgLimit == 0) {
            layer.tips('来几张图片', '#multiImgBtn', {
                anim: 6, tips: 3
            });
            return false
        }
    });

    var ImgListView = $('#multiImgList')//创建列表对象
        , ImguploadList = upload.render({
        elem: '#multiImgBtn'//选择图片
        , url: '/product/addImg'
        , accept: 'file'
        , multiple: true
        , auto: false
        , bindAction: '#uploadAction'//上传触发
        , data: {
            type: function () {
                return $('#tr1').id;
            }
        }
        , choose: function (obj) {
            hasCreateProduct = false;
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function (index, file, result) {
                if ((imgLimit) >= 5) {
                    layer.msg("最多5个图", {icon: 4});
                    $('#multiImgBtn').attr("disabled", "disabled");
                    delete files[index];
                    ImguploadList.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    return;
                }
                imgLimit++;
                if (imgLimit == 1) {
                    myIndex = index;
                    obj.resetFile(index, file, myFileNametype + file.name);
                }
                var tr = $(['<tr id="upload-' + index + '">'
                    , '<td>' + file.name + '</td>'
                    , '<td><img id="td' + imgLimit + '" onmouseover="showImgTips(' + imgLimit + ')" style="width:100px;height: 100px" src="' + result + '"/></td>'
                    , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                    , '<td>等待上传</td>'
                    , '<td>'
                    , '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                    , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                    , '</td>'
                    , '</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function () {
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function () {
                    imgLimit--;
                    $('#multiImgBtn').removeAttr("disabled");
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    ImguploadList.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                ImgListView.append(tr);
            });
        }
        , done: function (res, index, upload) {
            if (res.code == 0) { //上传成功
                var tr = ImgListView.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                imgUploaded++;//已上传加一
                if (imgUploaded >= imgLimit) {
                    imgUploaded = 0;
                    imgLimit = 0;
                }
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        , before: function (obj) {
            if (imgLimit == 0) {
                layer.tips('来几张图片', '#multiImgBtn', {
                    anim: 6, tips: 3
                });
                return false
            }
            //上传之前先提交其他信息并获取新增productId

            if (!hasCreateProduct) {

                $.ajax({
                    type: 'post',
                    url: '/product/add',
                    async: false,//设置是否异步执行，false为非异步
                    data: {
                        "productName": $('#productName').val(),
                        "productSubTitle": $('#productSubTitle').val(),
                        "originalPrice": $('#originalPrice').val(),
                        "promotePrice": $('#promotePrice').val(),
                        "stock": $('#stock').val(),
                        "category": $('#category').val()
                    },
                    success: function (res) {
                        proId = res.productId;
                        hasCreateProduct = true;
                    },
                    error: function (res) {
                        alert('error');
                    }
                });

            }

            this.data = {'id': proId, 'type': myFileNametype};
        }
        , error: function (index, upload) {
            var tr = ImgListView.find('tr#upload-' + index)
                , tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传

            ImguploadList.upload();
        }
        , allDone: function (obj) {
            layer.confirm(
                '要不要再来一发？',//提示信息
                {btn: ['干', '我拒绝']},//按钮的文本
                function () {//响应第一个按钮
                    window.location.reload();
                }
                , function () {//响应第二个按钮
                    layer.closeAll();
                    var index_ = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index_); //再执行关闭
                });
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

function showImgTips(id) {
    /**
     * 只想图用tips放大，响应onmouseover
     */
    var tipsi;
    var src = $('#td' + id).attr("src");
    $("#td" + id).hover(function () {
        tipsi = layer.tips('<div><img style="width:300px;height: 200px" src="' + src + '"/></div>', this
            , {tips: 2, time: 0, area: ['325px', '220px'], anim: -1});
    }, function () {
        layer.close(tipsi);
    });
}

/**
 * 提示5张图片的tips
 */
function showAddImgBtnTips() {
    var tip;
    $('#multiImgBtn').hover(function () {
        tip = layer.tips("第1个默认为封面", this, {tips: [1], icon: 6, anim: -1});
    }, function () {
        layer.close(tip);
    });
}

//普通图片上传
// upload.render({
//     elem: '#singleImgBtn' //绑定前端的上传按钮
//     , url: ''        //上传的地址
//     , auto: false
//     , choose: function (obj) {
//         //预读本地文件示例，不支持ie8
//         obj.preview(function (index, file, result) {
//             $('#singleImg').attr('src', result); //图片链接（base64）
//         });
//         //演示失败状态，并实现重传
//         var singleImgText = $('#singleImgText');
//         singleImgText.html('<span style="color: #FF5722;"></span> <a class="layui-btn layui-btn-xs demo-reload">确认上传</a>');
//         singleImgText.find('.demo-reload').on('click', function () {
//             uploadInst.upload();
//         });
//     }
//     , before: function (obj) {
//         //预读本地文件示例，不支持ie8
//         obj.preview(function (index, file, result) {
//             $('#singleImg').attr('src', result); //图片链接（base64）
//         });
//     }
//     , done: function (res) {
//         //如果上传失败
//         if (res.code > 0) {
//             return layer.msg('上传失败');
//         }
//         //上传成功
//     }
//     , error: function () {
//         //演示失败状态，并实现重传
//         var singleImgText = $('#singleImgText');
//         singleImgText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
//         singleImgText.find('.demo-reload').on('click', function () {
//             uploadInst.upload();
//         });
//     }
// });
// ImgListView.upload
//多文件列表