<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:useBean id='TestingRichard' scope='session'
	class='Servlets.TestingRichard' />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finalistas Concurso de Talentos Uruguayos</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/sitio.css">

</head>


<body>

	<!------------------ Hover Effect Style : Demo - 16 --------------->
	<form action="richard" method="post">



		<div class="container mt-40">
			<h3 class="text-center">VOTA POR TU PARTICIPANTE FAVORITO</h3>
			<div class="row mt-30">



				<div class="col-md-4 col-sm-6">
					<div class="box16">
						<img src="http://bestjquery.com/tutorial/hover-effect/demo118/images/img-1.jpg">
<%-- 						<img src="data:image/jpeg;base64,<%=request.getAttribute("fotoF1")%>"> --%>
						<div class="box-content">
							<h3 class="title"><%= request.getAttribute("nombreF1") %></h3>
							<span class="post"> GUITARRISTA </span>
							<ul class="social">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-instagram"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>

				<!--  -->
				<div class="col-md-4 col-sm-6">
					<div class="box16">
						<img
							src="http://bestjquery.com/tutorial/hover-effect/demo118/images/img-2.jpg">
						<div class="box-content">
							<h3 class="title"><%= request.getAttribute("nombreF2") %></h3>
							<span class="post">Web Designer</span>
							<ul class="social">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-instagram"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="col-md-4 col-sm-6">
					<div class="box16">
						<img
							src="http://bestjquery.com/tutorial/hover-effect/demo118/images/img-3.jpg">
						<div class="box-content">
							<h3 class="title"><%= request.getAttribute("test") %></h3>
							<span class="post">Web Designer</span>
							<ul class="social">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-instagram"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>


	</form>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>