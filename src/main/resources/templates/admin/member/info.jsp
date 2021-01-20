<#include "/admin/layout/common.jsp">
<!-- Content Header (Page header) -->
<!-- Main content -->
<script>

</script>
<section class="content">
	<!-- Default box -->
	<div class="box box-primary">
		<div class="box-body">
			<table class="table table-striped" id="table">
				<tr>
					<th>队员编码</th>
					<th>游戏名</th>
					<th>Steam</th>
					<th>Steam(32位)</th>
					<th>操作</th>
				</tr>
				<#list members as members>
				<tr>
					<!--TODO 后续优化  -->
					<td>${members.code}</td>
					<td>${members.gameName}</td>
					<td>${members.steamId}</td>
					<td>${members.steamId32}</td>
					<td><@shiro.hasPermission name="system:member:edit"> <#if
						members.memberStatus == 1>
						<button class="btn btn-sm btn-warning"
							onclick="status(this,${members.id},0)">替补</button> <#else>
						<button class="btn btn-sm btn-warning"
							onclick="status(this,${members.id},1)">上线</button> </#if>
						<button class="btn btn-sm btn-primary"
							onclick="updateDream('${members.code}')">换队</button>
						</@shiro.hasPermission> <@shiro.hasPermission
						name="system:member:del">
						<button class="btn btn-sm btn-danger" onclick="del(${members.id})">删除</button>
						</@shiro.hasPermission>
					</td>
				</tr>
				</#list>
			</table>
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

 function updateDream(code){
	 layer.open({
			type : 2,
			title : '选择战队',
			shadeClose : true,
			shade : 0.8,
			area : [ '550px', '90%' ],
			content : '/team/info?type=updateDream&memberCode='+code
		});
 }

 function GetValue(obj) {
	 if(obj){
		 location.reload();
	 }
 }
</script>