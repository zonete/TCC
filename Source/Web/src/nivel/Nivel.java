package nivel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.Conexao;

public class Nivel {
	
	public String ListaNiveis(Integer Codigo) throws SQLException{
		String TXTHTML = "";
		//SELECT * FROM dispositivo d, permissao p WHERE p.CODIDISP = d.CODIDISP AND p.CODINIVEL = 1
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		result = state.executeQuery("SELECT codinivel, descricao from nivel");
		
		System.out.println("SELECT codinivel, descricao from nivel");
		
		while(result.next()){
			if (Codigo == result.getInt(1)) {
				TXTHTML = TXTHTML +  "<option value='"+ result.getString(1) +"' selected>"+ result.getString(2) + "</option>";
			}else{
				TXTHTML = TXTHTML +  "<option value='"+ result.getString(1) +"'>"+ result.getString(2) + "</option>";
			}
		}
		if(TXTHTML != ""){
			TXTHTML = "<select name='nivel'>"
					+ TXTHTML
					+ "</select>";				
			
		}		
		
		return TXTHTML;
		
		
	}
	

}
