package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
		return conn;
	}
		
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	
	
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch(IOException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public static void buscaProdutos (String codBarras, String nome, double valUni) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_produtos WHERE cod_barras IN (" + codBarras+ ")");
			
			while(rs.next()) {
				//System.out.println(rs.getInt("id") + ", " + rs.getString("descricao") + ", " + rs.getBigDecimal("preco") + ", " + rs.getInt("qtd_estoque") + ", " + rs.getInt("cod_barras"));
				nome = rs.getString("descricao");
				valUni = rs.getDouble("preco");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
