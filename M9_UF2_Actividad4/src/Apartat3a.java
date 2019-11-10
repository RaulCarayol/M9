
public class Apartat3a {

	class elevado {
		int base,exponente;
		public elevado(int base, int exponente){
			this.base=base;
			this.exponente=exponente;
		}
		public int calcular(){
			int resultado=base;
			for (int i = 0; i < exponente; i++) {
				resultado=resultado * base;
			}
			return resultado;
		}
	}
	public static void main(String[] args) {
		System.out.println();

	}

}
