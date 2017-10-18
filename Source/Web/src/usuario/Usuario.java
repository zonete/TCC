package usuario;

import conexao.Conexao;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Usuario {
	public String Nome;
	public String Senha;
	public String Login;
	public Integer Codigo;
	public Integer Nivel;
	public Boolean Adm;
	public String ListaUser() throws SQLException{
		String TXTHTML = "";
		//SELECT * FROM dispositivo d, permissao p WHERE p.CODIDISP = d.CODIDISP AND p.CODINIVEL = 1
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state.executeQuery("SELECT u.CODIUSUA,u.NOME,u.LOGIN,n.CODINIVEL,u.ADM FROM usuario u, nivelusua n WHERE u.CODIUSUA = n.CODIUSUA");
		
		System.out.println("SELECT u.CODIUSUA,u.LOGIN,u.NOME,n.CODINIVEL,u.ADM FROM usuario u, nivelusua n WHERE u.CODIUSUA = n.CODIUSUA");
		
		while(result.next()){
			TXTHTML = TXTHTML +  "<tr>"
						+ "<td class='txt1'>"+ result.getString(1) + "</td>"
						+ "<td class='txt1'>"+ result.getString(2) + "</td>"
						+ "<td class='txt1'>"+ result.getString(3) + "</td>"
						+ "<td class='txt1'>"+ result.getString(4) + "</td>";
			if(result.getInt(5)==1){
				
				TXTHTML = TXTHTML + "<td class='txt1'> Sim </td>";
			}else{
				TXTHTML = TXTHTML + "<td class='txt1'> Não </td>";
			}
			TXTHTML = TXTHTML + 
					 "<td class='txt1'><a href=\"usuarioform.jsp?cod="+result.getString(1)+"\"><img src=\"img/edit.png\">"
					+ "</a><a href=\"usuariodel.jsp?cod="+result.getString(1)+"\"><img src=\"img/del.png\"></a></td></tr>";
						
		}
		if(TXTHTML != ""){
			TXTHTML = "<table class='bordasimples' border=1 width='600'>"
					+ "<tr>"
					+ "<td class='txt1'><b>Codigo</b></td>"
					+ "<td class='txt1'><b>Nome</b></td>"
					+ "<td class='txt1'><b>Login</b></td>"
					+ "<td class='txt1'><b>Nível</b></td>"
					+ "<td class='txt1'><b>Acesso Adm.</b></td>"
					+ "<td class='txt1'><b>Opções.</b></td>"
					+ "</tr>"
					+ TXTHTML
					+ "</table>";				
			
		}
		
		
		return TXTHTML;
		
		
	}
	
	public Boolean VerificaLogin() throws SQLException{
			
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state.executeQuery("select CODIUSUA,NOME,LOGIN,SENHA from USUARIO where LOGIN = '"
				+ this.Login + "' and senha = MD5('" + Senha + "')");
		String a = "select CODIUSUA,NOME,LOGIN,SENHA from USUARIO where LOGIN = '"
				+ this.Login.toUpperCase() + "' and senha = MD5('" + Senha + "')";
		
		System.out.println(a);
		//System.out.println("select tipo from pessoa where usuario = '"
			//	+ usuario + "' and senha = MD5('" + senha + "')");
		
		if (result.next()){
			//System.out.println("AXI");
			this.Codigo = result.getInt(1);
			this.Nome = result.getString(2);
			//conn.fecharConexao();	
			return true;
		} else {
			//conn.fecharConexao();
			return false;
		}	
		
		//return true;
		
	}
	public Boolean VerificaExisteLoginCod() throws SQLException{
		
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state.executeQuery("select CODIUSUA,NOME,LOGIN,SENHA from USUARIO where LOGIN = UPPER('"
				+ this.getLogin() + "')" +
				" and codiusua <> "+ this.getCodigo());
		String a = "select CODIUSUA,NOME,LOGIN,SENHA from USUARIO where LOGIN = UPPER('"
				+ this.getLogin() + "')" +
				" and codiusua <> "+ this.getCodigo();
	
		System.out.println(a);
		
		if (result.next()){			
			return true;
		} else {
			return false;
		}	
	
	}
	
	public Boolean VerificaExisteLogin() throws SQLException{
		
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state.executeQuery("select CODIUSUA,NOME,LOGIN,SENHA from USUARIO where LOGIN = UPPER('"
				+ this.getLogin() + "')");
		String a = "select CODIUSUA,NOME,LOGIN,SENHA from USUARIO where LOGIN = UPPER('"
				+ this.getLogin() + "')";
	
		System.out.println(a);
		
		if (result.next()){			
			return true;
		} else {
			return false;
		}	
	
	}
	
	public void GRAVA() throws IOException, SQLException  {
		Conexao conn = new Conexao();
		Statement state = conn.abrirConexao();
		this.UsuarioID();
		System.out.println("INSERT INTO usuario(codiusua,nome,login,senha) VALUES ("+ 
				+ this.getCodigo() + ",'"
				+ this.getNome() + "','"
				+ this.getLogin()+"',md5('"
				+ this.getSenha()+"'))");
		state.execute("INSERT INTO usuario(codiusua,nome,login,senha) VALUES ("+ 
				+ this.getCodigo() + ",'"
				+ this.getNome() + "','"
				+ this.getLogin()+"',md5('"
				+ this.getSenha()+"'))");
		System.out.println("INSERT INTO nivelusua VALUES ("+ 
		+ this.getNivel() + ","
		+ this.getCodigo()+")");
		state.execute("INSERT INTO nivelusua VALUES ("+ 
				+ this.getNivel() + ","
				+ this.getCodigo()+")");	
		
	}
	
	public void ALTERA() throws IOException, SQLException  {
		Conexao conn = new Conexao();
		Statement state = conn.abrirConexao();
		System.out.println("update usuario set " +
				"nome = '"+ this.getNome()+
				"',login = '"+ this.getLogin()+
				"' where codiusua = " + this.getCodigo());
		state.execute("update usuario set " +
				"nome = '"+ this.getNome()+
				"',login = '"+ this.getLogin()+
				"' where codiusua = " + this.getCodigo());
		System.out.println("DELETE from nivelusua WHERE CODIUSUA = "+ 
				+ this.getCodigo());
		state.execute("DELETE from nivelusua WHERE CODIUSUA = "+ 
				+ this.getCodigo());
		
		System.out.println("INSERT INTO nivelusua VALUES ("+ 
				+ this.getNivel() + ","
				+ this.getCodigo()+")");
		state.execute("INSERT INTO nivelusua VALUES ("+ 
						+ this.getNivel() + ","
						+ this.getCodigo()+")");	
	}
	
	public void DELETA() throws IOException, SQLException  {
		Conexao conn = new Conexao();
		Statement state = conn.abrirConexao();
		System.out.println("delete from nivelusua  " +
				" where codiusua = " + this.getCodigo());
		state.execute("delete from nivelusua  " +
				" where codiusua = " + this.getCodigo());	
		System.out.println("delete from usuario  " +
				" where codiusua = " + this.getCodigo());
		state.execute("delete from usuario  " +
				" where codiusua = " + this.getCodigo());	
		
	}
	
	
	
	public void UsuarioID() throws SQLException{
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		System.out.println("SELECT max(u.CODIUSUA)FROM usuario u");
		result = state.executeQuery("SELECT max(u.CODIUSUA)FROM usuario u");
		
		if (result.next()){
			//System.out.println("AXI");
			this.Codigo = result.getInt(1) + 1;
		}		
		
	}
	
	public Boolean CarregaUsuarioID(Integer codigo) throws SQLException{
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state.executeQuery("SELECT u.CODIUSUA,u.LOGIN,u.NOME,n.CODINIVEL,u.ADM FROM usuario u, nivelusua n "
				+ "WHERE u.CODIUSUA = n.CODIUSUA and u.CODIUSUA = "+ codigo);
		
		System.out.println("SELECT u.CODIUSUA,u.LOGIN,u.NOME,n.CODINIVEL,u.ADM FROM usuario u, nivelusua n "
				+ "WHERE u.CODIUSUA = n.CODIUSUA and u.CODIUSUA = "+ codigo);
		
		if (result.next()){
			//System.out.println("AXI");
			this.Codigo = result.getInt(1);
			this.Login = result.getString(2);			
			this.Nome = result.getString(3);
			this.Nivel = result.getInt(4);
			if (result.getInt(5) == 1){
			  this.Adm = true;
			}else{
			  this.Adm = false;
			}
				  
			//conn.fecharConexao();
			return true;
		} else {
			//conn.fecharConexao();
			return false;
		}		
		
	}

	
    
	public Boolean getAdm() {
		return Adm;
	}

	public void setAdm(Boolean adm) {
		Adm = adm;
	}

	public Integer getNivel() {
		return Nivel;
	}

	public void setNivel(Integer nivel) {
		Nivel = nivel;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public Integer getCodigo() {
		return Codigo;
	}

	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}
	
	
}
