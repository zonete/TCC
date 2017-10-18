package disp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.Conexao;
import usuario.Usuario;

;
public class Dispositivos {
	public Integer CodiDisp;
	public String DescDisp;
	public String Porta;

	public String CelListaDisp2(Integer user, Integer CodiDisp2, String desc, String port) throws SQLException {
		String Txthtml = "";

		String ligado = "0";
		String permissao = "0";

		if (CodiDisp2 != null) {
			if (this.VerificaStatusDisp(CodiDisp2)) {
				ligado = "1";
			} else {
				ligado = "0";

			}

			if (this.VerificaPermissaoDisp(CodiDisp2, user)) {
				permissao = "1";
			} else {
				permissao = "0";

			}

			Txthtml = desc + ";" + port + ";" + ligado + ";" + permissao
					+ ";";
		} else {
			Txthtml = "0;0;0;0;";

		}
		return Txthtml;

	}
	
	public String CelListaDisp(Integer user) throws SQLException {
		String Txthtml = "";

		String ligado = "0";
		String permissao = "0";

		if (CodiDisp != null) {
			if (this.VerificaStatusDisp(CodiDisp)) {
				ligado = "1";
			} else {
				ligado = "0";

			}

			if (this.VerificaPermissaoDisp(CodiDisp, user)) {
				permissao = "1";
			} else {
				permissao = "0";

			}

			Txthtml = DescDisp + ";" + Porta + ";" + ligado + ";" + permissao
					+ ";";
		} else {
			Txthtml = "0;0;0;0;";

		}
		return Txthtml;

	}

	public String ListaDispUser(Usuario user) throws SQLException {
		String TXTHTML = "";
		// SELECT * FROM dispositivo d, permissao p WHERE p.CODIDISP =
		// d.CODIDISP AND p.CODINIVEL = 1
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state
				.executeQuery("SELECT d.CODIDISP,d.DESCRICAO,d.PORTA FROM dispositivo d, permissao p WHERE p.CODIDISP = d.CODIDISP AND p.CODINIVEL = "
						+ user.getNivel());

		System.out
				.println("SELECT * FROM dispositivo d, permissao p WHERE p.CODIDISP = d.CODIDISP AND p.CODINIVEL = "
						+ user.getNivel());

		while (result.next()) {
			if (this.VerificaStatusDisp(result.getInt(1))) {
				// ligado
				TXTHTML = TXTHTML + "<tr>" + "<td class='txt1'>"
						+ result.getString(1) + "</td>" + "<td class='txt1'>"
						+ result.getString(2) + "</td>" + "<td class='txt1'>"
						+ result.getString(3) + "</td>"
						+ "<td class='txt1'><a href=\"desliga.jsp?id="
						+ result.getString(1)
						+ "\"><img src=\"img/off.png\"></a></td>" + "</tr>";

			} else {
				// desligado
				TXTHTML = TXTHTML + "<tr>" + "<td class='txt1'>"
						+ result.getString(1) + "</td>" + "<td class='txt1'>"
						+ result.getString(2) + "</td>" + "<td class='txt1'>"
						+ result.getString(3) + "</td>"
						+ "<td class='txt1'><a href=\"liga.jsp?id="
						+ result.getString(1)
						+ "\"><img src=\"img/on.png\"></a></td>" + "</tr>";

			}

		}
		if (TXTHTML != "") {
			TXTHTML = "<table class='bordasimples' border=1 width='350'>"
					+ "<tr>" + "<td class='txt1'><b>Codigo</b></td>"
					+ "<td class='txt1'><b>Dispositivo</b></td>"
					+ "<td class='txt1'><b>Porta</b></td>"
					+ "<td class='txt1'><b>Opção</b></td>" + "</tr>" + TXTHTML
					+ "</table>";

		}

		return TXTHTML;

	}
	
	public String ListaDispUser2(Usuario user) throws SQLException {
		String TXTHTML = "";
		// SELECT * FROM dispositivo d, permissao p WHERE p.CODIDISP =
		// d.CODIDISP AND p.CODINIVEL = 1
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state
				.executeQuery("SELECT d.CODIDISP,d.DESCRICAO,d.PORTA FROM dispositivo d");

		System.out
				.println("SELECT d.CODIDISP,d.DESCRICAO,d.PORTA FROM dispositivo d");
		String txt;
		while (result.next()) {
			txt = CelListaDisp2(user.Codigo, result.getInt(1), result.getString(2),result.getString(3));
			
				TXTHTML = TXTHTML + txt + "#";
		}		

		return TXTHTML;

	}

	public String ListaDisp() throws SQLException {
		String TXTHTML = "";
		// SELECT * FROM dispositivo d, permissao p WHERE p.CODIDISP =
		// d.CODIDISP AND p.CODINIVEL = 1
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state
				.executeQuery("SELECT d.CODIDISP,d.DESCRICAO,d.PORTA FROM dispositivo d");

		System.out
				.println("SELECT d.CODIDISP,d.DESCRICAO,d.PORTA FROM dispositivo d");

		while (result.next()) {
			TXTHTML = TXTHTML + "<tr>" + "<td class='txt1'>"
					+ result.getString(1) + "</td>" + "<td class='txt1'>"
					+ result.getString(2) + "</td>" + "<td class='txt1'>"
					+ result.getString(3) + "</td>"
					+ "<td class='txt1'><a href=\"dispdel.jsp?cod=" + result.getString(1)
					+ "\"><img src=\"img/del.png\"></a></td>" + "</tr>";
		}
		if (TXTHTML != "") {
			TXTHTML = "<table class='bordasimples' border=1 width='350'>"
					+ "<tr>" + "<td class='txt1'><b>Codigo</b></td>"
					+ "<td class='txt1'><b>Dispositivo</b></td>"
					+ "<td class='txt1'><b>Porta</b></td>"
					+ "<td class='txt1'><b>Opção</b></td>" + "</tr>" + TXTHTML
					+ "</table>";

		}

		return TXTHTML;

	}

