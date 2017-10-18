package analise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

import conexao.Conexao;

public class Analises {
	public String Titulo,V1,V2,V3,V4,V_2,V_3,V_1;
	
	
	 
	public String getV_2() {
		return V_2;
	}

	public void setV_2(String v_2) {
		V_2 = v_2;
	}

	public String getV_3() {
		return V_3;
	}

	public void setV_3(String v_3) {
		V_3 = v_3;
	}

	public String getV_1() {
		return V_1;
	}

	public void setV_1(String v_1) {
		V_1 = v_1;
	}

	public String getV2() {
		return V2;
	}

	public void setV2(String v2) {
		V2 = v2;
	}

	public String getV3() {
		return V3;
	}

	public void setV3(String v3) {
		V3 = v3;
	}

	public String getV4() {
		return V4;
	}

	public void setV4(String v4) {
		V4 = v4;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getV1() {
		return V1;
	}

	public void setV1(String v1) {
		V1 = v1;
	}

	public String AnaliseResumida(Date pinicio,Date pfim ) throws SQLException, ParseException{
		String TXTHTML = "";
		//SELECT * FROM dispositivo d, permissao p WHERE p.CODIDISP = d.CODIDISP AND p.CODINIVEL = 1
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(pfim);
		c.add(Calendar.DATE,1);
		pfim = c.getTime();
		
		
		
		System.out.println("SELECT 	LB.CODIDISP,LB.DESCRICAO,"+
				" CAST(SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"TOTAL_HORAS_LIGADO\", "+
				" CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"MEDIA_HORAS_LIGADO\","+
				" CAST(SEC_TO_TIME(MAX(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"MAIOR_PERIODO_HORAS_LIGADO\","+
				" CAST(SEC_TO_TIME(MIN(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"MENOR_PERIODO_HORAS_LIGADO\","+
				" CAST((SELECT COUNT(1) FROM log_acionamento LC WHERE LC.CODIDISP = LB.CODIDISP AND LC.ACAO = 1 AND LC.DATAHORA>='2013-09-01' AND LC.DATAHORA<='2013-09-14') as CHAR)\"TOTAL_ACIONAMENTO\","+
				" CAST(((SUM(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))*100)/(TIME_TO_SEC(TIMEDIFF('"+sdf1.format(pfim)+" 00:00:00','"+sdf1.format(pinicio)+" 00:00:00')))) AS CHAR)\"PORCENTAGEM_LIGADO\" ,"+
				"(SUM(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))),"+
				" AVG(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))"+
				" FROM (SELECT 	L.CODIDISP,D.DESCRICAO,"+
				" L.DATAHORA \"ACIONOU\","+	
				" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA  "+
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) IS NULL THEN NOW() "+
				" ELSE (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) END AS \"DESLIGOU\" "+   
				" FROM log_acionamento L,dispositivo D "+
				" WHERE L.CODIDISP = D.CODIDISP "+
				" AND L.DATAHORA >= '"+ sdf1.format(pinicio) +"'"+
				" AND L.DATAHORA <= '"+ sdf1.format(pfim) +"'"+
				" AND L.ACAO = 1) LB "+
				" GROUP BY LB.CODIDISP,LB.DESCRICAO");
		  
		
		result = state.executeQuery("SELECT 	LB.CODIDISP,LB.DESCRICAO,"+		
				" CAST(SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"TOTAL_HORAS_LIGADO\", "+
				" CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"MEDIA_HORAS_LIGADO\","+
				" CAST(SEC_TO_TIME(MAX(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"MAIOR_PERIODO_HORAS_LIGADO\","+
				" CAST(SEC_TO_TIME(MIN(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"MENOR_PERIODO_HORAS_LIGADO\","+
				" CAST((SELECT COUNT(1) FROM log_acionamento LC WHERE LC.CODIDISP = LB.CODIDISP AND LC.ACAO = 1 AND LC.DATAHORA>='2013-09-01' AND LC.DATAHORA<='2013-09-14') as CHAR)\"TOTAL_ACIONAMENTO\","+
				" CAST(((SUM(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))*100)/(TIME_TO_SEC(TIMEDIFF('"+sdf1.format(pfim)+" 00:00:00','"+sdf1.format(pinicio)+" 00:00:00' )))) AS CHAR)\"PORCENTAGEM_LIGADO\" , "+
				"(SUM(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))),"+
				" AVG(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))"+
				" FROM (SELECT 	L.CODIDISP,D.DESCRICAO,"+
				" L.DATAHORA \"ACIONOU\","+	
				" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA  "+
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) IS NULL THEN NOW() "+
				" ELSE (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) END AS \"DESLIGOU\" "+   
				" FROM log_acionamento L,dispositivo D "+
				" WHERE L.CODIDISP = D.CODIDISP "+
				" AND L.DATAHORA >= '"+ sdf1.format(pinicio) +"'"+
				" AND L.DATAHORA <= '"+ sdf1.format(pfim) +"'"+
				" AND L.ACAO = 1) LB "+
				" GROUP BY LB.CODIDISP,LB.DESCRICAO");
		
		
		
