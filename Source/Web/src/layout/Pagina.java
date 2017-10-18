package layout;

public class Pagina {
	public String Titulo = "TCC - 2013";
	
	public String Cabecalho(){		
		String TXTHTML = "";
		TXTHTML = "<html>  "
				+ "<head>"
				+ "<title>"+ Titulo  + "</title> "
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
				+ "</head>"
				+ "<body>";	
		return TXTHTML;	
	}
}
