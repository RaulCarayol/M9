import java.sql.CallableStatement;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Apartat1 {
	public class cliente implements Runnable{
		private String nombre;
		
		
		
		@Override
		public void run() {
			System.out.println("Creado");
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(4);
		
		// Crea objecte Runnable.
		final Runnable ob = new Apartat1().new cliente();
		schExService.scheduleWithFixedDelay(ob, 0, 2, TimeUnit.SECONDS);
		schExService.awaitTermination(150, TimeUnit.SECONDS);
		// shutdown (Acaba tots els fils)
		schExService.shutdownNow();
		//Mostra "Completat" al final del programa
		System.out.println("Completat");
		
	}

}
