import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Apartat3a {
	public static void main(String[] args) {
		 ForkJoinPool pool = new ForkJoinPool();
	        System.out.println("Calculat:  " + pool.invoke(new Elevado(2,2)));

	}
}
class Elevado extends RecursiveTask<Integer>{
	int base,exponente;
	public Elevado(int base, int exponente){
		this.base=base;
		this.exponente=exponente;
	}

	@Override
	protected Integer compute() {
		int resultado=base;
	     if(exponente <= 0){ return 1;}
	     else if(exponente%2 == 0){
	    	 Elevado exp1 = new Elevado(base,exponente/2);
	    	 return exp1.compute()* exp1.join();
	     }else{
			Elevado exp2 = new Elevado(base,exponente-1);
			 return base*exp2.join();
	     }

	}
}
