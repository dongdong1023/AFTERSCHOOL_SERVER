<#include "/admin/layout/layout.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<@layout title="队员信息" active="member">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
       	 队员信息
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i>战队管理</a></li>
        <li class="active"><i class="fa fa-users"></i>队员信息</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
		<div class="box-header">
			<div class="col-sm-6">
                <@shiro.hasPermission name="system:member:add">
				<a class="btn btn-sm btn-success" href="${ctx!}/member/add">新增</a>
                </@shiro.hasPermission>
			</div>
			<div class="col-sm-6">
				<div class="col-sm-8">
					<input id="parame" name="parame" class="form-control" type="text" value="${parame}" placeholder="请输入队员编码/姓名/游戏名/Steam/Steam(32位)/描述...">
				</div>
				<a class="btn btn-primary btn-info" onclick="query()">查询</a>
			</div>
		</div>
		<div class="box-body">
              <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>队员编码</th>
                    <th>姓名</th>
                    <th>所属战队</th>
                    <th>游戏名</th>
                    <th>队员头像</th>
                    <th>描述</th>
                    <th>Steam</th>
                    <th>Steam(32位)</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                <#list members as memberInfo>
                <tr>
                    <td>${memberInfo.id}</td>
                    <td>${memberInfo.code}</td>
                    <td>${memberInfo.name}</td>
                    <td>${memberInfo.dreamName}</td>
                    <td>${memberInfo.gameName}</td>
                    <td><a class="showImg" data-magnify="gallery" href="${ctx!}/${memberInfo.imageFile}">查看</td>
                    <td>${memberInfo.description}</td>
                    <td>${memberInfo.steamId}</td>
                    <td>${memberInfo.steamId32}</td>
                    <td>${memberInfo.createTime}</td>
                    <td>${memberInfo.updateTime}</td>
                    <td>
					<@shiro.hasPermission name="system:member:edit">
					<a class="btn btn-sm btn-primary" href="${ctx!}/member/edit/${memberInfo.id}">编辑</a>
                    	 <#if memberInfo.memberStatus == 1>
                        	<button class="btn btn-sm btn-warning" onclick="status(this,${memberInfo.id},0)">替补</button>
                         <#else>
                           <button class="btn btn-sm btn-warning" onclick="status(this,${memberInfo.id},1)">上线</button>
						</#if>
					</@shiro.hasPermission>
                    <@shiro.hasPermission name="system:member:del">
                        <button class="btn btn-sm btn-danger" onclick="del(${memberInfo.id})">删除</button>
					</@shiro.hasPermission>
					</td>
                </tr>
				</#list>
            </table>
        </div>
        <!-- /.box-body -->
         <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/member/index?sort=updateTime,desc" />
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
<script>
 function del(id){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx!}/member/delete/" + id,
                success: function(res){
                    layer.msg(res.message, {time: 1000}, function () {
                        location.reload();
                    });
                }
            });
        });
    }

 
 
 function status(obj,id,status){
	 var text = obj.innerText;
     layer.confirm('确定'+text+'吗?' , {icon: 3, title:'提示'}, function(index){
           $.ajax({
             type: "POST",
             dataType: "json",
             url: "${ctx!}/member/status/" + id+"?status="+status,
             success: function(res){
                 layer.msg(res.message, {time: 2000}, function () {
                     location.reload();
                 });
             }
         });
     });
 }
</script>
</@layout>