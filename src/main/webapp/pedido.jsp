<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pedido</title>
</head>
<body>
	<c:if test="${error != null}">
		<c:out value="${error}"/>
	</c:if>
	<h2>Realiza tu pedido</h2>
	<table>
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Precio</th>
		</tr>
		<c:forEach items="${listadoProductos}" var="producto">
			<tr>
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td>${producto.precioNormal}</td>
				<td><a href="Controller?operacion=anadir&idproducto=${producto.id}&nombreproducto=${producto.nombre}&productoprecio=${producto.precioNormal}">Añadir</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${not empty carroCompra.getElementos()}">
		<h2>Productos en tu carro</h2>
		<table>
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Cantidad</th>
			</tr>
			<c:forEach items="${carroCompra.getElementos()}" var="productocarro">
				<tr>
					<td>${productocarro.id}</td>
					<td>${productocarro.nombre}</td>
					<td>${productocarro.precioNormal}</td>
					<td>${productocarro.cantidad}</td>
					<td><a href="Controller?operacion=anadir&idproducto=${productocarro.id}&nombreproducto=${productocarro.nombre}&productoprecio=${productocarro.precioNormal}">Añadir</a></td>
					<td><a href="Controller?operacion=eliminar&idproducto=${productocarro.id}&nombreproducto=${productocarro.nombre}&productoprecio=${productocarro.precioNormal}">Retirar</a></td>
					<td><a href="Controller?operacion=remover&idproducto=${productocarro.id}&nombreproducto=${productocarro.nombre}&productoprecio=${productocarro.precioNormal}">Eliminar</a></td>
				</tr>
			</c:forEach>
		</table>
		<a href="Controller?operacion=enviarpedido">Enviar pedido</a>
	</c:if>
</body>
</html>