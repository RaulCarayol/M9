import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Fil implements Runnable {
	String nombreCliente;
	String cadena;
	PrintWriter fsortida;
	BufferedReader fentrada;
	int numConexion;
	public Fil(int numConexion) throws IOException{
		this.nombreCliente = Servidor.nombreClientes[numConexion];
		fsortida=null;
		this.numConexion = numConexion;
		fsortida = new PrintWriter(Servidor.arraySockets[this.numConexion].getOutputStream(), true);
		System.out.println(nombreCliente+" connectat... ");
		fsortida.println(nombreCliente);
		fentrada = new BufferedReader(new InputStreamReader(Servidor.arraySockets[this.numConexion].getInputStream()));
	}

	@Override
	public void run() {
		boolean finHilo=false;
		try {
			cadena = fentrada.readLine();
			while (cadena != null && !finHilo) {
				//fsortida.println(cadena);
				enviarATodos(cadena);
				System.out.println(nombreCliente);
				System.out.println("Rebent: "+cadena);
				if (cadena.equals("*")){ 
						fentrada.close();
						fsortida.close();
						finHilo = true;
					}else{
						cadena = fentrada.readLine();
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void enviarATodos(String mensaje) throws IOException{
		System.out.println(nombreCliente+" enviando a todos...");
		for (int i = 0; i < Servidor.arraySockets.length; i++) {
			if(Servidor.arraySockets[i] != null){
				fsortida = new PrintWriter(Servidor.arraySockets[i].getOutputStream(), true);
				fsortida.println("["+nombreCliente+"]: " + mensaje);
			}
		}
	}
}
