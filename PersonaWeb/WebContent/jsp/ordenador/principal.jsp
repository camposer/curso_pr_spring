<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="es.indra.formacion.pr.persistence.model.Persona" %>
<%@ page import="es.indra.formacion.pr.persistence.model.Ordenador" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="ordenador.titulo"/></title>
</head>
<body>
	<h1><spring:message code="ordenador.titulo"/></h1>
	
	<form action="agregar.do" method="post">
		<table>
			<tr>
				<td><spring:message code="ordenador.nombre"/>:</td>
				<td><input type="text" name="nombre"/></td>
			</tr>
			<tr>
				<td><spring:message code="ordenador.serial"/>:</td>
				<td><input type="text" name="serial"/></td>
			</tr>
			<tr>
				<td><spring:message code="ordenador.propietario"/>:</td>
				<td>
					<select name="personaId">
						<c:forEach items="${personas}" var="p">
							<option value="${p.id}">${p.nombre} ${p.apellido}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="<spring:message code="ordenador.boton.agregar"/>"/></td>
			</tr>
		</table>
	</form>
			
	<br/>
	
	<table>
		<tr>
			<th><spring:message code="ordenador.nombre"/></th>
			<th><spring:message code="ordenador.serial"/></th>
			<th><spring:message code="ordenador.propietario"/></th>
		</tr>
		<c:forEach items="${ordenadores}" var="o">
			<c:set var="propietario" value="${o.persona.nombre} ${o.persona.apellido}"/>			
			<tr>
				<td>${o.nombre}</td>
				<td>${o.serial}</td>
				<td>${propietario}</td>
			</tr>
		</c:forEach>
			
	</table>
</body>
</html>