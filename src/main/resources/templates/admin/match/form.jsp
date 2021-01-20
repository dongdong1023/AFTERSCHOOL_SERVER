<#include "/admin/layout/layout.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<#assign css>

</#assign>
<#assign js>
<script src="/assets/plugins/laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script>

    $(function () {
        //初始化检测表单
        $('#frm').bootstrapValidator();
    });


    /*选择战队Id*/
    var checkId = '';

    laydate.render({
        elem: '#matchTime', //指定元素
        type: 'datetime'
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
                url: "${ctx!}/admin/manage/edit",
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
    
    
    function checkDream(id){
    	checkId = id;
    	layer.open({
    		  type: 2,
    		  title: '选择战队',
    		  shadeClose: true,
    		  shade: 0.8,
    		  area: ['550px', '90%'],
    		  content: '/team/info'
    	}); 
    }
    
    function GetValue(obj){
        $("#"+checkId).val(obj);
        $('#frm').data("bootstrapValidator").updateStatus(checkId, 'has-success').validateField(checkId);
    }
    
</script>
</#assign>
<@layout title="比赛编辑" active="match">
<!-- Content Header (Page header) -->
<section class="content-header">
     <h1>
        比赛编辑
        <small>编辑比赛详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-random"></i>推送管理</a></li>
        <li class="active"><i class="fa fa-video-camera"></i>比赛信息</li>
        <li class="active"><i class="fa fa-edit"></i>比赛编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" id="frm" method="post"  enctype="multipart/form-data" action="${ctx!}/admin/manage/edit">
                    <div class="box-body">
                        <input type="hidden" id="id" name="id" value="${match.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">比赛名称：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" value="${match.name}"  data-bv-notempty="true"
                                       data-bv-notempty-message="不能为空">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">比赛编码：</label>
                            <div class="col-sm-8">
                                <input id="code" name="code" class="form-control" type="text" <#if match.code == '' || match.code == null>readonly="readonly"</#if> value="${match.code}">
                            </div>
                        </div>
                        
                        
                        <div class="form-group">
                            <label class="col-sm-3 control-label">类型：</label>
                            <div class="col-sm-8">
                                <select name="type" class="form-control">
                                    <option value="csgo" <#if match.type == 'csgo'>selected="selected"</#if>>csgo</option>
                                    <option value="pubg" <#if match.type == 'pubg'>selected="selected"</#if>>pubg</option>
                                </select>
                            </div>
                        </div>

						<div class="form-group">
							<label class="col-sm-3 control-label">比赛方A：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<input id="dreamCodeA" name="dreamCodeA" type="text"
										class="form-control" value="${match.dreamCodeA}"
										readonly="readonly"  data-bv-notempty="true"
                                           data-bv-notempty-message="不能为空"> <span type="file"
										class="btn btn-primary input-group-addon"
										onclick="checkDream('dreamCodeA')">选择</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label">比赛方B：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<input id="dreamCodeB" name="dreamCodeB" type="text"
										class="form-control" value="${match.dreamCodeB}"
										readonly="readonly"  data-bv-notempty="true"
                                           data-bv-notempty-message="不能为空"> <span type="file"
										class="btn btn-primary input-group-addon"
										onclick="checkDream('dreamCodeB')">选择</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
                            <label class="col-sm-3 control-label">比赛日期：</label>
                            <div class="col-sm-8">
                                <input id="matchTime" name="matchTime" class="laydate-icon form-control layer-date" value="${match.matchTime}">
                            </div>
                        </div>


						<div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                                <textarea id="description" name="description" class="form-control" rows="6">${match.description}</textarea>
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