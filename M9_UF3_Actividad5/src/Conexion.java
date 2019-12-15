import java.awt.image.DirectColorModel;
import java.net.InetAddress;

public class Conexion{
	private String nom;
	private InetAddress direccion;
	private int puerto;
	public Conexion(String nom, InetAddress direccion,int puerto){
		this.nom=nom;
		this.direccion=direccion;
		this.puerto = puerto;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public InetAddress getDireccion() {
		return direccion;
	}

	public void setDireccion(InetAddress direccion) {
		this.direccion = direccion;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String toString(){
		return nom +"\ndireccion: " + direccion.toString() + "\nPuerto: "+puerto;
	}
}