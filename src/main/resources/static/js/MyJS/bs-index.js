// var sess = $.ajax({
//     type: 'post',
//     datatype: 'json',
//     url: '/admin/islogin',
//     success: function (res) {
//         if (!res) {
//             window.parent.window.location.href = "/admin"
//         }
//         setTimeout(sess, 60);
//     }
//
// });


//简易的登录拦截-后台模块-开始
$.ajax({
    type: 'post',
    ascyn: false,
    url: '/admin/islogin',
    success: function (res) {
        //res是否登录的boolean值
        if (res == "false") {//进行提示登录

            layui.use('layer', function () {
                layer.open({
                    type: 2,
                    content: '/page/bglogin',
                    anim: 3,
                    title: '登录',
                    area: ['500px', '312px'],
                    closeBtn: false
                });

            });
        } else {//登录通过
            layui.use(['element', 'table', 'layer', 'form', 'laypage', 'upload'], function () {
                layer.closeAll();
                var table = layui.table;
                var form = layui.form;
                var upload = layui.upload;

                //分类管理表格初始化--开始
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
                    , page: true//开启分页
                    // , height: 530
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
                    }
                });
                //分类管理表格初始化--结束

                //商品管理表格初始化--开始
                table.render({
                    elem: '#datagrid1'
                    , url: '/product/list'//数据请求url
                    , toolbar: '#toolbar1'
                    , title: '商品管理'
                    , autoSort: false
                    , cols: [[//templet之定义数据模板
                        {
                            field: 'productId', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true
                        }
                        , {
                            fixed: 'image', title: '封面', width: 80, templet: function (data) {//
                                return '<img id="img' + data.imgId + '" onmouseout="closeImgTips()" onmouseover="showImgTipsOnTable1(' + data.imgId + ')" src="' + data.imgUrl + '" style="width:50px;height: 40px"/>';
                            }
                        }
                        , {
                            field: 'productName', title: '商品名称', width: 500, edit: 'text', templet: function (data) {
                                return data.product.productName.toString();
                            }
                        }
                        , {
                            field: 'subTitle', title: '小标题', width: 250, edit: 'text', templet: function (data) {
                                return data.product.subTitle.toString();
                            }
                        }
                        , {
                            field: 'categoryName', title: '分类', width: 180, templet: function (data) {
                                return data.product.category.categoryName.toString();
                            }
                        }
                        , {
                            field: 'originalPrice', title: '原价', width: 100, edit: 'text', templet: function (data) {
                                return data.product.originalPrice;
                            }
                        }
                        , {
                            field: 'promotePrice', title: '现价', width: 100, edit: 'text', templet: function (data) {
                                return data.product.promotePrice;
                            }
                        }
                        , {
                            field: 'stock', title: '库存', width: 80, edit: 'text', sort: true, templet: function (data) {
                                return data.product.stock;
                            }
                        }
                        , {
                            field: 'createDate', title: '创建日', width: 191, unresize: true, templet: function (data) {
                                return data.product.createDate.toString();
                            }
                        }
                        , {
                            fixed: 'right', title: '操作', toolbar: '#bar1', width: 500, templet: function (data) {
                            }
                        }
                        // , {field: 'logins', title: '登入次数', width: 100, sort: true,}
                        // , {
                        //     field: 'email', title: '邮箱', width: 150, edit: 'text', templet: function (res) {
                        //         return '<em>' + res.email + '</em>'
                        //     }
                        // }
                    ]]
                    , page: true //开启分页
                    , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                        return {
                            "code": res.code, //解析接口状态
                            "msg": res.message, //解析提示文本
                            "count": res.count, //解析数据长度
                            "data": res.data //解析据列表数
                        };
                    }
                });
                //商品管理表格初始化--结束

                // 订单管理表格初始化化--start
                table.render({//属性管理表
                    elem: '#datagrid4'
                    , url: '/product/orderList/all'//数据请求url
                    , toolbar: '#toolbar4'
                    , title: '管理'
                    , cols: [[
                        {fixed: 'left', title: '操作', toolbar: '#orderBar', unresize: true, width: 120},
                        {
                            field: 'orderCode', title: '订单id', width: 120, sort: true,
                            templet: function (data) {
                                return data.orderCode;
                            }
                        }
                        , {field: 'status', title: '付款状态', width: 120}
                        , {field: 'payDate', title: '付款日期', width: 120}
                        , {field: 'confirmDate', title: '收货日期', width: 120}
                        , {field: 'deliver', title: '货物状态', width: 120}
                        , {field: 'deliveryDate', title: '发货日期', width: 120}
                        , {field: 'productId', title: '商品id', width: 120}
                        , {field: 'productName', title: '商品', width: 120}
                        , {field: 'number', title: '购买数量', width: 120}
                        , {field: 'userName', title: '收货人', width: 120}
                        , {field: 'address', title: '收货地址', width: 120}
                        , {field: 'phone', title: '电话', width: 120}
                        , {field: 'email', title: '邮箱', width: 120}
                        , {field: 'userMessage', title: '备注信息', width: 120}
                        // , {field: 'deliver', title: '发货', width: 120}
                        // , {field: 'deliver', title: '发货', width: 120}
                        , {field: 'ordercreateDate', title: '下单日期', width: 120}

                    ]]
                    , page: true//开启分页
                    // , height: 530
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
                    }
                });
                // 订单管理表格初始化化--end

                //用户管理后台表格初始化--start
                table.render({//属性管理表
                    elem: '#datagrid3'
                    , url: '/admin/list/all'//数据请求url
                    // , toolbar: '#toolbar4'
                    , title: '管理'
                    , cols: [[
                        // {fixed: 'left', title: '操作', toolbar: '#orderBar', unresize: true, width: 120},
                        // {
                        //     field: 'orderCode', title: '订单id', width: 120, sort: true,
                        //     templet: function (data) {
                        //         return data.orderCode;
                        //     }
                        // },
                        {field: 'userId', title: '用户Id', width: 120}
                        , {field: 'userName', title: '用户名', width: 120}
                        , {
                            field: 'password', title: '密码', width: 120, templet: function (data) {
                                return "******";
                            }
                        }
                        , {field: 'email', title: '邮箱', width: 420}

                    ]]
                    , page: true//开启分页
                    // , height: 530
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
                    }
                });
                //用户管理后台表格初始化--start

                //头工具栏事件监听与响应--开始
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
                                , end: function () {
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
                                , offset: 'lt'//默认打开在左上角
                                , yes: function (index, layuio) {
                                }
                            });
                            break;
                        case 'reloadOnTable2'://table2重载事件
                            table.reload('datagrid2', {
                                page: 1,
                                url: '/category/list'
                                // , height: 580
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
                                    // height: 580,
                                    success: function (res) {
                                        $('#SearchCategory').val("")
                                    }
                                })
                            }
                            break;
                        case 'SearchProduct'://搜索商品
                            if ($('#SearchProduct').val() != '') {
                                table.reload('datagrid1', {
                                    url: '/product/search',
                                    where: {"statement": $('#SearchProduct').val()},
                                    page: 1,
                                    success: function (res) {
                                        $('#SearchProduct').val("");
                                    }
                                });
                            } else {
                                layer.msg("不为空", {icon: 2})
                                return;
                            }
                            break;
                        case 'reloadOnTable1':
                            table.reload('datagrid1', {
                                url: '/product/list'
                            });
                            break;
                        case 'all':
                            table.reload(
                                'datagrid4',
                                {
                                    url: '/product/orderList/all'
                                }
                            );
                            break;
                        case 'undeliverList':
                            table.reload(
                                'datagrid4',
                                {
                                    url: '/product/orderList/undeliver'
                                }
                            );
                            break;
                        case 'deliverList':
                            table.reload(
                                'datagrid4',
                                {
                                    url: '/product/orderList/deliver'
                                }
                            );
                            break;
                        case 'doneList':
                            table.reload(
                                'datagrid4',
                                {
                                    url: '/product/orderList/done'
                                }
                            );
                            break;
                        case 'unpayList':
                            table.reload(
                                'datagrid4',
                                {
                                    url: '/product/orderList/unpay'
                                }
                            );
                            break;
                        case 'paidList':
                            table.reload(
                                'datagrid4',
                                {
                                    url: '/product/orderList/paid'
                                }
                            );
                            break;
                        case 'paidundeliverList':
                            table.reload(
                                'datagrid4',
                                {
                                    url: '/product/orderList/paidundeliver'
                                }
                            );
                            break;
                        case 'delivernotdoneList':
                            table.reload(
                                'datagrid4',
                                {
                                    url: '/product/orderList/delivernotdone'
                                }
                            );
                            break;
                    }
                });
                //头工具栏事件监听与响应--结束

                //监听 响应排序--开始
                table.on('sort(datagrid1)', function (obj) {
                    console.log(obj.field); //当前排序的字段名
                    console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
                    console.log(this); //当前排序的 th 对象

                    //尽管我们的 table 自带排序功能，但并没有请求服务端。
                    //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
                    table.reload('datagrid1', {
                        url: '/product/stocksort',
                        initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
                        , where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                            field: obj.field //排序字段
                            , order: obj.type //排序方式
                        }
                    });
                });
                //监听 响应排序--开始


                //监听 表格内工具条 -- 开始
                table.on('tool', function (obj) {
                    var data = obj.data;
                    // var id=obj.config.id;
                    //商品管理第一层表格
                    if (obj.event === 'del1') {
                        layer.confirm('真的么?',
                            {btn: ['果断下架', '容我三思']},
                            function (index) {
                                obj.del();
                                //删除商品
                                $.ajax({
                                    type: "post",
                                    url: '/product/del',
                                    data: data,
                                    success: function () {

                                    }
                                });
                                layer.close(index);
                            },
                            function () {

                            });
                    } else if (obj.event === 'edit-properties') {
                        layer.open({
                            type: 2,
                            scrollBar: true,
                            title: "属性管理:" + data.product.productName,
                            area: ['1000px', '700px'],
                            content: '/page/index-iframe-table1-property-manage?pid=' + data.productId + '&cid=' + data.product.category.categoryId,
                            success: function () {

                            }
                        });

                        // layer.alert('编辑行：<br>' + JSON.stringify(data))

                    } else if (obj.event == 'Imgdetail1') {//查看图片信息
                        layer.open({
                            type: 2,
                            content: '/page/imgDetail?productId=' + obj.data.productId,
                            area: ['1000px', '700px'],
                            title: '商品图片信息',
                            scrollBar: true,
                            success: function () {

                            }
                        });
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

                    //订单管理表格内工具栏响应--开始
                    if (obj.event == 'deliver-bar-btn') {
                        //发货--start
                        if (obj.data.status == "已付款" && obj.data.deliver == "未发货") {

                            //正常发货
                            $.post(
                                '/product/deliver/' + obj.data.orderId,
                                function (res) {
                                    if (res == "success") {
                                        layer.msg("已修改货物状态");
                                        table.reload('datagrid4');
                                        //通知用户
                                        $.post(
                                            '/mail/deliver/send',
                                            {"email":obj.data.email,"productName":obj.data.productName},
                                            function (res) {
                                                if (res=="success"){
                                                    layer.msg("发货通知已经发送到用户邮箱");
                                                } else{
                                                    layer.msg("发货通知失败，请手动通知");
                                                }
                                            }
                                        );
                                    }
                                }
                            );
                        } else if (obj.data.status == "已付款" && obj.data.deliver == "已发货") {
                            //防止重复发货
                            layer.msg("发过了，兄dei", {anim: 6});
                        } else {
                            //防止非法发货
                            layer.msg("你是认真的吗？", {anim: 6});
                        }
                        //发货--end


                    } else if (obj.event == 'del-bar-btn') {

                    }
                    //订单管理表格内工具栏响应--开始

                });
                //监听 表格内工具条 -- 结束


                //单元格编辑****商品管理表格
                table.on('edit(datagrid1)', function (obj) {
                    $.ajax({
                        type: 'POST',
                        url: '/product/edit',
                        data: obj.data,
                        success: function (flag) {
                            //回调函数
                            if (flag > 0) {
                                layer.msg("修改成功|success ");
                            } else {
                                layer.msg("修改失败|failed ");
                            }
                            table.reload('datagrid1');
                        },
                        dataType: 'json'
                    });
                });

                // 单元格编辑*****属性管理表格
                table.on('edit(datagrid2)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
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

                // 添加商品监听（二次监听，与图片上传绑定同一个按钮）--开始
                $('#uploadAction').click(function () {
                    if (imgLimit == 0) {
                        layer.tips('来几张图片', '#multiImgBtn', {
                            anim: 6, tips: 3
                        });
                        return false
                    }
                });
                // 添加商品监听--end

                // 图片上传前端模块--开始

                //选中的图片数
                var imgLimit = 0;

                //已经被上传的文件数
                var imgUploaded = 0;

                //是否已经将商品表单的非图片部分上传
                var hasCreateProduct = false;

                //封面图片的标记
                var myFileNametype = "iNdeX_";

                //记录商品表单非图片部分上传成功之返回的productId
                var proId = -1;

                //创建列表对象，动态插入列
                var ImgListView = $('#multiImgList');

                // 上传失败标记
                var failflag = false;

                //多问价上传的对象
                var ImguploadList = upload.render({

                    //选择图片的按钮id
                    elem: '#multiImgBtn'

                    //图片长传的地址url
                    , url: '/product/addImg'

                    //接受类型
                    , accept: 'file'

                    //支持多文件长传，但是会触发多次单个文件上传
                    , multiple: true

                    //选中不自动上传
                    , auto: false

                    //上传触发，绑定一个按钮
                    , bindAction: '#uploadAction'

                    , data: {
                        type: function () {
                            return $('#tr1').id;
                        }
                    }

                    // 选择了文件后会触发
                    , choose: function (obj) {
                        hasCreateProduct = false;
                        var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                        //读取本地文件
                        obj.preview(function (index, file, result) {
                            if ((imgLimit) >= 5) {
                                layer.msg("最多5个图", {icon: 4});
                                $('#multiImgBtn').attr("disabled", "disabled");
                                delete files[index];
                                //清空 input file 值，以免删除后出现同名文件不可选
                                ImguploadList.config.elem.next()[0].value = '';
                                return;
                            }
                            // 选择的图片加一
                            imgLimit++;

                            // 如果是第一张，重新设置文件名，带上封面的标记
                            if (imgLimit == 1) {
                                obj.resetFile(index, file, myFileNametype + file.name);
                            }

                            // 动态拼接表格的一行，.join('')很重要
                            var tr = $(['<tr id="upload-' + index + '">'
                                , '<td>' + file.name + '</td>'
                                , '<td><img id="td' + imgLimit + '" onmouseout="closeImgTips()" onmouseover="showImgTips(' + imgLimit + ')" style="width:100px;height: 100px" src="' + result + '"/></td>'
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

                            //删除--开始
                            tr.find('.demo-delete').on('click', function () {

                                // 删除了选中的展示图片后选中的总图片减一
                                imgLimit--;
                                $('#multiImgBtn').removeAttr("disabled");

                                //删除对应的文件
                                delete files[index];

                                // 删除对应的展示行
                                tr.remove();

                                //清空 input file 值，以免删除后出现同名文件不可选
                                ImguploadList.config.elem.next()[0].value = '';
                            });
                            //删除--结束

                            // 想表格中追加一行
                            ImgListView.append(tr);
                        });
                    }

                    // 上传成功后触发，上传多文件会触发多次
                    , done: function (res, index, upload) {
                        // alert("done")
                        failflag = false;
                        if (res.code == 0) { //上传成功
                            var tr = ImgListView.find('tr#upload-' + index)
                                , tds = tr.children();
                            tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                            tds.eq(3).html(''); //清空操作

                            //已上传加一
                            imgUploaded++;
                            // 判断是否已经全部上传完毕
                            if (imgUploaded >= imgLimit) {

                                // 上传完毕，已经上传数，选中的图片数清零初始化
                                imgUploaded = 0;
                                imgLimit = 0;
                            }

                            //删除文件队列已经上传成功的文件
                            return delete this.files[index];
                        }
                        // this.error(index, upload);
                    }
                    // 上传之前调用
                    , before: function (obj) {

                        // 没有选择图片，提示用户选择图片
                        if (imgLimit == 0) {
                            layer.tips('来几张图片', '#multiImgBtn', {
                                anim: 6, tips: 3
                            });
                            return true;
                        }

                        if ($('#productName').val() == "") {
                            $('#productName').focus();
                            return;
                        }
                        if ($('#productSubTitle').val() == "") {
                            $('#productSubTitle').val("");
                        }
                        if ($('#originalPrice').val() == "") {
                            $('#originalPrice').val("0");
                        }
                        if ($('#promotePrice').val() == "") {
                            $('#promotePrice').val("0");
                            $('#promotePrice').focus();
                            return;
                        }
                        if ($('#stock').val() == "") {
                            $('#stock').val(0);
                        }
                        if ($('#category').val() == "") {
                            $('#category').focus();
                            return;
                        }
                        //上传之前先提交其他信息并获取新增productId
                        if (!hasCreateProduct) {

                            // 必须先获取到商品的id才能上传图片
                            $.ajax({
                                type: 'post',
                                url: '/product/add',

                                //设置是否异步执行，false为非异步
                                async: false,
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
                        failflag = true;
                        // ImguploadList.upload();
                    }

                    // 全部文件上传完成之后调用
                    , allDone: function (obj) {
// alert("allDone")
                        if (!failflag) {
                            // 提示用户是否继续上传
                            layer.confirm(
                                //提示信息
                                '要不要再来一发？',

                                //按钮的文本
                                {btn: ['干', '我拒绝']},

                                //响应第一个按钮
                                function () {
                                    window.location.reload();
                                }

                                //响应第二个按钮
                                , function () {

                                    // 关闭当前页面对象上的所有弹出层
                                    layer.closeAll();

                                    //先得到当前iframe层的索引，通过iframe的parent获取当前的layui-iframe的index值
                                    var index_ = parent.layer.getFrameIndex(window.name);

                                    //再执行关闭
                                    parent.layer.close(index_);
                                });
                        }
                    }
                });


                // 图片上传前端模块--结束

            });
        }
    }

});

//导航的展示与隐藏--开始
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

//导航的展示与隐藏--结束

/**
 * 图用tips放大，响应onmouseover
 */
function showImgTips(id) {
    var tipsi;
    var src = $('#td' + id).attr("src");
    tipsi = layer.tips('<div><img style="width:300px;height: 200px" src="' + src + '"/></div>', '#td' + id
        , {tips: 2, time: 0, area: ['0px', '0px'], anim: -1});
}

/**
 * 图用tips放大，响应onmouseover
 */
function showImgTipsOnTable1(id) {
    var tips2;
    var src = $('#img' + id).attr("src");
    tips2 = layer.tips('<div><img style="width:500px;height: 403px" src="' + src + '"/></div>', '#img' + id
        , {tips: 2, time: 0, area: ['0px', '0px'], anim: -1});
}

/**
 * 关闭弹出层
 */
function closeImgTips() {
    layer.closeAll();
}

/**
 * 提示5张图片的tips
 */
function showAddImgBtnTips() {
    layer.tips("第1个默认为封面", '#multiImgBtn', {tips: [1], icon: 6, anim: -1});
}


/**
 * 隐藏所有表格
 */
function hideAll() {
    document.getElementById("option4").style.display = "none";
    document.getElementById("option2").style.display = "none";
    document.getElementById("option3").style.display = "none";
    document.getElementById("option1").style.display = "none";
}

/**
 * 管理员退出登录
 */
function adminlogout() {
    layer.confirm('yes?', {
        offset: 'rt'
        , icon: 3
        , title: '提示'
        , btn: ['走了', '再想想']
    }, function (index) {
        $.ajax({
            type: 'post'
            , datatype: 'json'
            , url: '/admin/logout'
            , success: function (res) {
                layer.close(index);
                window.location.reload();
            }
        });
    }, function (index) {

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