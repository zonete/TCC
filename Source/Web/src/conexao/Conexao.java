package conexao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Conexao {
	public Statement statement;
	public Connection connection;

	public Statement abrirConexao() throws SQLException {
		try {
			//Atribui o driver
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			String banco = "tcc"; //Nome do DataBase
			String host = "localhost"; //Local do Banco de Dados
			String str_conn = "jdbc:mysql://" + host + ":3306/" + banco; 																	
			String usuario = "root";//Usuário para acesso ao BD
			String senha = "230306";//Senha para acesso ao BD
			Connection conn = DriverManager.getConnection(str_conn, usuario,senha);
			statement = conn.createStatement();
			return statement;
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
			
		}

	}
	
	public void fecharConexao() throws SQLException {
		statement.close();
		connection.close();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		this.fecharConexao();
		super.finalize();
	}


}