	public Boolean VerificaPermissaoDisp(Integer CodigoDisp, Integer user)
			throws SQLException {

		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		System.out.println("SELECT COUNT(1) FROM permissao p , nivelusua n "
				+ "WHERE n.CODINIVEL = p.CODINIVEL " + " AND n.CODIUSUA = "
				+ user.toString() + " AND p.CODIDISP = "
				+ CodigoDisp.toString());

		result = state
				.executeQuery("SELECT COUNT(1) FROM permissao p , nivelusua n "
						+ "WHERE n.CODINIVEL = p.CODINIVEL "
						+ " AND n.CODIUSUA = " + user.toString()
						+ " AND p.CODIDISP = " + CodigoDisp.toString());

		if (result.next()) {
			if (result.getInt(1) > 0) {
				// ligado
				return true;
			} else {
				// desligado
				return false;
			}
		} else {
			// desligado
			// conn.fecharConexao();
			return false;
		}
	}

	public Boolean VerificaStatusDisp(Integer CodigoDisp) throws SQLException {

		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state
				.executeQuery("SELECT ACAO FROM log_acionamento WHERE codidisp = "
						+ CodigoDisp
						+ " AND datahora = (SELECT MAX(datahora) FROM log_acionamento WHERE CODIDISP = "
						+ CodigoDisp + ")");

		System.out
				.println("SELECT ACAO FROM log_acionamento WHERE codidisp = "
						+ CodigoDisp
						+ " AND datahora = (SELECT MAX(datahora) FROM log_acionamento WHERE CODIDISP = "
						+ CodigoDisp + ")");

		if (result.next()) {
			if (result.getInt(1) == 1) {
				// ligado
				return true;
			} else {
				// desligado
				return false;
			}
		} else {
			// desligado
			// conn.fecharConexao();
			return false;
		}
	}

	public void ALTERA(Integer codinivel) throws IOException, SQLException  {
		Conexao conn = new Conexao();
		Statement state = conn.abrirConexao();
		System.out.println("update usuario set " +
				"descricao = '"+ this.getDescDisp()+
				"',porta = '"+ this.getPorta()+
				"' where codidisp = " + this.getCodiDisp());
		state.execute("update usuario set " +
				"descricao = '"+ this.getDescDisp()+
				"',porta = '"+ this.getPorta()+
				"' where codidisp = " + this.getCodiDisp());
		
		
		
		
		System.out.println("INSERT INTO permissao VALUES ("+ 
				+ this.getCodiDisp() + ","
				+ codinivel+")");
		state.execute("INSERT INTO permissao VALUES ("+ 
				+ this.getCodiDisp() + ","
				+ codinivel+")");	
	}
	public void DispID() throws SQLException{
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		System.out.println("SELECT max(D.CODIDISP)FROM dispositivo d");
		result = state.executeQuery("SELECT max(D.CODIDISP)FROM dispositivo d");
		
		if (result.next()){
			//System.out.println("AXI");
			this.CodiDisp = result.getInt(1) + 1;
		}		
		
	}
	
	public void DELETA(Integer Codi) throws IOException, SQLException  {
		Conexao conn = new Conexao();
		Statement state = conn.abrirConexao();
		System.out.println("delete from permissao  " +
				" where codidisp = " + Codi);
		state.execute("delete from permissao  " +
				" where codidisp = " + Codi);	
		System.out.println("delete from dispositivo  " +
				" where codidisp = " + Codi);
		state.execute("delete from dispositivo  " +
				" where codidisp = " + Codi);	
		
	}
	public void GRAVA(Integer Codinivel) throws IOException, SQLException  {
		Conexao conn = new Conexao();
		Statement state = conn.abrirConexao();
		this.DispID();
		System.out.println("INSERT INTO dispositivo(codidisp,descricao,porta) VALUES ("+ 
				+ this.getCodiDisp() + ",'"
				+ this.getDescDisp() + "','"
				+ this.getPorta()+"')");
		state.execute("iNSERT INTO dispositivo(codidisp,descricao,porta) VALUES ("+ 
				+ this.getCodiDisp() + ",'"
				+ this.getDescDisp() + "','"
				+ this.getPorta()+"')");
		System.out.println("INSERT INTO permissao VALUES ("+ 
		+ this.getCodiDisp() + ","
		+ Codinivel+")");
		state.execute("INSERT INTO permissao VALUES ("+ 
				+ this.getCodiDisp() + ","
				+ Codinivel+")");	
		
	}
	public Integer getCodiDisp() {
		return CodiDisp;
	}

	public void setCodiDisp(Integer codiDisp) {
		CodiDisp = codiDisp;
	}

	public String getDescDisp() {
		return DescDisp;
	}

	public void setDescDisp(String descDisp) {
		DescDisp = descDisp;
	}

	public String getPorta() {
		return Porta;
	}

	public void setPorta(String porta) {
		Porta = porta;
	}

	public Boolean CarregaPortaID(Integer codigo) throws SQLException {
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state
				.executeQuery("SELECT codidisp,descricao,porta FROM dispositivo where codidisp = "
						+ codigo);

		System.out
				.println("SELECT codidisp,descricao,porta FROM dispositivo where codidisp = "
						+ codigo);

		if (result.next()) {
			// System.out.println("AXI");
			this.CodiDisp = result.getInt(1);
			this.DescDisp = result.getString(2);
			this.Porta = result.getString(3);
			// conn.fecharConexao();
			return true;
		} else {
			// conn.fecharConexao();
			return false;
		}

	}

}
