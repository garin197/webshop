function keyenter(event) {
    if (event.keyCode == 13) {
        $('#register-btn').click();
    }
}

function sendvaild() {


    if ($('#emailsignup').val() == "") {

        $('#emailsignup').focus();
        layui.use(['layer'], function () {
            layer.tips('邮箱没写哦', "#emailsignup");
        });
        return false;
    }

    layui.use(['layer'], function () {
        $.ajax({
            type: "post",
            datatype: "json",
            async: true,
            url: '/mail/send',
            data: {"email": $('#emailsignup').val()},
            success: function (res) {
                // layer.open({
                //     type: 1,
                //     closeBtn: false,
                //     content: '<div>' +
                //         '<input type="text" placeholder="验证码" id="valid" name="valid" autocomplete="off" lay-verify="required">' +
                //         '<input class="layui-btn-normal" autocomplete="off" onclick=""><span>ok</span></input>' +
                //         '</div>'
                // });

                if (res) {

                    layer.tips(
                        "已经发送验证码，请查看！", "#vaild", {}
                    );
                }
            }
        });


    });

    timeout = 30
    window.setTimeout("update_getValid(" + timeout + ")", 1000);

}

var timeout = 30;

function update_getValid(t) {
    var btn_getvaild = document.getElementById("get-vaild-btn");
    if (t >= 0) {
        btn_getvaild.value = "（" + t + "s后）重新获取";
        btn_getvaild.disabled = true;
        timeout--;
        window.setTimeout("update_getValid(" + timeout + ")", 1000);
    } else {
        btn_getvaild.value = '重新获取';
        btn_getvaild.disabled = false;
        window.clearTimeout();
    }


}


function checkusername() {
    if ($('#usernamesignup').val() != "") {
        $.ajax({
            async: false,
            type: 'post',
            url: '/user/isexist',
            data: {"userName": $('#usernamesignup').val(), "type": "username"}
            , success: function (res) {
                if (res) {

                    layui.use(['layer'], function () {

                        layer.tips('用户名已存在', '#usernamesignup', {});

                    });

                    $('#usernamesignup').focus();
                } else {

                    layui.use(['layer'], function () {

                        layer.tips('该名可以使用', '#usernamesignup');

                    });
                }
            }
        });
    }
}

function submit_register() {

    if ($('#usernamesignup').val() == "") {
        $('#usernamesignup').focus();
        return;
    }
    if ($('#emailsignup').val() == "") {
        $('#emailsignup').focus();
        return;

    }
    if ($('#passwordsignup').val() == "") {
        $('#passwordsignup').focus();
        return;

    }
    if ($('#passwordsignup_confirm').val() == "") {
        $('#passwordsignup_confirm').focus();
        return;

    }
    if ($('#vaild').val() == "") {
        $('#vaild').focus();
        return;

    }



    $.post(
        '/user/register',
        {
            "userName": $('#usernamesignup').val(),
            "email": $('#emailsignup').val(),
            "password": $('#passwordsignup').val(),
            "vaild": $('#vaild').val()
        },
        function (res) {
            if (res.flag == true) {
                layer.msg(res.msg);
                window.location.href = "/user/login";
            } else {
                layer.msg(res.msg);
            }
        }
    );
}
