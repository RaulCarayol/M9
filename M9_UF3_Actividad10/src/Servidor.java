import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

public static void main(String[] args) throws IOException {
		
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";
		int conexiones=0;

		System.out.println("Numero N limite Conexiones");
		int limiteConexiones = Integer.valueOf(args[0]);
		System.out.println(limiteConexiones);
		
		PrintWriter fsortida = null;
		BufferedReader fentrada = null;
		
		while(conexiones < limiteConexiones){
			System.out.println("Esperant connexió...");
			
			Socket socket = servidor.accept();
			Fil fil = new Fil(socket,"Client "+(conexiones+1));
			Thread filThread = new Thread(fil);
			filThread.start();
			
			conexiones++;
		}
		servidor.close();
	}
}
