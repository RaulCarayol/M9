
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
//El programa demana si vol fer servir el m�tode GET o PUT.
//Despr�s aquest par�metre el programa demana 
//la resta de par�metres per consultar el codi de la URL que tamb� li passa com a par�metres.
public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {
		Scanner teclado = new Scanner(System.in);
		boolean finPorgrama = false;
		String metodo;
		while(!finPorgrama){
			System.out.println("Que Metodo quieres (Escribe get para GET o put para PUT )");
			metodo = teclado.next();
			while(! (metodo.equalsIgnoreCase("get") || metodo.equalsIgnoreCase("put") || metodo.equalsIgnoreCase("post"))){
				System.out.println("Escoge una opcion de las dos");
				System.out.println("Que Metodo quieres (Escribe get para GET o put para PUT )");
				metodo = teclado.next();
			}
			if(metodo.equalsIgnoreCase("put") || metodo.equalsIgnoreCase("post")){
				
			}
		}
		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
		
		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();
		teclado.close();
	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "http://www.insbaixcamp.org/";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "http://www.insbaixcamp.cat/moodle/";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "ca-es");
		
		//Query string
		String urlParameters = "categoryid=7";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}

}
