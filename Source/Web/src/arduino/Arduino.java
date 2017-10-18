package arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;


//import classes.Statement;
import conexao.Conexao;
import usuario.Usuario;

public class Arduino {
	public Boolean arduinoconectado = true;

	CommPortIdentifier portId = null;
	Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
	SerialPort sp;
	String PortID = "COM18";
	String AppName = "Aplicacao Jtest";
	OutputStream os;
	int timeOut = 1000;

	public void abriporta() throws NoSuchPortException, PortInUseException,
			IOException {
		if (arduinoconectado) {
			sp = (SerialPort) CommPortIdentifier.getPortIdentifier(PortID)
					.open(AppName, timeOut);
			os = sp.getOutputStream();
		}
	}

	public void fechaporta() throws NoSuchPortException, PortInUseException,
			IOException {
		if (arduinoconectado) {
			os.close();
			sp.close();
		}
	}

	public void Teste() throws NoSuchPortException, PortInUseException,
			IOException, InterruptedException {
		sp = (SerialPort) CommPortIdentifier.getPortIdentifier(PortID).open(
				AppName, timeOut);
		os = sp.getOutputStream();
		String b = "BH";
		os.write(b.getBytes());
		b = "CH";
		Thread.sleep(2000);
		os.write(b.getBytes());

		b = "DH";
		Thread.sleep(2000);
		os.write(b.getBytes());

		b = "EH";
		Thread.sleep(2000);
		os.write(b.getBytes());

		b = "EL";
		Thread.sleep(1000);
		os.write(b.getBytes());

		b = "DL";
		Thread.sleep(1000);
		os.write(b.getBytes());

		b = "CL";
		Thread.sleep(1000);
		os.write(b.getBytes());

		b = "BL";
		Thread.sleep(1000);
		os.write(b.getBytes());

		os.flush();
		os.write(b.getBytes());
		os.close();
		sp.close();
	}

	public void liga(String porta, Integer codigouser, Integer codDisp)
			throws IOException, SQLException, NoSuchPortException,
			PortInUseException {
		try {
			if (arduinoconectado) {
				String b = porta + "H";
				os.write(b.getBytes());
				os.flush();
			}
			Conexao conn = new Conexao();
			Statement state = conn.abrirConexao();
			Date data = new Date(System.currentTimeMillis());
			SimpleDateFormat formatarDate = new SimpleDateFormat(
					"yyyy-MM-dd H:mm:ss");
			System.out.print(formatarDate.format(data));
			state.execute("insert into log_acionamento values ('"
					+ formatarDate.format(data) + "'," + "1,"
					+ codDisp.toString() + "," + codigouser.toString() + ")");

			// this.fechaporta();
		} finally {
			// TODO Auto-generated catch block
			this.fechaporta();
		}

	}
	public void testee()
			throws IOException, SQLException, NoSuchPortException,
			PortInUseException, InterruptedException {
		Integer i,a;
		
		try {
			i=0;
			a=1;
			String b;
			for(i=0;i<10000;i++){
			if (arduinoconectado) {
				if(a==1){
					b= "NH";
					a=0;
				}else{
					b = "NL";
					a=1;					
				}
				os.write(b.getBytes());
				
				Thread.sleep(1000);
			}
			os.flush();
			}
			
		} finally {
			// TODO Auto-generated catch block
			this.fechaporta();
		}

	}

	public void desliga(String porta, Integer codigouser, Integer codDisp)
			throws IOException, SQLException, NoSuchPortException,
			PortInUseException {
		try {
			
			if (arduinoconectado) {
				System.out.print("Arduino -> ON");
				String b = porta + "L";
				os.write(b.getBytes());
				os.flush();
			}
			Conexao conn = new Conexao();
			Statement state = conn.abrirConexao();
			Date data = new Date(System.currentTimeMillis());
			SimpleDateFormat formatarDate = new SimpleDateFormat(
					"yyyy-MM-dd H:mm:ss");
			System.out.print(formatarDate.format(data));
			state.execute("insert into log_acionamento values ('"
					+ formatarDate.format(data) + "'," + "2,"
					+ codDisp.toString() + "," + codigouser.toString() + ")");

		} finally {
			this.fechaporta();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		this.fechaporta();
		super.finalize();
	}

}
