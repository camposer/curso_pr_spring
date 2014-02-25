<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.indra.formacion.pr.spring.to.Producto" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8'>
		<title>Tienda de artículos electrónicos</title>
		<link rel='stylesheet' type='text/css' href='../css/estilos.css'>
		<link rel='stylesheet' type='text/css' href='../css/carrito.css'>
	</head>
	<body>
		<h1>Tienda de artículos electrónicos</h1>
		<form>
			<table id='tablaCarrito' class='tabla'>
				<tr>
					<th>Artículo</th>
					<th>Precio</th>
					<th>Cantidad</th>
					<th>Precio</th>
					<th>Eliminar</th>
				</tr>

		<%
		Map<Integer, Producto> productos = (Map<Integer, Producto>) session.getAttribute("productos");
		float totalTotal = 0;
		if (productos != null) for (Producto p : productos.values()) {
		%>
					<tr>
						<td><%= p.getNombre()  %></td>
						<td><%= p.getPrecio()  %> €</td>
						<td><input type='text' name='cantidad' maxlength='3'  value='<%= p.getCantidad() %>' ></td>
						<td><input type='text' name='precio' value='<%= p.getTotal() %>' readonly></td>
						<td class='eliminar'><a href='Eliminar?id=<%= p.getId() %>'>x</a></td>
					</tr>
		<%			
			totalTotal += p.getTotal();
		}
		 %>
		
				<tr class='total'>
					<td colspan='3'>Total</td>
					<td><input type='text' name='total' value='<%= totalTotal %>'readonly></td>
				</tr>
				<tr>
		
					<td colspan='5'>
						<input type='button' value='Atrás' onclick='window.location.href="Principal";'>
						<input type='button' value='Comprar'>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

