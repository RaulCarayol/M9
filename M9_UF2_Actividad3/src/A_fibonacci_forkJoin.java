import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class A_fibonacci_forkJoin extends RecursiveTask<Long> {
    long numero;
    public A_fibonacci_forkJoin(long numero){
        this.numero=numero;
    }    
    protected Long compute() {
        // ATENCIO **1** double calcul = java.lang.Math.cos(54879854);
        if(numero <= 1) return numero;
        A_fibonacci_forkJoin fib1 = new A_fibonacci_forkJoin(numero-1);
        //fib1.fork();
	A_fibonacci_forkJoin fib2 = new A_fibonacci_forkJoin(numero-2);
        fib2.fork();
	 return fib1.compute()+ fib2.join();
	 }
    public static void main(String[] args){
    	long TiempoInicial=System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Calculat:  " + pool.invoke(new A_fibonacci_forkJoin(41)));
		long tiempoEjecucion=((System.currentTimeMillis()-TiempoInicial));
		System.out.println("Tiempo de ejecución : " +
							tiempoEjecucion/100d);
    }
}