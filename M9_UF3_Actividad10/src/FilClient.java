import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FilClient implements Runnable {
	Socket socket;
	String nombreCliente,cadena;
	PrintWriter fsortida;
	BufferedReader fentrada;
	private BufferedReader reader;
    private Client client;
 
    public FilClient(Socket socket,BufferedReader fentrada) throws IOException {
        this.socket = socket;
        this.fentrada = fentrada;
    }
 
    public void run() {
    	boolean inicio = true;
        while (inicio) {
            try {
            	//codigo = "0_jose_hola que tal"
            	String codigo = fentrada.readLine();
            	
                System.out.println("\n" + codigo);
                /*
                // prints the username after displaying the server's message
                if (client.getUserName() != null) {
                    System.out.print("[" + client.getUserName() + "]: ");
                }
                */
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                inicio = false;
            }
            
            }
        }
    }
