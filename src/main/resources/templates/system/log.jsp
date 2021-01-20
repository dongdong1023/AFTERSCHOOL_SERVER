<#include "/admin/layout/layout.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<@layout title="系统日志" active="log">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
       	 系统日志
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i>系统管理</a></li>
        <li><a href="#"><i class="fa fa-file-text-o"></i>系统日志</a></li>
        <li class="active"><i class="fa fa fa-table"></i>日志列表</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
		<div class="box-header">
			<div class="col-sm-6">
				<div class="col-sm-8">
					<input id="parame" name="parame" class="form-control" type="text" value="${parame}" placeholder="请输入ip/操作模块/操作类型...">
				</div>
				<a class="btn btn-primary btn-info" onclick="query()">查询</a>
			</div>
		</div>
		<div class="box-body">
              <table class="table table-striped" style="table-layout:fixed;">
                <tr>
                    <th>ID</th>
                    <th>用户</th>
                    <th>操作模块</th>
                    <th>操作类型</th>
                    <th>IP</th>
                    <th>结果</th>
                    <th>日期</th>
                    <th>详细</th>
                </tr>
                <#list systemLogs as logs>
                <tr>
                    <td>${logs.id}</td>
                    <td>${logs.nickName}</td>
                    <td>${logs.module}</td>
                    <td>${logs.userAction}</td>
                    <td>${logs.ip}</td>
                    <td>
                    <#if logs.result == 0>
                    <span class="label label-success" style="color: limegreen">成功</span>
                    <#else>
                    <span class="label label-danger" style="color: darkred">失败</span>
                    </#if>
                    </td>
                    <td>${logs.createTime}</td>
                    <td title='${logs.description}' style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${logs.description}</td>
                </tr>
				</#list>
            </table>
        </div>
        <!-- /.box-body -->
         <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/admin/systemLog/index?sort=createTime,desc" />
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>