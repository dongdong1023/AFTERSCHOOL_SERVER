<#include "/admin/layout/layout.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<#assign css>

</#assign>
<#assign js>
<script src="/assets/plugins/laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script>

    laydate.render({
        elem: '#birthday' //指定元素
        , type: 'datetime'
    });
    $(".btn-submit").click(function () {
        var formData = new FormData($('#frm')[0]);
        $.ajax({
            type: "POST",
            contentType: false,
            processData: false,
            async: false,
            url: "${ctx!}/admin/user/edit",
            data: formData,
            dataType: "JSON",
            success: function (res) {
                layer.msg(res.message, {
                    time: 1000
                }, function () {
                    window.location.href = document.referrer;
                });
            }
        });
    });
</script>
</#assign>
<@layout title="用户编辑" active="user">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        用户编辑
        <small>编辑用户详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 用户管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 用户编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" id="frm" method="post" action="${ctx!}/admin/user/edit">
                    <div class="box-body">
                        <input type="hidden" id="id" name="id" value="${user.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">账户名：</label>
                            <div class="col-sm-10">
                                <input id="userName" name="userName" class="form-control" type="text" value="${user.userName}" <#if user?exists> readonly="readonly"</#if> >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">昵称：</label>
                        <div class="col-sm-10">
                            <input id="nickName" name="nickName" class="form-control" type="text" value="${user.nickName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"><#if user.avatar != '' && user.avatar != null>
                            <a data-magnify="gallery" href="${ctx!}${user.avatar}">[当前]</a></#if>头像：
                        </label>
                        <div class="col-sm-10">
                            <input type="file" name="poster" id="poster" multiple
                                   class="file-loading" accept="image/*"
                                   value="${user.avatar}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别：</label>
                        <div class="col-sm-10">
                            <select name="sex" class="form-control">
                                <option value="0" <#if user.sex == 0>selected="selected"</#if>>女</option>
                            <option value="1" <#if user.sex == 1>selected="selected"</#if>>男</option>
                        </select>
                    </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">出生日期：</label>
                <div class="col-sm-10">
                    <input id="birthday" name="birthday" class="laydate-icon form-control layer-date" value="${user.birthday}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">电话：</label>
                <div class="col-sm-10">
                    <input id="telephone" name="telephone" class="form-control" value="${user.telephone}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">E-mail：</label>
                <div class="col-sm-10">
                    <input id="email" name="email" class="form-control" value="${user.email}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">地址：</label>
                <div class="col-sm-10">
                    <input id="address" name="address" class="form-control" value="${user.address}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">状态：</label>
                <div class="col-sm-10">
                    <select name="locked" class="form-control">
                        <option value="0" <#if user.locked == 0>selected="selected"</#if>>未锁定</option>
                    <option value="1" <#if user.locked == 1>selected="selected"</#if>>锁定</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">描述：</label>
            <div class="col-sm-10">
                <textarea id="description" name="description" class="form-control" rows="6">${user.description}</textarea>
            </div>
        </div>
    </div>
    <div class="box-footer">
        <button type="button" class="btn btn-default btn-back">返回</button>
        <button type="button" class="btn btn-info pull-right btn-submit">提交</button>
    </div>
    </form>
    </div>
    <!-- /.box -->
    </div>
    </div>
</section>
<!-- /.content -->
</@layout>
