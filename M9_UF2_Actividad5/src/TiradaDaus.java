

public class TiradaDaus {
	private int tiradaDau;

	public TiradaDaus (int e) {
		tiradaDau=e;
	}

	public synchronized int getSumaTirada() {
		return tiradaDau;
	}

	public synchronized void setSumaTirada(int e) {
		tiradaDau += e;
	}
}
/*
public class JoinFils implements Runnable {

	private TiradaDaus xobj;

	public JoinFils(TiradaDaus m) {
		xobj=m;
	}
	public void run(){
		try{
			Thread.sleep(1000);
			int resultatDau=(int) (Math.random()*6) + 1;
			xobj.setSumaTirada(resultatDau);
			System.out.println("Tirada fil "+Thread.currentThread().getName() +
					": " +resultatDau);
		}catch (InterruptedException e){
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TiradaDaus ans=new TiradaDaus(0);

		JoinFils obj1 = new JoinFils(ans);
		JoinFils obj2 = new JoinFils(ans);
		JoinFils obj3 = new JoinFils(ans);
		Thread fil_1 = new Thread(obj1);
		fil_1.setName("Dau 1");
		Thread fil_2 = new Thread(obj2);
		fil_2.setName("Dau 2");
		Thread fil_3 = new Thread(obj3);
		fil_3.setName("Dau 3");
		fil_1.start();
		fil_2.start();
		fil_3.start();
		fil_1.join(); //Espera el fil_1 que el fil principal, el que l’ha invocat acabi
		fil_2.join();
		fil_3.join();
		System.out.println("Total tirada: "+ ans.getSumaTirada());
		System.out.println("Final Fil Principal");
	}
}

*/
