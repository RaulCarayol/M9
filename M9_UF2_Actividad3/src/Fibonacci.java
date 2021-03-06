
public class Fibonacci {

	public static long calculaFibonacci(long numero) {
        double calcul = java.lang.Math.cos(54879854);
        if (numero==0) { return 0; }
        else if (numero==1) { return 1; }
        else { 
            return (calculaFibonacci(numero-2) + calculaFibonacci(numero-1)); 
        }
    }
	
	public static void main(String[] args) {
		long TiempoInicial=System.nanoTime();
		System.out.println("Calculat : "+ calculaFibonacci(42));
		long tiempoEjecucion=((System.nanoTime()-TiempoInicial));
		System.out.println("Tiempo de ejecucion :" +
							tiempoEjecucion);
	}

}
