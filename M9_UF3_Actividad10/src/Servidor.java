import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
	String cadena = "";
	public static int conexiones=0;
	static int limiteConexiones = 3; 
	//Integer.valueOf(args[0]);
	public static String[] nombreClientes = new String[limiteConexiones];
	public static Socket[] arraySockets = new Socket[limiteConexiones];

public static void main(String[] args) throws IOException {
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		System.out.println("Numero N limite Conexiones");
		System.out.println(limiteConexiones);	
		
		PrintWriter fsortida = null;
		BufferedReader fentrada = null;
		
		while(conexiones < limiteConexiones){
			System.out.println("Esperant connexi�...");	
			Socket socket = servidor.accept();
			
			fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String nom =fentrada.readLine();
			System.out.println(nom);
			//leer nombre de usuario
			nombreClientes[conexiones] = nom;
			arraySockets[conexiones] = socket;
			
			Fil fil = new Fil(conexiones);
			Thread filThread = new Thread(fil);
			filThread.start();
			
			conexiones++;
		}
		servidor.close();
	}
}
