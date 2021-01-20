<#include "/admin/layout/layout.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<#assign css>
	
</#assign>
<#assign js>
<script>
    $(function () {
        //初始化检测表单
        $('#frm').bootstrapValidator();
    });


    $(".btn-submit").click(function () {
        var formData = new FormData($('#frm')[0]);
        //开启验证
        $('#frm').bootstrapValidator('validate');
        var flag = $('#frm').data("bootstrapValidator").isValid();
        if (!flag) {
            return false;
        } else {
            $.ajax({
                type: "POST",
                contentType: false,
                processData: false,
                async: false,
                url: "${ctx!}/team/edit",
                dataType: "JSON",
                data: formData,
                success: function (res) {
                    layer.msg(res.message, {
                        time: 1000
                    }, function () {
                        window.location.href = document.referrer;
                    });
                }
            });
        }
    });
</script>
</#assign>
<@layout title="战队编辑" active="home">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        战队编辑
        <small>编辑战队详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i>战队管理</a></li>
        <li><a href="#"><i class="fa fa-user-o"></i> 战队信息</a></li>
        <li class="active"><i class="fa fa-edit"></i>战队编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" id="frm" method="post"  enctype="multipart/form-data" action="${ctx!}/team/edit">
                    <div class="box-body">
                        <input type="hidden" id="id" name="id" value="${dream.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">战队名：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" value="${dream.name}"  data-bv-notempty="true"
                                       data-bv-notempty-message="不能为空">
                            </div>
                        </div>
                        <div class="form-group" hidden="hidden">
                            <label class="col-sm-3 control-label">战队编码：</label>
                            <div class="col-sm-8">
                                <input id="code" name="code" class="form-control" type="text" readonly="readonly" value="${dream.code}">
                            </div>
                        </div>
                        
                         <div class="form-group">
                            <label class="col-sm-3 control-label"><#if dream.dreamIcon != '' && dream.dreamIcon != null><a data-magnify="gallery" href="${ctx!}/${dream.dreamIcon}">[当前]</a></#if>战队图标:</label>
                            <div class="col-sm-8">
                            <input type="file" name="poster" id="poster" multiple class="file-loading" accept="image/*" value="${dream.dreamIcon}"/>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-3 control-label">类型：</label>
                            <div class="col-sm-8">
                                <select name="type" class="form-control">
                                    <option value="csgo" <#if dream.type == 'csgo'>selected="selected"</#if>>csgo</option>
                                    <option value="pubg" <#if dream.type == 'pubg'>selected="selected"</#if>>pubg</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-3 control-label">获奖记录：</label>
                            <div class="col-sm-8">
                                <textarea id="history" name="history" class="form-control" rows="6">${dream.history}</textarea>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                                <textarea id="description" name="description" class="form-control" rows="6">${dream.description}</textarea>
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