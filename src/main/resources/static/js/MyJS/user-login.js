/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

// layui.use(['layer'],function(){
//
// });

function keyenter(event) {
    if (event.keyCode == 13) {
        $('#register-btn').click();
    }
}

function sendvaild() {
        layui.use(['layer'], function () {
            $.ajax({
                type: "post",
                datatype: "json",
                async: false,
                url: '/mail/send',
                data:{email:$('#emailsignup').val()};
                success: function (res) {
                    // layer.open({
                    //     type: 1,
                    //     closeBtn: false,
                    //     content: '<div>' +
                    //         '<input type="text" placeholder="验证码" id="valid" name="valid" autocomplete="off" lay-verify="required">' +
                    //         '<input class="layui-btn-normal" autocomplete="off" onclick=""><span>ok</span></input>' +
                    //         '</div>'
                    // });
                }
            });


        });


}

function checkusername() {
    if ($('#usernamesignup').val() != "") {
        $.ajax({
            async:false,
            type: 'post',
            url: '/user/isexist',
            data:{username:$('#usernamesignup').val(),type:"username"}
            ,success:function(res){
                if (res){

                    layui.use(['layer'],function(){

                        layer.tips('用户名已存在', '#usernamesignup', {});
                    });
                    $('#usernamesignup').val("");
                    $('#usernamesignup').focus();
                }
            }
        });
    }
}
