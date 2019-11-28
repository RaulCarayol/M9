import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import org.omg.CORBA.TIMEOUT;
public class ClientUDP2 {

	public static void main (String[] args) throws Exception {
		boolean seguir = true;

		//FLUX PER A ENTRADA ESTĀNDARD
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//Socket client
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] enviats = new byte[1024];
		byte[] rebuts = new byte[1024];
		
		//DADES DEL SERVIDOR al qual s'envia el missatge
		InetAddress IPServidor = InetAddress.getLocalHost();
		int port = 9800;
		while(seguir){
		//INTRODUIR DADES PEL TECLAT
		System.out.print("Introdueix missatge: ");
		String cadena = in.readLine();
		enviats = cadena.getBytes();

		//ENVIANT DATAGRAMA AL SERVIDOR
		System.out.println("Enviant "+enviats.length+"bytes al servidor.");
		DatagramPacket enviament = new DatagramPacket(enviats, enviats.length, IPServidor, port);
		clientSocket.send(enviament);

		//REBENT DATAGRAMA DEL SERVIDOR
		DatagramPacket rebut = new DatagramPacket(rebuts, rebuts.length);
		System.out.println("Esperant datagrama...");
		clientSocket.setSoTimeout(5000);
		try{
			clientSocket.receive(rebut);
			String majuscula = new String(rebut.getData());
	
			//ACONSEGUINT INFORMACIĶ DEL DATAGRAMA
			InetAddress IPOrigen = rebut.getAddress();
			int portOrigen = rebut.getPort();
			System.out.println("\tProcedent de: "+IPOrigen+":"+portOrigen);
			System.out.println("\tDades: "+majuscula.trim());
		}catch(SocketTimeoutException e){
			  	seguir=false;
			  	System.out.println("Tiempo excedido");
			 }
		}
		//Tanca el socket
		clientSocket.close();

	}
}
