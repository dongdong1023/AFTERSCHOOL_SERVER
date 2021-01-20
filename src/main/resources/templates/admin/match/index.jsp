<#include "/admin/layout/layout.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<@layout title="比赛信息" active="match">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
       	 比赛信息
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-random"></i>推送管理</a></li>
        <li class="active"><i class="fa fa-video-camera"></i>比赛信息</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
    	       <div class="box-header">
	       <div class="col-sm-6">
			<@shiro.hasPermission name="system:match:add">
				<a class="btn btn-sm btn-success" href="${ctx!}/admin/manage/add">新增</a>
			</@shiro.hasPermission>
			</div>
			<div class="col-sm-6">
				<div class="col-sm-8">
					<input id="parame" name="parame" class="form-control" type="text" value="${parame}" placeholder="请输入比赛编码/比赛名称/描述...">
				</div>
				<a class="btn btn-primary btn-info" onclick="query()">查询</a>
			</div>
		</div>
        <div class="box-body">
              <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>编码</th>
                    <th>比赛名称</th>
                    <th>比赛方A</th>
                    <th>比赛方B</th>
                    <th>状态</th>
                    <th>类型</th>
                    <th>描述</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                <#list matchs as matchInfo>
                <tr>
                    <td>${matchInfo.id}</td>
                    <td>${matchInfo.code}</td>
                    <td>${matchInfo.name}</td>
                    <td>${matchInfo.dreamNameA}</td>
                    <td>${matchInfo.dreamNameB}</td>
                    <td>
                      <#if matchInfo.status == 1>
                            <span class="label label-danger">比赛中</span>
						<#else>
                            <span class="label label-info">未开赛</span>
						</#if>
                    <td>${matchInfo.type}</td>
                    <td>${matchInfo.description}</td>
                    <td>${matchInfo.createTime}</td>
                    <td>${matchInfo.updateTime}</td>
                    <td>
                    	 <#if matchInfo.status == 1>
                            <@shiro.hasPermission name="system:match:off">
                        	<button class="btn btn-sm btn-warning" onclick="status(this,${matchInfo.id},0)">关播</button>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="system:match:pushOBS">
                        	<a class="btn btn-sm btn-danger" href="${ctx!}/admin/manage/command?dreamCodeA=${matchInfo.dreamCodeA}&dreamCodeB=${matchInfo.dreamCodeB}">推流OBS</a>
                            </@shiro.hasPermission>
                         <#else>
                            <@shiro.hasPermission name="system:match:edit">
							<a class="btn btn-sm btn-primary" href="${ctx!}/admin/manage/edit/${matchInfo.id}">编辑</a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="system:match:on">
                           <button class="btn btn-sm btn-warning" onclick="status(this,${matchInfo.id},1)">开赛</button>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="system:match:del">
                           <button class="btn btn-sm btn-danger" onclick="del(${matchInfo.id})">删除</button>
                            </@shiro.hasPermission>
						</#if>

					</td>
                </tr>
				</#list>
            </table>
        </div>
        <!-- /.box-body -->
         <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/admin/manage/index?sort=status,desc:updateTime,desc" />
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
                url: "${ctx!}/admin/manage/delete/" + id,
                success: function(res){
                    layer.msg(res.message, {time: 2000}, function () {
                        location.reload();
                    });
                }
            });
        });
    }
 
 function status(obj,id,status){
	 var text = obj.innerText;
	 var warn = '';
	 var recordText = '';
	 if(text == '关播'){
		 warn = '(提醒:执行此操作本场比赛的临时数据也会清除)'; 
		 recordText = '<br/><input type="checkbox" id="checkRecordDatas"><span style="color:red">同时记录本场数据</span></label>';
	 }
     layer.confirm('确定'+text+'吗?<span style="color:red">'+warn+'</span>' + recordText, {icon: 3, title:'提示'}, function(index){
         	var record = '';
			if($("#checkRecordDatas").is(':checked')){
				//保存比赛数据
				record = 1;
			}else{
				//不保存比赛数据
				record = 0;
			}
           $.ajax({
             type: "POST",
             dataType: "json",
             url: "${ctx!}/admin/manage/status/" + id+"?status="+status+"&record="+record,
             success: function(res){
                 layer.msg(res.message, {time: 2000}, function () {
                     location.reload();
                 });
             }
         });
     });
 }
</script>
<!-- /.content -->
</@layout>