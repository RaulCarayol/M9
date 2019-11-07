public class HeretaFil extends Thread {

	String strImprimir;
	public HeretaFil(String strP) {
		strImprimir=strP;
	}

	public void run(){
		for(int x=0;x<5;x++){
			System.out.println(strImprimir+ " " + x);
		}

	}

	public static void main(String[] args) {

		Thread primer = new HeretaFil("Fil 1");
		Thread segon = new HeretaFil("Fil 2");
		Thread tercero = new HeretaFil("Fil 3");
		Thread cuarto = new HeretaFil("Fil 4");
		Thread quinto = new HeretaFil("Fil 5");
		Thread sexto = new HeretaFil("Fil 6");
		Thread septimo = new HeretaFil("Fil 7");
		Thread octavo = new HeretaFil("Fil 8");
		Thread noveno = new HeretaFil("Fil 9");
		Thread decimo = new HeretaFil("Fil 10");
		Thread undecimo = new HeretaFil("Fil 11");
		
		// Hem creat dos fils primer i segon, però no s’han executat.
		// Per poder−lo executar s’ha de cridar al mètode start()
		primer.run();
		segon.run();
		tercero.run();
		cuarto.run();
		quinto.run();
		sexto.run();
		septimo.run();
		octavo.run();
		noveno.run();
		decimo.run();
		undecimo.run();

		System.out.println("Final Fil Principal");

	}
}