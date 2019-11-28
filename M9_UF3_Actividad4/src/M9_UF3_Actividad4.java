import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class M9_UF3_Actividad4 {

public static void main (String[] args) throws Exception {
		
		//ADREÇA IP i PORT ENVIAMENT DATAGRAMA
		InetAddress desti = InetAddress.getLocalHost();
		int port = 12347;
		byte[] missatge = new byte[1024];
	
		//Variable String codificada a bytes
		String salutacions = "Enviant salutacions...!! ";
		missatge = salutacions.getBytes();
		
		//CONSTRUCCIÓ DATAGRAMA PER ENVIAR
		DatagramPacket enviament = new DatagramPacket(missatge, missatge.length, desti, port);
		DatagramSocket socket = new DatagramSocket();//Port local
		System.out.println("Enviant datagrama de longuitud: "+missatge.length);
		System.out.println("Host destí: "+desti.getHostName());
		System.out.println("IP destí: "+desti.getHostAddress());
		System.out.println("Port local de socket: "+socket.getLocalPort());
		System.out.println("Port remot:"+enviament.getPort());
		
		//ENVIO DATAGRAMA
		socket.send(enviament);
		
		//TANCAMENT PORT
		socket.close();
		
	}

}
}
