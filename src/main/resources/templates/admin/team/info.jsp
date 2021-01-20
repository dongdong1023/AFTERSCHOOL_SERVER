<#include "/admin/layout/common.jsp">
<#import "/admin/layout/macro.jsp" as macro>
<!-- Content Header (Page header) -->
<!-- Main content -->
<script>
	$(function(){
		$(".navbar").remove();
	});
	
	function checkForm(){
		var dataCode = $('input:radio:checked').val();
		var type = getUrlParam('type');
		if(isBlank(dataCode)){
			topMsg('请选择要绑定的战队');
			return false;
		}
		
		if(!isBlank(type) && type == 'updateDream'){
			var memberCode = getUrlParam('memberCode');
			$.ajax({
				type : "POST",
				contentType : false,
				processData : false,
				async : false,
				url : "${ctx!}/member/updateDream/"+memberCode+"?dreamCode="+dataCode,
				dataType : "JSON",
				success : function(res) {
					layer.msg(res.message, {
						time : 2500
					}, function() {
						parent.layer.closeAll();
						parent.GetValue(true);
					});
				}
			});
		}else{
			parent.GetValue(dataCode);
			parent.layer.closeAll();
		}
	}
</script>
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-body">
              <table class="table table-striped" id="table">
                <tr>
                    <th>选择</th>
                    <th>战队名</th>
                </tr>
                <#list pageInfo.content as teamInfo>
                <tr>
                	<!--TODO 后续优化  -->
                    <td><input type="radio" name="iCheck" class="icheckbox_square-blue" value="${teamInfo.code}"></td>
                    <td>${teamInfo.name}</td>
                </tr>
				</#list>
            </table>
        </div>
           	<div class="box-footer">
               <button type="button" class="btn btn-info pull-right btn-submit" onclick="checkForm()">确定</button>
              </div>
        <!-- /.box-body -->
        <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/team/info?" />
        </div>
    </div>
    <!-- /.box -->
</section>
<!-- /.content -->