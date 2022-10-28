package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexiones.Conexion;
import entidades.Cliente;
import excepciones.PedidoException;

public class DaoCliente {
	
	public Cliente getClienteById(int id) throws PedidoException, SQLException, Exception {
		Cliente cliente = null;
		Conexion conexion = new Conexion();
		Connection con= null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = conexion.getConexion();
			String ordenSQL = "SELECT * FROM CLIENTE WHERE ID = ?";
			st = con.prepareStatement(ordenSQL);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				//id, nombre, tipo, limitecredito
				cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
			} else {
				throw new PedidoException("El id del cliente no existe");
			}
		} catch (PedidoException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null) {
				con.close();
			}
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
		return cliente;
	}
}
