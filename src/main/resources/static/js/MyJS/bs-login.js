/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

layui.use(['layer', 'form'], function () {

    //后台登录--开始
    var form = layui.form;
    form.on('submit(admin-form-submit-btn)', function (data) {
        $.ajax({
            type: 'post',
            datatype: 'json',
            url: '/admin/adlogin',
            data: data.field,
            success: function (res) {
                if (res.code == 0) {
                    layer.msg(res.msg, {anim: 6});
                } else if (res.code == 1) {
                    layer.closeAll();
                    window.parent.window.location.href = '/admin';

                }
            }
        });

    });
    //后台登录--结束


});

// 退出登录确定提示--开始
function logout() {
    alert("dfa");
    layer.confirm("走了吗？",
        {title: '呜呜~~'},
        {btn: ['走了', '不舍得，留下']},
        function (index) {

        }, function (index) {

        })
}
// 退出登录确定提示--结束

// 响应登录时按回车确定的事件--开始
function keytologin(event) {
    var code=event.keyCode;
    // alert(code==13)
    // 如果是按下了回车，结模拟点击按钮的事件
    if (code == 13) {
        $('#admin-form-submit-btn').click();
    }

}
// 响应登录时按回车确定的事件--结束
