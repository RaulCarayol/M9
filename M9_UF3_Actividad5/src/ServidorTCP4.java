import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServidorTCP4 {
	
	public static void main (String[] args) throws Exception {
		
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";
		int conexiones=0;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Numero N limite Conexiones");
		int limiteConexiones = teclado.nextInt();
		PrintWriter fsortida = null;
		BufferedReader fentrada = null;
		System.out.println("Esperant connexió... ");
		Socket clientConnectat = servidor.accept();
		System.out.println("Client connectat... ");
		while(conexiones < limiteConexiones){
			//FLUX DE SORTIDA AL CLIENT
			fsortida = new PrintWriter(clientConnectat.getOutputStream(), true);
			System.out.println("Client " + (conexiones+1));
			fsortida.println("Client " + (conexiones + 1));
			
			//FLUX D'ENTRADA DEL CLIENT
			 fentrada = new BufferedReader(new InputStreamReader(clientConnectat.getInputStream()));
			
			while ((cadena = fentrada.readLine()) != null) {
				
				fsortida.println(cadena);
				System.out.println("Rebent: "+cadena);
				if (cadena.equals("*")) break;
			}
		}
		
		//TANCAR STREAMS I SOCKETS
		System.out.println("Tancant connexió... ");
		fentrada.close();
		fsortida.close();
		clientConnectat.close();
		servidor.close();
		
	}
}
