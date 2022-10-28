package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Producto;

public class DaoProducto {
	
	public ArrayList<Producto> listarProductos() throws Exception {
		ArrayList<Producto> listadoProductos = new ArrayList<>();
		Conexion conexion = new Conexion();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = conexion.getConexion();
			st = con.createStatement();
			String ordenSQL = "SELECT ID, NOMBRE, PRECIO_NORMAL FROM PRODUCTO";
			rs = st.executeQuery(ordenSQL);
			while (rs.next()) {
				Producto miProducto = new Producto(rs.getLong("ID"),rs.getString("NOMBRE"),rs.getDouble("PRECIO_NORMAL"));
				listadoProductos.add(miProducto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return listadoProductos;
	}
	
}
