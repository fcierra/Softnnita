<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>ISoft Profiler</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Font Awesome -->
	<link href="http://localhost:8080/ContenidoEstatico/resources/ui/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
	<!-- Theme style -->
	<link href="http://localhost:8080/ContenidoEstatico/resources/ui/css/adminlte.css" rel="stylesheet"/>
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body styleClass="hold-transition login-page">
<section class="content" style="margin-top: 250px">
	<div class="error-page">
		<h2 class="headline text-danger">Alerta</h2>

		<div class="error-content">
			<h3><i class="fa fa-warning text-danger"></i> Oops! Algo anda mal.</h3>

			<p>
				Nosotros estamos trabajando para solventar el problema.
				Si el problema persiste, contacta al administrador.
			</p>

			<form class="search-form">
				<div class="input-group">
					<input type="text" name="search" class="form-control" placeholder="Escribe tu nombre y notifica">

					<div class="input-group-append">
						<button type="submit" name="submit" class="btn btn-danger"><i class="fa fa-search"></i>
						</button>
					</div>
				</div>
				<!-- /.input-group -->
			</form>
			<a href="${pageContext.request.contextPath}/">Ir al inicio</a>
		</div>
	</div>
	<!-- /.error-page -->
</section>
	<!-- /.login-box -->



</body>

</html>
