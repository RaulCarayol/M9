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
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
 class Cliente implements Callable<Cliente>{
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
	
 
public static void main(String[] args) throws InterruptedException {
	
	//final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(4);
	
	// Crea objecte Runnable.
//	final Runnable ob = new Apartat1().new Cliente();
//	schExService.scheduleWithFixedDelay(ob, 0, 3, TimeUnit.SECONDS);
//	schExService.awaitTermination(150, TimeUnit.SECONDS);
	
	
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

	//schExService.shutdownNow();
	System.out.println("Completat");
	}
}




