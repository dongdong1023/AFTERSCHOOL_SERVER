<#include "/admin/layout/layout.jsp"> <#import "/admin/layout/macro.jsp"
as macro> <#assign css>
<link rel="stylesheet" href="/assets/plugins/guojia/css/select_gj.css"/>
</#assign> <#assign js>
<script src="/assets/plugins/guojia/js/select_gj.min.js"></script>
<script src="/assets/plugins/guojia/js/select2_1.js"></script>
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
				processData : false,
				async : false,
				url : "${ctx!}/member/edit",
				dataType: "JSON",
				data: formData,
				success: function (res) {
					if (res.code == 0) {
						layer.msg(res.message, {
							time: 1000
						}, function () {
							window.location.href = document.referrer;
						});
					} else {
						layer.msg(res.message, {
							time: 1000
						});
					}

				}
			});
		}
	});

	function checkDream() {
		layer.open({
			type: 2,
			title: '选择战队',
			shadeClose: true,
			shade: 0.8,
			area: ['550px', '90%'],
			content: '/team/info'
		});
	}

	function GetValue(obj) {
		$("#dreamCode").val(obj);
		$('#frm').data("bootstrapValidator").updateStatus('dreamCode', 'has-success').validateField('dreamCode');
	}
</script>
</#assign> <@layout title="队员编辑" active="member">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		队员编辑 <small>编辑队员详细信息</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-home"></i>战队管理</a></li>
		<li><a href="#"><i class="fa fa-users"></i> 队员信息</a></li>
		<li class="active"><i class="fa fa-edit"></i>队员编辑</li>
	</ol>
</section>
<!-- Main content -->
<section class="content" onload="text()">
	<div class="row">
		<div class="col-md-10">
			<!-- Default box -->
			<div class="box  box-primary">
				<form class="form-horizontal form-edit" id="frm" method="post"
					enctype="multipart/form-data" action="${ctx!}/member/edit">
					<div class="box-body">
						<input type="hidden" id="id" name="id" value="${member.id}">
						<div class="form-group">
							<label class="col-sm-3 control-label">姓名：</label>
							<div class="col-sm-8">
								<input id="name" name="name" class="form-control" type="text"
									value="${member.name}" data-bv-notempty="true"
									data-bv-notempty-message="不能为空">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">所属战队：</label>
							<div class="col-sm-8">
								<div class="input-group">
									<!-- data-bv-notempty="true" data-bv-notempty-message="不能为空"  -->
									<input id="dreamCode" name="dreamCode" type="text"
										class="form-control" value="${member.dreamCode}"
										readonly="readonly" data-bv-notempty="true"
										data-bv-notempty-message="不能为空"> <span type="file"
																			   class="btn btn-primary input-group-addon"
																			   onclick="checkDream()">选择</span>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">游戏名:</label>
							<div class="col-sm-8">
								<input id="gameName" name="gameName" class="form-control"
									   type="text" value="${member.gameName}" data-bv-notempty="true"
									   data-bv-notempty-message="不能为空">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Steam:</label>
							<div class="col-sm-8">
								<input id="steamId" name="steamId" class="form-control"
									   type="text" value="${member.steamId}" data-bv-notempty="true"
									   data-bv-notempty-message="不能为空">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">Steam(32位):</label>
							<div class="col-sm-8">
								<input id="steamId32" name="steamId32" class="form-control"
									   type="text" value="${member.steamId32}" data-bv-notempty="true"
									   data-bv-notempty-message="不能为空">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label"><#if
								member.imageFile != '' && member.imageFile != null><a
										data-magnify="gallery" href="${ctx!}/${member.imageFile}">[当前]</a></#if>队员头像:
							</label>
							<div class="col-sm-8">
								<input type="file" name="poster" id="poster" multiple
									class="file-loading" accept="image/*"
									value="${member.imageFile}" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">国家：</label>
							<div class="col-sm-8">
								<select name="country" id="country"
									class="fastbannerform__country">
									<input id="countryData" class="form-control" type="hidden"
									value="${member.country}">
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">获奖记录：</label>
							<div class="col-sm-8">
								<textarea id="history" name="history" class="form-control"
									rows="6">${member.history}</textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">描述：</label>
							<div class="col-sm-8">
								<textarea id="description" name="description"
									class="form-control" rows="6">${member.description}</textarea>
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
