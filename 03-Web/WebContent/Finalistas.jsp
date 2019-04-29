<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id='Finalistas' scope='session' class='Servlets.Finalistas' />

<jsp:useBean id='ListaFinalistas' scope='session'
	class='java.util.ArrayList' />

<%@page import="ValueObjects.*"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finalistas - Concurso de Talentos Uruguayos</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/sitio.css">
<link rel="icon" type="image/png" sizes="16x16"
	href="img/favicon-16x16.png">
</head>


<body>
	<!------------------ Hover Effect Style : Demo - 16 --------------->
	<div class="container mt-40">
		<div>
			<img class="logoTalento" src="img/logo.png">
		</div>
		<h2 class="text-center">VOTA POR TU FINALISTA FAVORITO</h2>
<!-- 		<div class="row mt-30" style="margin-right: 4%;">    -->
			<div class="row mt-30">  
			<% 
				ArrayList<VOParticipanteFinalista> listaFinalistas = (ArrayList<VOParticipanteFinalista>) request.getAttribute("ListaFinalistas"); 
				
			%>

			<% 
			for(VOParticipanteFinalista f : listaFinalistas ){%>
				<div class="col-md-4 col-sm-6" style="flex: 0 0 30.333%; margin-left: auto;">
				<!-- <div class="col-md-4 col-sm-6"> -->
					<div class="box16">
						<img src="data:image/jpeg;base64,<%=f.getFoto()%>" width="300"
							height="350">
						<div id="contenedor" class="box-content">
							<h3 class="title"><%=f.getNombreArtistico()%></h3>
	
							 <span id="<%=f.getNombreArtistico().replace(" ", "")%>" class="btn btn-outline-light font-weight-bold">Votos: <%=f.getPuntajeTotalPublico()%></span> 
							
							 <span class="post">Edad: <%=f.getEdad()%></span>
	
													
							<ul class="social">
								<input id="<%=f.getNombreArtistico()%>" type="button"
									onclick="enviar_formulario(this)" class="btn btn-outline-primary"
									value="Votar!" name="id1">
	
							</ul>
						</div>
					</div>
				</div>
			<% 	
			} 
			%>

		</div>
	</div>


	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script>
		function enviar_formulario(botonPress) {
			var idVotado = botonPress.id;
			$.ajax({
				 data:{votado:idVotado},
	             url:'finalistas',
	             type:'POST',
	             async: true,
	             success : function(res){
	            	 
	            	 $('#' + res.finalista0.replace(/ /g, "")).text("Votos : " + res.puntaje0);
	            	 $('#' + res.finalista1.replace(/ /g, "")).text("Votos : " + res.puntaje1);
	            	 $('#' + res.finalista2.replace(/ /g, "")).text("Votos : " + res.puntaje2);
		          
	         	 }
	           });
			}
		
		function refrescar_formulario() {
			
			$.ajax({
				 data:{refrescar:"refrescar"},
	             url:'finalistas',
	             type:'POST',
	             async: true,
	             success : function(res){
	            	
	            	 $('#' + res.finalista0.replace(/ /g, "")).text("Votos : " + res.puntaje0);
	            	 $('#' + res.finalista1.replace(/ /g, "")).text("Votos : " + res.puntaje1);
	            	 $('#' + res.finalista2.replace(/ /g, "")).text("Votos : " + res.puntaje2);
		          
	         	 }
	           });
			}
		
		setInterval(function() {
			refrescar_formulario();
        }, 3000);
		
		
	</script>


</body>
</html>