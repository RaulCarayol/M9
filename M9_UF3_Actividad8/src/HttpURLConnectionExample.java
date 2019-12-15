
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
//El programa demana si vol fer servir el mètode GET o PUT.
//Després aquest paràmetre el programa demana 
//la resta de paràmetres per consultar el codi de la URL que també li passa com a paràmetres.
public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {
		Scanner teclado = new Scanner(System.in);
		boolean finPorgrama = false;
		String metodo;
		while(!finPorgrama){
			System.out.println("Que Metodo quieres (Escribe get para GET o put para PUT )");
			metodo = teclado.nextLine();
			while(! (metodo.equalsIgnoreCase("get") || metodo.equalsIgnoreCase("put") || metodo.equalsIgnoreCase("post"))){
				System.out.println("Escoge una opcion de las dos");
				System.out.println("Que Metodo quieres (Escribe get para GET o put para POST )");
				metodo = teclado.nextLine();
			}
			if(metodo.equalsIgnoreCase("put") || metodo.equalsIgnoreCase("post")){
				System.out.println("\n( P O S T )");
				System.out.println("Escribe User-Agent (ej: HTTP/1.0, Mozilla/5.0)");
				String userAgent = teclado.nextLine();		

				System.out.println("Escribe URL: ");
				String url = teclado.nextLine();
				System.out.println("Escribe formato lengua (ej: UTF-8, ca-es)");
				String idioma = teclado.nextLine();	

				System.out.println("Escribe URL Parameters: ");
				String urlParameters = teclado.nextLine();
				sendPost(url,userAgent,urlParameters,idioma);
			}else{
				System.out.println("\n( G E T )");
				System.out.println("Escribe User-Agent (ej: HTTP/1.0, Mozilla/5.0)");
				String userAgent = teclado.nextLine();		

				System.out.println("Escribe URL: ");
				String url = teclado.nextLine();
				sendGet(url,userAgent);
			}
			System.out.println("Quieres continuar ?(S-si,N-no)");
			metodo = teclado.next();
			if(metodo.equalsIgnoreCase("N") || metodo.equalsIgnoreCase("no")){
				finPorgrama=true;
			}
		}

		teclado.close();
	}

	// HTTP GET request
	private static void sendGet(String url,String userAgent) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", userAgent);

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
	private static void sendPost(String url,String userAgent,String urlParameters,String idioma) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", userAgent);
		con.setRequestProperty("Accept-Language", idioma);
		
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

