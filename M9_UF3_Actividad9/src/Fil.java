import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Fil implements Runnable {
	Socket socket;
	String nombreCliente,cadena;
	PrintWriter fsortida;
	BufferedReader fentrada;
	public Fil(Socket socket,String nombreCliente){
		this.socket=socket;
		this.nombreCliente = nombreCliente;
		fsortida=null;
	}

	@Override
	public void run() {
		boolean finHilo=false;
		try {
			fsortida = new PrintWriter(socket.getOutputStream(), true);
			System.out.println(nombreCliente+" connectat... ");
			
			fsortida.println(nombreCliente);
			fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while ((cadena = fentrada.readLine()) != null && !finHilo) {
				fsortida.println(cadena);
				System.out.println(nombreCliente);
				System.out.println("Rebent: "+cadena);
				if (cadena.equals("*")){ 
						fentrada.close();
						fsortida.close();
						finHilo = true;
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
