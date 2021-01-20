<#include "/admin/layout/layout.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<#assign css>
<link href="${ctx!}/assets/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
</#assign>
<#assign js>
<script src="${ctx!}/assets/plugins/ztree/jquery.ztree.all.min.js"></script>
<script>
    var setting = {
        check: {
            enable: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };

    setting.check.chkboxType = {
        "Y": "ps",
        "N": "s"
    };
    $.ajax({
        type: "POST",
        url: "${ctx!}/admin/resource/tree/1",
        dataType: 'json',
        success: function (msg) {
            $.fn.zTree.init($("#tree"), setting, msg);
            openZtreeById(${parentId});
        }
    });

    /**
     * 打开并选中指定树节点
     * @param id
     */
    function openZtreeById(id) {
        var tree = $.fn.zTree.getZTreeObj("tree");
        var node = tree.getNodeByParam("id", id);
        tree.selectNode(node, true)
        tree.checkNode(node, true, true);
    }


    /**
     * 树节点点击事件
     * @param event
     * @param treeId
     * @param data
     */
    function zTreeOnClick(event, treeId, data) {
        //console.log(data);
        var parentId = data.id;
        location.href = "${ctx!}/admin/resource/children/" + parentId;
    }

    function del(id) {
        layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx!}/admin/resource/delete/" + id,
                success: function (res) {
                    layer.msg(res.message, {time: 1000}, function () {
                        location.reload();
                    });
                }
            });
        });
    }
</script>
</#assign>
<@layout title="资源管理" active="resource">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        资源列表
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 资源管理</a></li>
        <li class="active"><i class="fa fa-table"></i> 资源列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="box-header box box-primary">
        <@shiro.hasPermission name="system:resource:add">
        <a class="btn btn-sm btn-success" href="${ctx!}/admin/resource/add">新增</a>
    </@shiro.hasPermission>
    </div>
    <!-- Main grant -->
    <div class="col-md-3">
        <!-- Default box -->
        <div class="box  box-primary">
            <div class="box-body">
                <ul id="tree" class="ztree"></ul>
            </div>
        </div>
    </div>
    <!-- /.grant -->
    <div class="col-md-9">
        <div class="box  box-primary">
            <div class="box-body" id="children">
                <table class="table table-striped">
                    <tr>
                        <th>ID</th>
                        <th>资源名称</th>
                        <th>资源key</th>
                        <th>类型</th>
                        <th>资源url</th>
                        <th>层级</th>
                        <th>排序</th>
                        <th>icon</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    <#list pageInfo as resourceInfo>
                    <tr>
                        <td>${resourceInfo.id}</td>
                        <td>${resourceInfo.name}</td>
                        <td>${resourceInfo.sourceKey}</td>
                        <td>
                            <#if resourceInfo.type == 0>
                            <span class="label label-info">目录</span>
                            <#elseif resourceInfo.type == 1>
                            <span class="label label-danger">菜单</span>
                            <#else >
                            <span class="label label-warning">按钮</span>
                        </#if>
                        </td>
                        <td>${resourceInfo.sourceUrl}</td>
                        <td>${resourceInfo.level}</td>
                        <td>${resourceInfo.sort}</td>
                        <td>${resourceInfo.icon}</td>
                        <td>
                            <#if resourceInfo.isHide == 1>
                            <span class="label label-danger">隐藏</span>
                            <#else>
                            <span class="label label-info">显示</span>
                        </#if>
                        </td>
                        <td>
                            <@shiro.hasPermission name="system:resource:edit">
                            <a class="btn btn-sm btn-primary"
                               href="${ctx!}/admin/resource/edit/${resourceInfo.id}">编辑</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="system:resource:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${resourceInfo.id})">删除</button>
                    </@shiro.hasPermission>
                    </td>
                    </tr>
                </#list>
                </table>
            </div>
        </div>
    </div>
</section>
<!-- /.content -->
</@layout>