		Titulo = "";
		V1="";
		V2="";
		V3="";
		V_1="";
		V_2="";
		V_3="";
		
		while(result.next()){
			TXTHTML = TXTHTML +  "<tr>"
						+ "<td class='txt1'>"+ result.getString(1) + "</td>"
						+ "<td class='txt1'>"+ result.getString(2) + "</td>"
						+ "<td class='txt1'>"+ result.getString(3) + "</td>"
						+ "<td class='txt1'>"+ result.getString(4) + "</td>"
						+ "<td class='txt1'>"+ result.getString(5) + "</td>"
						+ "<td class='txt1'>"+ result.getString(6) + "</td>"
						+ "<td class='txt1'>"+ result.getString(7) + "</td>"
						+ "<td class='txt1'>"+ result.getString(8) + "</td>"
						+ "</tr>";
			Titulo = Titulo + "'"+ result.getString(2)+ "'" + ",";
			V1 = V1 + result.getString(7) + ",";
			V2 = V2 + result.getString(9) + ",";
			V3  = V3 + result.getString(10) + ",";
			
			V_1 = V_1 + "'"+result.getString(2)+ " > " +  result.getString(7) + "',";
			V_2 = V_2 + "'"+result.getString(2)+ " > "+ result.getString(3) + "',";
			V_3  = V_3 + "'"+result.getString(2)+ " > "+result.getString(4) + "',";
						
		}
		if(TXTHTML != ""){
			TXTHTML = "<table class='bordasimples' border=1 width='100%'>"
					+ "<tr>"
					+ "<td class='txt1'><b>Codigo</b></td>"
					+ "<td class='txt1'><b>Dispositivo</b></td>"
					+ "<td class='txt1'><b>Total Horas Ligado</b></td>"
					+ "<td class='txt1'><b>Média Horas Ligado</b></td>"
					+ "<td class='txt1'><b>Maior Periodo Ligado</b></td>"
					+ "<td class='txt1'><b>Menor Periodo Ligado</b></td>"
					+ "<td class='txt1'><b>Quantidade Acionamento</b></td>"
					+ "<td class='txt1'><b>Porcentagem Ligada</b></td>"
					+ "</tr>"
					+ TXTHTML
					+ "</table>";				
			
		}
		
		
		return TXTHTML;
		
		
	}
	
	public String AnaliseHistorico(Date pinicio,Date pfim ) throws SQLException, ParseException{
		String TXTHTML = "";
		//SELECT * FROM dispositivo d, permissao p WHERE p.CODIDISP = d.CODIDISP AND p.CODINIVEL = 1
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(pfim);
		c.add(Calendar.DATE,1);
		pfim = c.getTime();
		
		
		
		System.out.println("SELECT 	CAST(LB.CODIDISP AS CHAR), "+
						" CAST(LB.DESCRICAO AS CHAR),	"+
	" CAST(LB.USUARIOACIONOU AS CHAR),"+
				" CAST(LB.NOMEUSUARIOACIONOU AS CHAR),"+
				" CAST(LB.DESLIGOU AS CHAR) \"INICIO\","+
				" CAST(LB.ACIONOU AS CHAR) \"FIM\","+	
				" CAST(LB.USUARIODESLIGOU AS CHAR), 	 "+
				" CASE LB.USUARIODESLIGOU WHEN 0  THEN \"Ainda está ligado\" "+
				" 					ELSE (SELECT nome FROM usuario WHERE codiusua = LB.USUARIODESLIGOU ) END AS \"NOMEUSUARDESL\","+
				" CAST(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU) AS CHAR)\"DURACAO\""+
				" FROM (SELECT 	L.CODIDISP,D.DESCRICAO,u.nome \"NOMEUSUARIOACIONOU\",u.codiusua \"USUARIOACIONOU\","+
				"  L.DATAHORA \"ACIONOU\","+	
				" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) IS NULL THEN NOW() "+
				" ELSE (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) END AS \"DESLIGOU\" , "+
				" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) IS NULL THEN \"0\" "+
				" ELSE (SELECT la.codiusua "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2 "+
				" AND la.datahora = (SELECT MIN(LA2.DATAHORA) "+
				" FROM log_acionamento LA2 "+ 
				" WHERE LA2.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = LA2.CODIDISP "+
				" AND LA2.ACAO  = 2)) "+	 
				" END AS \"USUARIODESLIGOU\" "+ 
				" FROM log_acionamento L,dispositivo D,usuario u "+
				" WHERE L.CODIDISP = D.CODIDISP "+
				" AND u.codiusua = l.codiusua "+
				" AND L.DATAHORA >= '"+ sdf1.format(pinicio) +"'"+
				" AND L.DATAHORA <= '"+ sdf1.format(pfim) +"'"+
				" AND L.ACAO = 1) LB "+
				" ORDER BY LB.CODIDISP,LB.ACIONOU");
		
		
		result = state.executeQuery("SELECT 	CAST(LB.CODIDISP AS CHAR), "+
				" CAST(LB.DESCRICAO AS CHAR),	"+
" CAST(LB.USUARIOACIONOU AS CHAR),"+
		" CAST(LB.NOMEUSUARIOACIONOU AS CHAR),"+
		" CAST(LB.DESLIGOU AS CHAR) \"INICIO\","+
		" CAST(LB.ACIONOU AS CHAR) \"FIM\","+	
		" CAST(LB.USUARIODESLIGOU AS CHAR), 	 "+
		" CASE LB.USUARIODESLIGOU WHEN 0  THEN \"Ainda está ligado\" "+
		" 					ELSE (SELECT nome FROM usuario WHERE codiusua = LB.USUARIODESLIGOU ) END AS \"NOMEUSUARDESL\","+
		" CAST(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU) AS CHAR)\"DURACAO\""+
		" FROM (SELECT 	L.CODIDISP,D.DESCRICAO,u.nome \"NOMEUSUARIOACIONOU\",u.codiusua \"USUARIOACIONOU\","+
		"  L.DATAHORA \"ACIONOU\","+	
		" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
		" FROM log_acionamento LA "+ 
		" WHERE LA.DATAHORA >= L.DATAHORA "+
		" AND LA.CODIDISP = L.CODIDISP "+
		" AND LA.ACAO  = 2) IS NULL THEN NOW() "+
		" ELSE (SELECT MIN(LA.DATAHORA) "+
		" FROM log_acionamento LA "+ 
		" WHERE LA.DATAHORA >= L.DATAHORA "+
		" AND LA.CODIDISP = L.CODIDISP "+
		" AND LA.ACAO  = 2) END AS \"DESLIGOU\" , "+
		" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
		" FROM log_acionamento LA "+ 
		" WHERE LA.DATAHORA >= L.DATAHORA "+
		" AND LA.CODIDISP = L.CODIDISP "+
		" AND LA.ACAO  = 2) IS NULL THEN \"0\" "+
		" ELSE (SELECT la.codiusua "+
		" FROM log_acionamento LA "+ 
		" WHERE LA.CODIDISP = L.CODIDISP "+
		" AND LA.ACAO  = 2 "+
		" AND la.datahora = (SELECT MIN(LA2.DATAHORA) "+
		" FROM log_acionamento LA2 "+ 
		" WHERE LA2.DATAHORA >= L.DATAHORA "+
		" AND LA.CODIDISP = LA2.CODIDISP "+
		" AND LA2.ACAO  = 2)) "+	 
		" END AS \"USUARIODESLIGOU\" "+ 
		" FROM log_acionamento L,dispositivo D,usuario u "+
		" WHERE L.CODIDISP = D.CODIDISP "+
		" AND u.codiusua = l.codiusua "+
		" AND L.DATAHORA >='"+ sdf1.format(pinicio) +"'"+
		" AND L.DATAHORA <='"+ sdf1.format(pfim) +"'"+
		" AND L.ACAO = 1) LB "+
		" ORDER BY LB.CODIDISP,LB.ACIONOU");
		
		
		String codanteriodisp = "";
		while(result.next()){
			if(codanteriodisp.equals(result.getString(1))){
			TXTHTML = TXTHTML +  "<tr>"
						+ "<td class='txt1'>"+ result.getString(3) + "</td>"
						+ "<td class='txt1'>"+ result.getString(4) + "</td>"
						+ "<td class='txt1'>"+ result.getString(5) + "</td>"
						+ "<td class='txt1'>"+ result.getString(6) + "</td>"
						+ "<td class='txt1'>"+ result.getString(7) + "</td>"
						+ "<td class='txt1'>"+ result.getString(8) + "</td>"
						+ "<td class='txt1'>"+ result.getString(9) + "</td>"
						+ "</tr>";
			}else{
				if(codanteriodisp != ""){
					TXTHTML = TXTHTML 
							+ "<tr><td colspan='5'> </td><td align=right><b>Total:</b></td><td align=center>"+Total(pinicio, pfim, codanteriodisp)+"</td></tr>"
							+ "</table>";
				}
				codanteriodisp = result.getString(1);
				TXTHTML = TXTHTML + "<br><table class='bordasimples' border=1 width='100%'>"
						+ "<tr><td colspan='7' align=center><b><font color=red>Dispositivo -> "+result.getString(1)+" - "+ result.getString(2)+"</font></b></td></tr>"
						+ "<tr>"
						+ "<td class='txt1'><b>Cód.</b></td>"
						+ "<td class='txt1'><b>Usuário Ligou</b></td>"
						+ "<td class='txt1'><b>Inicio</b></td>"
						+ "<td class='txt1'><b>Fim</b></td>"
						+ "<td class='txt1'><b>Cód.</b></td>"
						+ "<td class='txt1'><b>Usuário Desligou</b></td>"
						+ "<td class='txt1'><b>Duração</b></td>"
						+ "</tr>";
				TXTHTML = TXTHTML +  "<tr>"
						+ "<td class='txt1'>"+ result.getString(3) + "</td>"
						+ "<td class='txt1'>"+ result.getString(4) + "</td>"
						+ "<td class='txt1'>"+ result.getString(5) + "</td>"
						+ "<td class='txt1'>"+ result.getString(6) + "</td>"
						+ "<td class='txt1'>"+ result.getString(7) + "</td>"
						+ "<td class='txt1'>"+ result.getString(8) + "</td>"
						+ "<td class='txt1'>"+ result.getString(9) + "</td>"
						+ "</tr>";
				
			}
		}
		if(codanteriodisp != ""){
			TXTHTML = TXTHTML 
					+ "<tr><td colspan='5'> </td><td align=right><b>Total:</b></td><td align=center>"+Total(pinicio, pfim, codanteriodisp)+"</td></tr>"
					+ "</table>";
		}
		/*if(TXTHTML != ""){
			TXTHTML = "<table class='bordasimples' border=1 width='100%'>"
					+ "<tr>"
					+ "<td class='txt1'><b>Codigo</b></td>"
					+ "<td class='txt1'><b>Dispositivo</b></td>"
					+ "<td class='txt1'><b>Cód.</b></td>"
					+ "<td class='txt1'><b>Usuário Ligou</b></td>"
					+ "<td class='txt1'><b>Inicio</b></td>"
					+ "<td class='txt1'><b>Fim</b></td>"
					+ "<td class='txt1'><b>Cód.</b></td>"
					+ "<td class='txt1'><b>Usuário Desligou</b></td>"
					+ "<td class='txt1'><b>Duração</b></td>"
					+ "</tr>"
					+ TXTHTML
					+ "</table>";				
			
		}*/
		
		
		return TXTHTML;
		
		
	}
	
	public String Total(Date pinicio,Date pfim,String pcodidisp ) throws SQLException, ParseException{
		String TXTHTML = "";
		//SELECT * FROM dispositivo d, permissao p WHERE p.CODIDISP = d.CODIDISP AND p.CODINIVEL = 1
		Statement state;
		ResultSet result;
		Conexao conn = new Conexao();
		state = conn.abrirConexao();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(pfim);
		c.add(Calendar.DATE,1);
		pfim = c.getTime();
		
		
		
		System.out.println("SELECT 		CAST(SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"TOTAL\" "+
				" FROM (SELECT 	L.CODIDISP,D.DESCRICAO,u.nome \"NOMEUSUARIOACIONOU\",u.codiusua \"USUARIOACIONOU\","+
				"  L.DATAHORA \"ACIONOU\","+	
				" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) IS NULL THEN NOW() "+
				" ELSE (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) END AS \"DESLIGOU\" , "+
				" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) IS NULL THEN \"0\" "+
				" ELSE (SELECT la.codiusua "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2 "+
				" AND la.datahora = (SELECT MIN(LA2.DATAHORA) "+
				" FROM log_acionamento LA2 "+ 
				" WHERE LA2.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = LA2.CODIDISP "+
				" AND LA2.ACAO  = 2)) "+	 
				" END AS \"USUARIODESLIGOU\" "+ 
				" FROM log_acionamento L,dispositivo D,usuario u "+
				" WHERE L.CODIDISP = D.CODIDISP "+
				" AND u.codiusua = l.codiusua "+
				" AND L.DATAHORA >= '"+ sdf1.format(pinicio) +"'"+
				" AND L.DATAHORA <= '"+ sdf1.format(pfim) +"'"+
				" AND L.ACAO = 1) LB "+
				" where LB.CODIDISP = "+ pcodidisp); 
				
		
		
		result = state.executeQuery("SELECT 		CAST(SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(LB.DESLIGOU,LB.ACIONOU)))) AS CHAR)\"TOTAL\" "+
				" FROM (SELECT 	L.CODIDISP,D.DESCRICAO,u.nome \"NOMEUSUARIOACIONOU\",u.codiusua \"USUARIOACIONOU\","+
				"  L.DATAHORA \"ACIONOU\","+	
				" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) IS NULL THEN NOW() "+
				" ELSE (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) END AS \"DESLIGOU\" , "+
				" CASE WHEN (SELECT MIN(LA.DATAHORA) "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2) IS NULL THEN \"0\" "+
				" ELSE (SELECT la.codiusua "+
				" FROM log_acionamento LA "+ 
				" WHERE LA.CODIDISP = L.CODIDISP "+
				" AND LA.ACAO  = 2 "+
				" AND la.datahora = (SELECT MIN(LA2.DATAHORA) "+
				" FROM log_acionamento LA2 "+ 
				" WHERE LA2.DATAHORA >= L.DATAHORA "+
				" AND LA.CODIDISP = LA2.CODIDISP "+
				" AND LA2.ACAO  = 2)) "+	 
				" END AS \"USUARIODESLIGOU\" "+ 
				" FROM log_acionamento L,dispositivo D,usuario u "+
				" WHERE L.CODIDISP = D.CODIDISP "+
				" AND u.codiusua = l.codiusua "+
				" AND L.DATAHORA >= '"+ sdf1.format(pinicio) +"'"+
				" AND L.DATAHORA <= '"+ sdf1.format(pfim) +"'"+
				" AND L.ACAO = 1) LB "+
				" where LB.CODIDISP = "+ pcodidisp);
		
		
		String rst = "0";
		while(result.next()){
			rst =  result.getString(1);
		}
		return rst;		
		
	}
	
}
