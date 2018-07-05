<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>ISoftnnita-Profiler</title>
	<link href="css/adminlte.css" rel="stylesheet">
	<link href="plugins/iCheck/square/blue.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>


<body class="hold-transition login-page">
<div class="login-box">
	<div class="login-logo">
		<b>
			<spring:message code="commons.name.company.sub"/>
		</b>
		<spring:message code="commons.name.company.sub1"/>
	</div>
	<!-- /.login-logo -->
	<div class="card">
		<div class="card-body login-card-body">
			<p class="login-box-msg">
				<spring:message code="login.init"/>
			</p>

			<form:form method="POST" >
				<div class="form-group has-feedback">
					<input type="email" class="form-control" placeholder="<spring:message code="login.holder.login"/>">
					<span class="fa fa-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="<spring:message code="login.holder.pass"/>">
					<span class="fa fa-lock form-control-feedback"></span>
				</div>
				<div class="row">

					<!-- /.col -->
					<div class="col-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">
							<spring:message code="commons.buttom.accept"/>
						</button>
					</div>
					<!-- /.col -->
				</div>
			</form:form>


			<!-- /.social-auth-links -->

			<p class="mb-1">
				<a href="#">
					<spring:message code="login.label.forgot.pass"/>
				</a>
			</p>
			<p class="mb-0">
				<a href="register.html" class="text-center">
					<spring:message code="login.label.register"/>
				</a>
			</p>
		</div>
		<!-- /.login-card-body -->
	</div>
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- iCheck -->
<script src="plugins/iCheck/icheck.min.js"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass   : 'iradio_square-blue',
            increaseArea : '20%' // optional
        })
    })
</script>

</body>
</html>