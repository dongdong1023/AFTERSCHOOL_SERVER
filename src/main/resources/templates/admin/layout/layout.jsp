<#macro layout title="" active="">
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CSGOAdmin - ${title!}</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/assets/plugins/bootstarp/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/assets/plugins/font-awesome/css/font-awesome.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/assets/plugins/Ionicons/css/ionicons.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/assets/css/AdminLTE.css">
    <!-- 图片查看器 -->
    <link rel="stylesheet" href="/assets/css/jquery.magnify.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/assets/css/_all-skins.css">
    <!-- layer -->
    <link rel="stylesheet" href="/assets/plugins/layer/theme/default/layer.css">
    <!-- checkBox -->
    <link rel="stylesheet" href="/assets/css/icheck-blue.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/assets/plugins/iCheck/square/blue.css">
    <!-- bootstrapValidator -->
    <link rel="stylesheet" href="/assets/plugins/bootstrapValidator/bootstrapValidator.min.css">
    ${css!}

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="/assets/js/html5shiv.min.js"></script>
  <script src="/assets/js/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="/assets/css/googleapis-source.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>dmin</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>CSGO</b>Admin</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
          <ul class="nav navbar-nav">

              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <img src="<@shiro.principal property="avatar"/>" class="user-image" alt="User Image">
                      <span class="hidden-xs"><@shiro.principal property="nickName"/></span>
                  </a>
                  <ul class="dropdown-menu">
                      <!-- User image -->
                      <li class="user-header">
                          <img src="<@shiro.principal property="avatar"/>" class="img-circle" alt="User Image">

                          <p>
                              <@shiro.principal property="description"/>
                              <small><@shiro.principal property="createTime"/></small>
                          </p>
                      </li>
                      <!-- Menu Footer-->
                      <li class="user-footer">
                          <div class="pull-left">
                              <a href="${ctx!}/admin/user/updatePwd" class="btn btn-default btn-flat">修改密码</a>
                          </div>
                          <div class="pull-right">
                              <a href="${ctx!}/admin/logout" class="btn btn-default btn-flat">退出</a>
                          </div>
                      </li>
                  </ul>
              </li>
          </ul>
      </div>
    </nav>
  </header>

  <!-- =============================================== -->

  <!-- Left side column. contains the sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<@shiro.principal property="avatar"/>" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><@shiro.principal property="nickName"/></p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
      
        <li class="treeview <#if active=="home" || active=="member">active</#if>">
          <a href="#">
            <i class="fa fa-home"></i> <span>战队管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <@shiro.hasPermission name="system:team:index">
                <li <#if active = "home">class="active" </#if>><a href="${ctx!}/admin/index?sort=updateTime,desc"><i class="fa fa-user-o"></i> <span>战队信息</span></a></li>
            </@shiro.hasPermission>
            
             <@shiro.hasPermission name="system:member:index">
                <li <#if active = "member">class="active" </#if>><a href="${ctx!}/member/index?sort=updateTime,desc"><i class="fa fa-users"></i> <span>队员信息</span></a></li>
            </@shiro.hasPermission>
          </ul>
        </li>
        
        <li class="treeview <#if active=="match" >active</#if>">
          <a href="#">
            <i class="fa fa-random"></i> <span>推送管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <@shiro.hasPermission name="system:match:index">
                <li <#if active=="match">class="active"</#if>><a href="${ctx!}/admin/manage/index?sort=status,desc:updateTime,desc"><i class="fa fa-video-camera"></i>比赛信息</a></li>
            </@shiro.hasPermission>
          </ul>
        </li>
        
        <li class="treeview <#if active=="user" || active=="role" || active=="resource" ||active=="backup" || active=="log" >active</#if>">
          <a href="#">
            <i class="fa fa-cog"></i> <span>系统管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <@shiro.hasPermission name="system:user:index">
                <li <#if active=="user">class="active"</#if>><a href="${ctx!}/admin/user/index"><i class="fa fa-user-o"></i> 用户管理</a></li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="system:role:index">
                <li <#if active=="role">class="active"</#if>><a href="${ctx!}/admin/role/index"><i class="fa fa-user-circle-o"></i> 角色管理</a></li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="system:resource:index">
                <li <#if active=="resource">class="active"</#if>><a href="${ctx!}/admin/resource/children/-1"><i class="fa fa-file-o"></i> 资源管理</a></li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="system:backup:index">
                <li <#if active=="backup">class="active"</#if>><a href="${ctx!}/admin/backup/index"><i class="fa fa-heart"></i>数据备份</a></li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="system:log:index">
                <li <#if active=="log">class="active"</#if>><a href="${ctx!}/admin/systemLog/index?sort=id,desc:createTime,desc"><i class="fa fa-file-text-o"></i>系统日志</a></li>
            </@shiro.hasPermission>
          </ul>
        </li>
		
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
      <#nested >
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0.0
    </div>
    <strong>Copyright &copy; 2019 <a href="http://www.imbatv.cn/" target="view_blank">IMBATV</a>赛事管理系统.</strong> All rights
    reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="/assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/assets/plugins/bootstarp/js/bootstrap.js"></script>
<!-- SlimScroll -->
<script src="/assets/plugins/jquery-slimscroll/jquery.slimscroll.js"></script>
<!-- FastClick -->
<script src="/assets/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/assets/js/adminlte.js"></script>
<!-- layer -->
<script src="/assets/plugins/layer/layer.js"></script>
<!-- util -->
<script src="/js/util.js"></script>
<!-- 图片查看器 -->
<script src="/assets/js/jquery.magnify.min.js"></script>
<!--checkeBox  -->
<script src="/assets/js/icheck.js"></script>
<!--管理js  -->
<script src="/assets/js/command.js"></script>
<!-- iCheck -->
<script src="/assets/plugins/iCheck/icheck.js"></script>
<!--bootstrapValidator  -->
<script src="/assets/plugins/bootstrapValidator/bootstrapValidator.min.js"></script>
<script>
    $(document).ready(function () {
        $('.sidebar-menu').tree();
        $(".btn-back").click(function () {
            window.history.back();
        });
    })
    
 
	function query() {
		var url = window.location.href;
		url = url.split("?")[0];
		var parame = $("#parame").val();
		location.href = url + "?parame=" + parame;
	}
</script>
${js!}
</body>
</html>
</#macro>