<#include "/admin/layout/layout.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<@layout title="战队信息" active="home">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
       	 战队信息
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i>战队管理</a></li>
        <li class="active"><i class="fa fa-user-o"></i>战队信息</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
		<div class="box-header">
			<div class="col-sm-6">
				<@shiro.hasPermission name="system:team:add"> <a
					class="btn btn-sm btn-success" href="${ctx!}/team/add">新增</a>
				</@shiro.hasPermission>
			</div>

			<div class="col-sm-6">
				<div class="col-sm-8">
					<input id="parame" name="parame" class="form-control" type="text"
						value="${parame}"
						placeholder="请输入战队编码/战队名/描述...">
				</div>
				<a class="btn btn-primary btn-info" onclick="query()">查询</a>
			</div>
		</div>
		
        <div class="box-body">
              <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>战队编码</th>
                    <th>战队名</th>
                    <th>图标</th>
                    <th>类型</th>
                    <th>描述</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as teamInfo>
                <tr>
                    <td>${teamInfo.id}</td>
                    <td>${teamInfo.code}</td>
                    <td><a href="javascript:void(0)" onclick="showMembers('${teamInfo.code}','${teamInfo.name}')" style="text-decoration: underline;">${teamInfo.name}</a></td>
                    <td><a class="showImg" data-magnify="gallery" href="${ctx!}/${teamInfo.dreamIcon}">查看</td>
                    <td>${teamInfo.type}</td>
                    <td>${teamInfo.description}</td>
                    <td>${teamInfo.createTime}</td>
                    <td>${teamInfo.updateTime}</td>
                    <td>
					<@shiro.hasPermission name="system:team:edit">
					<a class="btn btn-sm btn-primary" href="${ctx!}/team/edit/${teamInfo.id}">编辑</a>
					</@shiro.hasPermission>
                    <@shiro.hasPermission name="system:team:del">
                        <button class="btn btn-sm btn-danger" onclick="del(${teamInfo.id})">删除</button>
					</@shiro.hasPermission>
					</td>
                </tr>
				</#list>
            </table>
        </div>
        <!-- /.box-body -->
         <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/admin/index?sort=updateTime,desc" />
        </div>
    </div>
    <!-- /.box -->
</section>

<script>
 function del(id){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx!}/team/delete/" + id,
                success: function(res){
                    layer.msg(res.message, {time: 2000}, function () {
                        location.reload();
                    });
                }
            });
        });
    }

 //战队显示对应队员
 function showMembers(code,name){
 	layer.open({
		  type: 2,
		  title: '【'+name+'】战队信息',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['950px', '90%'],
		  content: '/member/info?dreamCode='+code
	}); 
}
</script>
<!-- /.content -->
</@layout>