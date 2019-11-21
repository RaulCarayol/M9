import java.net.*;

public class TestInetAddress {
	
	public static void main (String[] args) {
		InetAddress dir = null;
		System.out.println("=====================================================");
		System.out.println("SORTIDA PER A LOCALHOST");
		try {
			//la direccion se recibe como argumento 
			dir = InetAddress.getByName(args[0].toString());
			//Llama al metodo provaTots de la clase
			provaTots(dir);
			
			//Array tipus InetAddress amb totes les adreces IP
			System.out.println("\tAdreces IP per a: "+dir.getHostName());
			InetAddress[] adreces = InetAddress.getAllByName(dir.getHostName());
			for (int i=0; i<adreces.length; i++) 
				System.out.println("\t\t"+adreces[i].toString());
			System.out.println("=====================================================");
			
		} catch (UnknownHostException e1) {e1.printStackTrace();}
		
	}
	//Metodo que muestra las propiedades de la direccion que se le pasa
	private static void provaTots(InetAddress dir) {
		
		InetAddress dir2;
		System.out.println("\tMètode getByName(): "+dir);
		try {
			//devuelve el nombre y la ip
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMètode getLocalHost(): "+dir2);
		} catch (UnknownHostException e) {e.printStackTrace();}
		
		//FEM SERVIR MÊTODES DE LA CLASSE
		//Muestra el nombre del dominio  
		System.out.println("\tMètode getHostName(): "+dir.getHostName());
		//Muestra la direccion
		System.out.println("\tMètode getHostAddress(): "+dir.getHostAddress());
		//Muestra el nombre y la ip
		System.out.println("\tMètode toString(): "+dir.toString());
		//Muestra el nombre del dominio completo
		System.out.println("\tMètode getCanonicalHostName(): "+dir.getCanonicalHostName());
		
	}

}


