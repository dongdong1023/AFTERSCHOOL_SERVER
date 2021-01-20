<#include "/admin/layout/layout.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<@layout title="数据备份" active="backup">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        数据备份
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i>系统管理</a></li>
        <li><a href="#"><i class="fa fa-heart"></i>数据备份</a></li>
        <li class="active"><i class="fa fa fa-table"></i>备份列表</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
		<div class="box-header">
        <@shiro.hasPermission name="system:backup:backup">
            <button class="btn btn-sm btn-primary" onclick="execute('备份','backup','')">备份</button>
        </@shiro.hasPermission>
		</div>
		<div class="box-body">
              <table class="table table-striped" style="table-layout:fixed;">
                <tr>
                    <th>文件名</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as backup>
                <tr>
                    <td>${backup.name}</td>
                    <td><span <#if backup.title == '系统默认备份'>class="btn-sm btn-danger" </#if>>${backup.title}</span></td>
                    <td>
                 <@shiro.hasPermission name="system:backup:restore">
                    <button class="btn btn-sm btn-warning" onclick="execute('还原','restore','${backup.name}')">还原</button>
                </@shiro.hasPermission>
              <@shiro.hasPermission name="system:backup:delete">
                  <button class="btn btn-sm btn-danger" onclick="execute('删除','delete','${backup.name}')">删除</button>
              </@shiro.hasPermission>
            <@shiro.hasPermission name="system:backup:setting">
            <button class="btn btn-sm btn-info" onclick="execute('设置系统默认备份','setting','${backup.name}')">设置</button>
            </@shiro.hasPermission>
                    </td>
                </tr>
				</#list>
            </table>
        </div>
        <!-- /.box-body -->
    <div class="box-footer clearfix">
        <@macro.page pageInfo=pageInfo url="${ctx!}/admin/backup/index?" />
    </div>
    </div>
    <!-- /.box -->
    <script>
        function execute(name,type,flieName) {
            layer.confirm("确定"+name+"吗?", {icon: 3, title: '提示'}, function (index) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/backup/"+type+"/" + encodeURI(flieName),
                    success: function (res) {
                        layer.msg(res.message, {time: 2000}, function () {
                            location.reload();
                        });
                    }
                });
            });
        }
    </script>
</section>
<!-- /.content -->
</@layout>