package conexiones;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class Conexion {
    public Conexion() {
    }
    public Connection getConexion() throws SQLException,Exception{
    	//String url="jdbc:oracle:thin:VS2DAWBIBLIOTECA23/VS2DAWBIBLIOTECA23@10.0.1.12:1521:oradai";
		String url="jdbc:oracle:thin:VS2DAWPEDIDO23/VS2DAWPEDIDO23@80.28.158.14:1521:oradai";
        Connection con;
        OracleDataSource ods;
        try{

        	ods=new OracleDataSource();
            ods.setURL(url);
            con=ods.getConnection();
        }
        catch(SQLException sqle){
        	System.out.println(sqle.toString());
            throw sqle;
        }
        catch(Exception e){
        	System.out.println(e.toString());
            throw e;
        }
        return con;
    }
}