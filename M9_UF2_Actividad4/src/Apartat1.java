import java.awt.List;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Cliente{
	String nombre;
	int[] articulos;

	public Cliente(String nombre, int[] articulos){
		this.nombre= nombre;
		this.articulos = articulos;
	}
}
public class Apartat1 implements Runnable{
	Cliente cliente;
	public Apartat1(Cliente cliente){
		this.cliente=cliente;
	}

	@Override
	public void run() {
		System.out.print("Generando el " + cliente.nombre + " con " + cliente.articulos.length + " artículos ");
		int numArticulos=cliente.articulos.length;
		for (int i =1 ; i <= numArticulos; i++) {
			System.out.print(cliente.articulos[i]);
			if (i != numArticulos) {
				System.out.print(", ");
			} else {
				System.out.print(")");
			}
		}
		System.out.println();
		System.out.println(cliente.nombre + " pasando por caja...");
		for (int i = 0; i < cliente.articulos.length; i++) {
			try {
				Thread.sleep(cliente.articulos[i] * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor1 = Executors.newFixedThreadPool(10);
		ArrayList<Cliente> llistaTasques= new ArrayList<Cliente>();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		for (int i = 0; i < 50; i++) {  
			int[] Articulos = new int[(int) (Math.random() * 30 - 1) + 1];
			for (int j = 0; j < Articulos.length; j++) {
				Articulos[j] = (int) (Math.random() * 8 - 1) + 1;
			}

			clientes.add(new Cliente("Cliente num " + (i + 1) , Articulos));
		}  
		for (Cliente cliente: clientes) {
			Thread.sleep(3000);
			Runnable caja = new Apartat1(cliente);
			executor1.execute(caja);
		}
		executor1.shutdown();

		//schExService.shutdownNow();
		System.out.println("Completat");
	}
}






