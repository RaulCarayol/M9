import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServidorTCP4 {
	
	public static void main (String[] args) throws Exception {
		
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
			Socket clientConnectat = servidor.accept();
			//FLUX DE SORTIDA AL CLIENT
			fsortida = new PrintWriter(clientConnectat.getOutputStream(), true);
			System.out.print("Client " + (conexiones+1));
			System.out.println(" connectat... ");
			fsortida.println("Client " + (conexiones + 1));
			
			//FLUX D'ENTRADA DEL CLIENT
			 fentrada = new BufferedReader(new InputStreamReader(clientConnectat.getInputStream()));
			
			while ((cadena = fentrada.readLine()) != null) {
				
				fsortida.println(cadena);
				System.out.println("Rebent: "+cadena);
				if (cadena.equals("*")){ 
					break;
					}
			}
			fentrada.close();
			fsortida.close();
			conexiones++;
		}
		servidor.close();
	}
}
