<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ejemplo de Consumo de REST API</title>
</head>
<body>
	<h1> Aqui pap's Agrega un Rol</h1>
	
	<c:url var="agregar" value="/saveOrUdpateRol"></c:url>
	<form:form action="${agregar}" commandName="rol">
	<table>
		<c:if test="${!empty rol.description}">
			<tr>
				<td> 
					<form:label path="idRol">
						<spring:message text="ID ROL"/>
					</form:label>
				</td>
				<td>
					<form:input path="idRol" readonly="true" size="8" disable="true"/>
					<form:hidden path="idRol"/>
				</td>
			</tr>
			
		</c:if>
	       <tr>
	       		<td>
	       			<form:label path="description">
	       				<spring:message text="Descripcion"/>
	       			</form:label>
	       		</td>
	       		<td>
	       			<form:input path="description"/>
	       		</td>
	       </tr>
	       <tr>
	       		<td colspan="2">
	       			<c:if test="${!empty rol.description}">
	       				<input type="submit" value="Editar Rol">
	       			</c:if>
	       			<c:if test="${ empty rol.description}">
	       				<input type="submit" value="Agregar Rol">
	       			</c:if>
	       		</td>
	       </tr>
	</table>
	</form:form>
	<h2> Lista de Roles</h2>
	
	<c:if test="${!empty  lista}">
		<table bordercolor="solid" border="1">
			<tr>
				<th> ID ROL</th>
				<th> Descripcion </th>
				<th> Eliminar</th>
			</tr>
			<c:forEach var="index" items="${lista}">
				<tr>
					<td>${index.idRol} </td>
					<td>${index.description}</td>
				
				</tr>		
			</c:forEach>
		</table>
	</c:if>
</body>
</html>