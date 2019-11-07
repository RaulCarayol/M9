import java.awt.List;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Apartat1 {
	public class Cliente implements Callable<Cliente>{
		private String nombre;
		private int articulos;
		
		public Cliente(String nombre, int articulos){
			this.nombre= nombre;
			this.articulos = articulos;
		}
		
		@Override
		public Cliente call() {

			return new Cliente(nombre, articulos);
		}
		
	}



public static void main(String[] args) throws InterruptedException {
	
	//final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(4);
	
	// Crea objecte Runnable.
//	final Runnable ob = new Apartat1().new Cliente();
//	schExService.scheduleWithFixedDelay(ob, 0, 3, TimeUnit.SECONDS);
//	schExService.awaitTermination(150, TimeUnit.SECONDS);
	
	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	// Creamos una lista de sumas de dos numeros aleatorios
	ArrayList<Cliente> llistaTasques= new ArrayList<Cliente>();
	
	for (int i = 0; i < 25; i++) {
		//Cliente cliente = new Cliente("mi", 3);
		//llistaTasques.add(cliente);
	}
	

	//schExService.shutdownNow();
	System.out.println("Completat");
	

}
}