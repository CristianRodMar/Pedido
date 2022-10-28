package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexiones.Conexion;
import entidades.CarroCompra;
import entidades.Cliente;
import entidades.Producto;
import excepciones.PedidoException;

public class DaoCarroCompra {
	
	public void enviarPedido(CarroCompra carroCompra, Cliente cliente, String direccion) throws PedidoException, SQLException, Exception {
		Conexion conexion = new Conexion();
		DaoCliente daoCliente = new DaoCliente();
		Connection con = null;
		Statement selectIdPedido = null;
		ResultSet rs = null;
		PreparedStatement insertPedido = null;
		PreparedStatement insertDetalles = null;
		double costeTotal = 0;
		int contador = 0;
		int idPedido = 0;
		try {
			daoCliente.getClienteById(cliente.getId());
			con = conexion.getConexion();
			String ordenSQL = "SELECT S_PEDIDO.NEXTVAL FROM DUAL";
			selectIdPedido = con.createStatement();
			rs = selectIdPedido.executeQuery(ordenSQL);
			if (rs.next()) {
				idPedido = rs.getInt(1);
			} else {
				throw new PedidoException("Error al obtener el valor de idPedido");
			}
			con.setAutoCommit(false);
			ordenSQL = "INSERT INTO PEDIDO VALUES(?, ?, 'PENDIENTE', 'NO', SYSDATE, ?)";
			insertPedido = con.prepareStatement(ordenSQL);
			insertPedido.setInt(1, idPedido);
			insertPedido.setInt(2, cliente.getId());
			insertPedido.setString(3, direccion);
			insertPedido.executeQuery();
			//1 idpedido, 2 lineadetalle, 3 idproducto, 4 cantidad, 5 preciounitario,6 preciototal
			ordenSQL = "INSERT INTO DETALLEPEDIDO VALUES(?,?,?,?,?,?)";
			insertDetalles = con.prepareStatement(ordenSQL);
			for (Producto producto : carroCompra.getElementos()) {
				insertDetalles.setInt(1, idPedido);
				insertDetalles.setInt(2,contador);
				insertDetalles.setLong(3, producto.getId());
				insertDetalles.setInt(4,producto.getCantidad());
				insertDetalles.setDouble(5, producto.getPrecioNormal());
				insertDetalles.setDouble(6, (producto.getPrecioNormal() * producto.getCantidad()));
				costeTotal += producto.getPrecioNormal() * producto.getCantidad();
				contador++;
				insertDetalles.executeQuery();
			}
			if (cliente.getLimiteCredito() < costeTotal) {
				throw new PedidoException("El pedido sobrepasa el limite de credito");
			}
			con.commit();
		} catch(PedidoException e) {
			e.printStackTrace();
			con.rollback();
			throw e;
			
		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
			throw e;
			
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		} finally {
			if (con != null) {
				con.close();
			}
			
			if (selectIdPedido != null) {
				selectIdPedido.close();
			}
			
			if (rs != null) {
				rs.close();
			}
			
			if (insertPedido != null) {
				insertPedido.close();
			}
			
			if (insertDetalles != null) {
				insertDetalles.close();
			}
		}
	}
}
