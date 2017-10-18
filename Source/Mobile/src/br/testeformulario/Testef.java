package br.testeformulario;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData.Item;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Testef extends Activity {
	EditText etNome;
	EditText etPass,etServidor;
	TextView tvResult;
	Button btLogar,btSair,btnSalvar,btnVoltar;
	Button btDisp1, btDisp2, btDisp3, btDisp4, btDisp5;
	Button btDisp6, btDisp7, btDisp8, btDisp9, btDisp10;
	String IP = "192.168.1.176";
	MenuItem Btn;
	Boolean AcionaAtualizar = false;
	
	
	String Desc1, Desc2, Desc3;
	String Port1, Port2, Port3;
	String Stat1, Stat2, Stat3;
	String Perm1, Perm2, Perm3;
	String idusuario;
	String arr[], bt1[], bt2[], bt3[], bt4[], bt5[];
	String bt6[], bt7[], bt8[], bt9[], bt10[];
	AlertDialog.Builder caixaalert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testef);
		if (android.os.Build.VERSION.SDK_INT > 9) { 
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); 
			StrictMode.setThreadPolicy(policy); 
		}
		caixaalert = new AlertDialog.Builder(this);
		etNome = (EditText) findViewById(R.form.nome);
		etPass = (EditText) findViewById(R.form.pass);
		btLogar = (Button) findViewById(R.botao.Logar);
		btLogar = (Button) findViewById(R.botao.Logar);
		tvResult = (TextView) findViewById(R.form.resultado);
		
		AcionaAtualizar = false;
		
		
		
		SharedPreferences pr = getSharedPreferences("IP",0);
		
		IP=pr.getString("IP","");
		
		
		
		
		btLogar.setOnClickListener(new View.OnClickListener() {
			

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ArrayList postParam = new ArrayList();
				postParam.add(new BasicNameValuePair("usuario", etNome
						.getText().toString()));
				postParam.add(new BasicNameValuePair("senha", etPass.getText()
						.toString()));
				postParam.add(new BasicNameValuePair("cel", "1"));
				String response = null;
				
				try {
					System.out.println("aki");
					response = CustomHttpClient.executeHttpPost(
							"http://"+ IP +":8080/Arduinotcc/login.jsp",
							postParam);
					String res = response.toString();
					res = res.replaceAll("\\s+", "");
					System.out.println("aki");
					System.out.println(res);

					if (!res.equals("0")) {
						// Global g = new Global();
						// g.setVg_user(Integer.parseInt(res));
						idusuario = res;
						// Global g2 = new Global();
						AcionaAtualizar=true;
						setContentView(R.layout.disp);
						
						MostraDisp();

						// tvResult.setText("Login correto :) =>"+res+"/"
						// +g2.getVg_user().toString());
					} else
						tvResult.setText("Hooo :(  Nome ou password incorretos");
				} catch (Exception e) {
					//tvResult.setText("Não foi Possível conectar ao Servidor:"+IP);
					
					caixaalert.setMessage("Não foi Possível conectar ao Servidor:"+IP);
					caixaalert.setTitle("ERRO");
					caixaalert.setNeutralButton("OK",null);				
					caixaalert.show();
				}
			}

		});

	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub				
		return super.onPrepareOptionsMenu(menu);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.testef,menu);
		return true;
	}

	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		if(AcionaAtualizar){
			switch(item.getItemId()){
	        case R.id.action_atualizar:
	                CarregaBotoes();
	                return true;        
	        }
		}
		switch(item.getItemId()){
        case R.id.action_settings:
        	
        	    setContentView(R.layout.config);
        	    btnSalvar = (Button) findViewById(R.botao.btnsalvar);
        	    btnVoltar = (Button)findViewById(R.botao.voltar);
        		etServidor = (EditText)findViewById(R.form.servidor);
        	    SharedPreferences pr = getSharedPreferences("IP",0);
        		etServidor.setText(pr.getString("IP",""));
        		
        		btnSalvar.setOnClickListener(new View.OnClickListener() {
        			
        			@Override
        			public void onClick(View arg0) {
        				// TODO Auto-generated method stub
        				SharedPreferences pref = getSharedPreferences("IP",MODE_PRIVATE);
        				SharedPreferences.Editor editor = pref.edit();
        				editor.putString("IP",etServidor.getText().toString());
        				
        				editor.commit();
        				IP = etServidor.getText().toString();
        				caixaalert.setMessage("IP "+ IP +" Salvo com Sucesso");
        				caixaalert.setTitle("Sucesso");
        				caixaalert.setNeutralButton("OK",null);				
        				caixaalert.show();
        				
        			}
        		});
        		btnVoltar.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
						AcionaAtualizar = false;
						Testef.this.onCreate(null);
					}
				});
        		
                return true;        
        }
		
		
		return super.onOptionsItemSelected(item);

	}

	public String InfoDisp(String Codigo, String user) {
		 
		String txt = "";
		
		System.out.println("InfoDisp = " + Codigo + "/ " + user);
		ArrayList postParam = new ArrayList();
		postParam.add(new BasicNameValuePair("id", Codigo));
		postParam.add(new BasicNameValuePair("user", user));
		String response = null;
		//AlertDialog.Builder caixaalert = new AlertDialog.Builder(this);
		try {
			System.out.println("InfoDisp = " + Codigo + "/ " + user);
			response = CustomHttpClient.executeHttpPost(
					"http://"+ IP +":8080/Arduinotcc/cel/infodisp.jsp",
					postParam);
			String res = response.toString();
			res = res.replaceAll("\\s+", "");
			// System.out.println("aki");
			System.out.println(res);

			if (!res.equals("0")) {
				// g.setVg_user(Integer.parseInt(res));
				// Global g2 = new Global();
				txt = res;

				// tvResult.setText("Login correto :) =>"+res+"/"
				// +g2.getVg_user().toString());
			} else
				txt = "0;0;0;0";
		} catch (Exception e) {
			//etNome.setText(e.toString());
			caixaalert.setMessage("Erro ao Receber InfoDisp");
			caixaalert.setTitle("ERRO");
			caixaalert.setNeutralButton("OK",null);				
			caixaalert.show();
		}

		return txt;
		
	
	}
	public void InfoDisp2(String user) {
	    int i;	 
		String txt = "";		
		ArrayList postParam = new ArrayList();		
		postParam.add(new BasicNameValuePair("user", user));
		String response = null;
		//AlertDialog.Builder caixaalert = new AlertDialog.Builder(this);
		try {
			System.out.println("InfoDisp = " + user);
			response = CustomHttpClient.executeHttpPost(
					"http://"+ IP +":8080/Arduinotcc/cel/infodisp2.jsp",
					postParam);
			String res = response.toString();
			res = res.replaceAll("\\s+", "");
//			res = res.getBytes();
			// System.out.println("aki");
			System.out.println(res);

			if (!res.equals("0")) {
				// g.setVg_user(Integer.parseInt(res));
				// Global g2 = new Global();
				txt = res;
				arr = txt.split("#");
				for (i=0;i < 10;i++ ){
		            
		            if (i < arr.length){
		            	txt = arr[i];	
		            	
		            }else{
		            	txt = "0;0;0;0";
		            }
			            switch(i){
			            case 0:bt1 = txt.split(Pattern.quote(";"));
		            	break;
		            case 1:bt2 = txt.split(Pattern.quote(";"));
	            	break;
		            case 2:bt3 = txt.split(Pattern.quote(";"));
	            	break;
		            case 3:bt4 = txt.split(Pattern.quote(";"));
	            	break;
		            case 4:bt5 = txt.split(Pattern.quote(";"));
	            	break;
		            case 5:bt6 = txt.split(Pattern.quote(";"));
	            	break;
		            case 6:bt7 = txt.split(Pattern.quote(";"));
	            	break;
		            case 7:bt8 = txt.split(Pattern.quote(";"));
	            	break;
		            case 8:bt9 = txt.split(Pattern.quote(";"));
	            	break;
		            case 9:bt10 = txt.split(Pattern.quote(";"));
	            	break;	            
			            }
		            
				}
				

				// tvResult.setText("Login correto :) =>"+res+"/"
				// +g2.getVg_user().toString());
			}
		} catch (Exception e) {
			//etNome.setText(e.toString());
			caixaalert.setMessage("Erro ao Receber InfoDisp2");
			caixaalert.setTitle("ERRO");
			caixaalert.setNeutralButton("OK",null);				
			caixaalert.show();
		}

		
		
	
	}
	public void AcionaDisp(String Codigo, String user,String Liga) {
		
		ArrayList postParam = new ArrayList();
		postParam.add(new BasicNameValuePair("id", Codigo));
		postParam.add(new BasicNameValuePair("codus", user));
		String response = null;
		//AlertDialog.Builder caixaalert = new AlertDialog.Builder(this);
		if(Liga.equals("0")){
			try {
				System.out.println("InfoDisp = " + Codigo + "/ " + user);
				response = CustomHttpClient.executeHttpPost(
						"http://"+ IP +":8080/Arduinotcc/cel/liga.jsp",
						postParam);			
				caixaalert.setMessage("Dispostivo "+ Codigo + " - Ligado com sucesso");
				caixaalert.setTitle("Acao");
				caixaalert.setNeutralButton("OK",null);				
				caixaalert.show();
			} catch (Exception e) {
				caixaalert.setMessage("Erro ao Enviar Solicitação");
				caixaalert.setTitle("ERRO");
				caixaalert.setNeutralButton("OK",null);				
				caixaalert.show();
				//etNome.setText(e.toString());
			}
		}else{
			try {
				System.out.println("InfoDisp = " + Codigo + "/ " + user);
				response = CustomHttpClient.executeHttpPost(
						"http://"+ IP +":8080/Arduinotcc/cel/desliga.jsp",
						postParam);			
				System.out.println("Desligar");
				caixaalert.setMessage("Dispostivo "+ Codigo + " - Desligado com sucesso");
				caixaalert.setTitle("Acao");
				caixaalert.setNeutralButton("OK",null);
				caixaalert.show();
			} catch (Exception e) {
				//etNome.setText(e.toString());
				caixaalert.setMessage("Erro ao Enviar Solicitação");
				caixaalert.setTitle("ERRO");
				caixaalert.setNeutralButton("OK",null);				
				caixaalert.show();
			}
			
		}
		
		AcionaAtualizar=true;
		MostraDisp();
		

	}

	public void CarregaBotoes() {
		
		//Btn.setEnabled(true);
		System.out.println("DISP 1 ");
		String res, temp1;
		System.out.println("DISP 1 " + idusuario);
	    InfoDisp2(idusuario);

		if (bt1[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";

		System.out.println(bt1[1]);
		System.out.println(bt1[2]);
		System.out.println(bt1[3]);
		btDisp1.setText(bt1[0].toString() + temp1);
		btDisp1.setEnabled((bt1[3].toString().equals("1")));

		System.out.println("DISP 2 ");
//		res = InfoDisp("2", idusuario);
//		bt2 = res.split(Pattern.quote(";"));
		if (bt2[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";
		btDisp2.setText(bt2[0].toString() + temp1);
		btDisp2.setEnabled((bt2[3].toString().equals("1")));

		System.out.println("DISP 3 ");
//		res = InfoDisp("3", idusuario);
//		bt3 = res.split(Pattern.quote(";"));
		if (bt3[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";
		btDisp3.setText(bt3[0].toString() + temp1);
		btDisp3.setEnabled((bt3[3].toString().equals("1")));

//		res = InfoDisp("4", idusuario);
//		bt4 = res.split(Pattern.quote(";"));
		if (bt4[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";
		btDisp4.setText(bt4[0].toString() + temp1);
		btDisp4.setEnabled((bt4[3].toString().equals("1")));

		//res = InfoDisp("5", idusuario);
		//bt5 = res.split(Pattern.quote(";"));
		if (bt5[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";
		btDisp5.setText(bt5[0].toString() + temp1);
		btDisp5.setEnabled((bt5[3].toString().equals("1")));

		//res = InfoDisp("6", idusuario);
		//bt6 = res.split(Pattern.quote(";"));
		if (bt6[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";
		btDisp6.setText(bt6[0].toString() + temp1);
		btDisp6.setEnabled((bt6[3].toString().equals("1")));

		//res = InfoDisp("7", idusuario);
		//bt7 = res.split(Pattern.quote(";"));
		if (bt7[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";
		btDisp7.setText(bt7[0].toString() + temp1);
		btDisp7.setEnabled((bt7[3].toString().equals("1")));

		//res = InfoDisp("8", idusuario);
		//bt8 = res.split(Pattern.quote(";"));
		if (bt8[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";
		btDisp8.setText(bt8[0].toString() + temp1);
		btDisp8.setEnabled((bt8[3].toString().equals("1")));

		//res = InfoDisp("9", idusuario);
		//bt9 = res.split(Pattern.quote(";"));
		if (bt9[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";
		btDisp9.setText(bt9[0].toString() + temp1);
		btDisp9.setEnabled((bt9[3].toString().equals("1")));

		//res = InfoDisp("10", idusuario);
		//bt10 = res.split(Pattern.quote(";"));
		if (bt10[2].equals("0")) {
			temp1 = " - Ligar";
		} else
			temp1 = " - Desligar";
		btDisp10.setText(bt10[0].toString() + temp1);
		btDisp10.setEnabled((bt10[3].toString().equals("1")));

		if (bt1[0].toString().equals("0")) {
			btDisp1.setVisibility(View.INVISIBLE);
			// btDisp1.set
		} else {
			btDisp1.setVisibility(View.VISIBLE);

		}
		if (bt3[0].toString().equals("0")) {
			btDisp3.setVisibility(View.INVISIBLE);
		} else {
			btDisp3.setVisibility(View.VISIBLE);
		}

		if (bt4[0].toString().equals("0")) {
			btDisp4.setVisibility(View.INVISIBLE);
		} else {
			btDisp4.setVisibility(View.VISIBLE);
		}

		if (bt5[0].toString().equals("0")) {
			btDisp5.setVisibility(View.INVISIBLE);
		} else {
			btDisp5.setVisibility(View.VISIBLE);
		}

		if (bt6[0].toString().equals("0")) {
			btDisp6.setVisibility(View.INVISIBLE);
		} else {
			btDisp6.setVisibility(View.VISIBLE);
		}

		if (bt7[0].toString().equals("0")) {
			btDisp7.setVisibility(View.INVISIBLE);
		} else {
			btDisp7.setVisibility(View.VISIBLE);
		}

		if (bt8[0].toString().equals("0")) {
			btDisp8.setVisibility(View.INVISIBLE);
		} else {
			btDisp8.setVisibility(View.VISIBLE);
		}

		if (bt9[0].toString().equals("0")) {
			btDisp9.setVisibility(View.INVISIBLE);
		} else {
			btDisp9.setVisibility(View.VISIBLE);
		}

		if (bt10[0].toString().equals("0")) {
			btDisp10.setVisibility(View.INVISIBLE);
		} else {
			btDisp10.setVisibility(View.VISIBLE);
		}
		//btLogar.setOnClickListener(new View.OnClickListener() {

		btDisp1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AcionaDisp("1", idusuario, bt1[2]);				
			}
		});
		btDisp2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AcionaDisp("2", idusuario, bt2[2]);				
			}
		});
		btDisp3.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) { 
			// TODO Auto-generated method stub 
			AcionaDisp("3", idusuario, bt3[2]); 
			} 
			});


			btDisp4.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) { 
			// TODO Auto-generated method stub 
			AcionaDisp("4", idusuario, bt4[2]); 
			} 
			});


			btDisp5.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) { 
			// TODO Auto-generated method stub 
			AcionaDisp("5", idusuario, bt5[2]); 
			} 
			});


			btDisp6.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) { 
			// TODO Auto-generated method stub 
			AcionaDisp("6", idusuario, bt6[2]); 
			} 
			});


			btDisp7.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) { 
			// TODO Auto-generated method stub 
			AcionaDisp("7", idusuario, bt7[2]); 
			} 
			});


			btDisp8.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) { 
			// TODO Auto-generated method stub 
			AcionaDisp("8", idusuario, bt8[2]); 
			} 
			});


			btDisp9.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) { 
			// TODO Auto-generated method stub 
			AcionaDisp("9", idusuario, bt9[2]); 
			} 
			});


			btDisp10.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) { 
			// TODO Auto-generated method stub 
			AcionaDisp("10", idusuario, bt10[2]); 
			} 
			});
			
			btSair.setOnClickListener(new View.OnClickListener() {
				@Override 
				public void onClick(View v) { 
				// TODO Auto-generated method stub 
				//setContentView(R.layout.activity_testef);
					AcionaAtualizar = false;
					Testef.this.onCreate(null);
					//Btn.setEnabled(false);
				} 
				});
		
		

	}

	public void MostraDisp() {
		System.out.println("CarregaBotoes() ");
		// setContentView(R.layout.disp);

		btDisp1 = (Button) findViewById(R.botao.disp1);
		btDisp2 = (Button) findViewById(R.botao.disp2);
		btDisp3 = (Button) findViewById(R.botao.disp3);

		btDisp4 = (Button) findViewById(R.botao.disp4);
		btDisp5 = (Button) findViewById(R.botao.disp5);
		btDisp6 = (Button) findViewById(R.botao.disp6);

		btDisp7 = (Button) findViewById(R.botao.disp7);
		btDisp8 = (Button) findViewById(R.botao.disp8);
		btDisp9 = (Button) findViewById(R.botao.disp9);
		btSair = (Button) findViewById(R.botao.sair);

		btDisp10 = (Button) findViewById(R.botao.disp10);
		
       // Btn = (MenuItem)findViewById(R.id.action_atualizar);
		//Btn.setEnabled(true);
		CarregaBotoes();
		System.out.println("SetContent");

	}

}
