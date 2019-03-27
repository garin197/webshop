/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

layui.use(['layer','form'],function(){

    //后台登录--开始
    var form=layui.form;
    form.on('submit(admin-form-submit-btn)',function (data) {
        $.ajax({
            type:'post',
            datatype:'json',
            url:'',
            success:function(res){

            }
        });
        
    });
    //后台登录--结束

});