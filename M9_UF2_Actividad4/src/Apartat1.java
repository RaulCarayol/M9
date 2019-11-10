import java.awt.List;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Apartat1 implements Runnable{
	Cliente cliente;
	public Apartat1(Cliente cliente){
		this.cliente=cliente;
	}
	public class Cliente implements Callable<Cliente>{
		private String nombre;
		private int[] articulos;
		
		public Cliente(String nombre, int[] articulos){
			this.nombre= nombre;
			this.articulos = articulos;
		}
		
		@Override
		public Cliente call() {

			return new Cliente(nombre, articulos);
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

public static void main(String[] args) throws InterruptedException {
	
	//final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(4);
	
	// Crea objecte Runnable.
//	final Runnable ob = new Apartat1().new Cliente();
//	schExService.scheduleWithFixedDelay(ob, 0, 3, TimeUnit.SECONDS);
//	schExService.awaitTermination(150, TimeUnit.SECONDS);
	
	
	ExecutorService executor1 = Executors.newFixedThreadPool(10);
	ArrayList<Cliente> llistaTasques= new ArrayList<Cliente>();


	//schExService.shutdownNow();
	System.out.println("Completat");
	

}


}