import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientTCP5 {

public static void main (String[] args) throws Exception {
		
		String host = "localhost";
		String msj="";
		int port = 60000;//Port remot
		Socket client = new Socket(host, port);
		String cadena, eco = "";
		
		//FLUX DE SORTIDA AL SERVIDOR
		PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);
		
		//FLUX D'ENTRADA AL SERVIDOR
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		//FLUX PER A ENTRADA ESTÀNDARD
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// mensaje al conectarse al servidor
		msj = fentrada.readLine();
		System.out.println(msj);
				
		System.out.println("Introdueix la cadena: ");
		//Lectura teclat
		cadena = in.readLine();
		
		while (cadena != null) {
			//Enviament cadena al servidor
			fsortida.println(cadena);
			//Rebuda cadena del servidor
			msj = fentrada.readLine();
			System.out.println("  =>ECO: "+msj);
			//Lectura del teclat
			cadena = in.readLine();
			
		}
		fsortida.close();
		fentrada.close();
		System.out.println("Finalització de l'enviament...");
		in.close();
		client.close();
	}
}
