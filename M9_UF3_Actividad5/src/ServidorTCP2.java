import java.net.*;
import java.io.*;

public class ServidorTCP2 {
	
	public static void main (String[] args) throws Exception {
		
		int numPort = 60000;
		
		DatagramSocket serverSocket = new DatagramSocket(9800);
		byte[] rebuts = new byte[1024];
		byte[] enviats = new byte[1024];

		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";
		
		System.out.println("Esperant connexió... ");
		Socket clientConnectat = servidor.accept();
		System.out.println("Client connectat... ");
		
		//FLUX DE SORTIDA AL CLIENT
		PrintWriter fsortida = new PrintWriter(clientConnectat.getOutputStream(), true);
		
		
		//FLUX D'ENTRADA DEL CLIENT
		Conexion[] clientes = new Conexion[3];
		int conexiones=0;
		boolean nuevaConexion=true;
		int actualConexion = 0;
		
		while (true) {
			//Conexion
			if(conexiones<=3){
				System.out.println("Esperant datagrama... ");
				//REBUT DATAGRAMA
				rebuts = new byte[1024];
				
				DatagramPacket paqRebuts = new DatagramPacket(rebuts, rebuts.length);
				serverSocket.receive(paqRebuts);
				cadena = new String(paqRebuts.getData());
				
				InetAddress IPConexion = paqRebuts.getAddress();
				int portConexio = paqRebuts.getPort();
				for (int i = 0; i < conexiones; i++) {
					if(IPConexion == clientes[i].getDireccion() &&
							portConexio == clientes[i].getPuerto()){
						nuevaConexion=false;
						actualConexion=i;
					}else{
						//nuevaConexion=true;
					}
				}
				if(nuevaConexion){
				clientes[conexiones]=new Conexion("cliente "+(conexiones+1), IPConexion, portConexio);
				actualConexion=conexiones;
				conexiones++;
				}
				
				System.out.println("\nCliente: " + clientes[actualConexion]);
				System.out.println("\tMissatge rebut: "+cadena.trim());
				
				//ADREÇA ORIGEN
				//InetAddress IPOrigen = paqRebuts.getAddress();
				//int port = paqRebuts.getPort();
								
				//CONVERTIR CADENA A MAJÚSCULA
				String majuscula = cadena.trim().toUpperCase();
				enviats = majuscula.getBytes();
				
				//ENVIAMENT DATAGRAMA AL CLIENT
				DatagramPacket paqEnviat = new DatagramPacket(enviats, enviats.length,
						clientes[actualConexion].getDireccion(), clientes[actualConexion].getPuerto());
				serverSocket.send(paqEnviat);
				
				//Per acabar
				if (cadena.trim().equals("*")) break;
			}
			
	BufferedReader fentrada = new BufferedReader(new InputStreamReader(clientConnectat.getInputStream()));
		
		while ((cadena = fentrada.readLine()) != null) {
			
			fsortida.println(cadena);
			System.out.println("Rebent: "+cadena);
			if (cadena.equals("*")) break;	
		}
		//TANCAR STREAMS I SOCKETS
		System.out.println("Tancant connexió... ");
		fentrada.close();
		fsortida.close();
		clientConnectat.close();
		servidor.close();
		
	}
	}
}


