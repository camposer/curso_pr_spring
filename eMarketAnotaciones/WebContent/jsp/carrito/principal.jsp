<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.indra.formacion.pr.spring.to.Producto" %>
<%@ page import="java.util.List" %>

<%@ include file="cabecera.jsp" %>

		<link rel="stylesheet" type="text/css" href="../css/principal.css">
		<style>
			#contenedor {
				margin: 0 auto;
				width: 80%;
			}

			#cabecera {
				background-color: #3b5998;
				color: white;
				text-align: center;
			}

			#pie {
				background-color: #3b5998;
				color: white;
				text-align: center;
				clear: both;
			}

			#contenido {
				background-color: red;
				float: left;
				width: 80%;
				height: 680px;
			}

			#sidebar {
				background-color: gray;
				float: left;
				width: 20%;
				height: 680px;
			}
		</style>
		
	<body>
		<div id="contenedor">
			<div id="cabecera">
				Esta es la cabecera...
			</div>

			<div id="sidebar">
				Este es el sidebar
			</div>

			<div id="contenido">
				<h1>Tienda de artículos electrónicos</h1>
				<form action="Agregar" method="post">
					<table id="tablaArticulo" class="tabla">
						<tr>
							<th>Artículo</th>
							<th>Precio (€)</th>
							<th>Cantidad</th>
						</tr>
						<%
						List<Producto> productos = (List<Producto>) request.getAttribute("productos");
						
						if (productos != null) for (Producto p : productos) {
						%>
						<tr>
							<td><%= p.getNombre() %></td>
							<td><%= p.getPrecio() %></td>
							<td>
								<input type="hidden" name="productoId" value="<%= p.getId() %>">
								<input type="text" name="cantidad" maxlength="3">
							</td>
						</tr>
						<%
						}
						%>
						<tr>
							<td colspan="3">
								<input type="reset" value="Borrar">
								<input type="submit" value="Agregar al carrito">
								<input type="button" value="Ver carrito" onclick="javascript:window.location.href='Mostrar';">
							</td>
						</tr>
					</table>
				</form>
			</div>


			<div id="pie">
				Este es el pie de página...
			</div>

		</div>

<%@ include file="pie.jsp"%>