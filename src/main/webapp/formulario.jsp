<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario</title>
</head>
<body>
	<c:if test="${error != null}">
		<c:out value="${error}"/>
	</c:if>
	<form action="Controller">
		<label for="nombre">ID:</label>
		<input type="text" name="id" value="${id}" required>
		<br/>
		<label for="direccion">Dirección:</label>
		<input type="text" name="direccion" value="${direccion}" required>
		<input type="submit" name="operacion" value="enviarformulario">
	</form>
	<a href="Controller?operacion=iniciocompra">Atrás</a>
</body>
</html>