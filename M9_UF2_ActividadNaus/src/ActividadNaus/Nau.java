package ActividadNaus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Nau extends Thread  {
	private int numero;
	private int x,y;
	private int dsx,dsy,v;
	private int tx;
	private int ty;
	private int ancho,alto;
	private boolean destruido;
	private String img = "/images/nau.jpg";
	private Image image;
	Vector<Disparo> disparos = new Vector<Disparo>();
	private boolean execute;
	
	public Nau(int i, int posX, int posY, int dX, int dY, int velocitat, int ancho,int alto) {
		this.numero = i;
		this.x=posX;
		this.y=posY;
		this.dsx=dX;
		this.dsy=dY;
		this.v=velocitat;
		this.ancho=ancho;
		this.alto=alto;
		image = new ImageIcon(Nau.class.getResource("/images/nauvisual2.png")).getImage();
		ty = image.getHeight(null);
		tx = image.getWidth(null);
		Thread t = new Thread(this); 
		destruido=false;
		t.start();       
	}

	public int velocitat (){ 
		return v;
	}

	public synchronized void moure (){
		x=x + dsx;
		y=y + dsy;
		// si arriva als marges ...
		if ( x>=ancho - tx || x<=0) dsx = - dsx;
		if ( y >= alto - ty || y<=0 ) dsy = - dsy;
		System.out.println("nave " + x +" "+y);
	}

	public void pinta (Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(this.image, x, y, null);

		if(!disparos.isEmpty()){
			for (int j = 0; j < disparos.size(); j++) {
				disparos.elementAt(j).pinta(g);
			}
		} 	
	}

	public void disparar () {
		Disparo disparo = new Disparo(40, ancho,alto,x + (image.getWidth(null) /2), y,true);
		disparos.add(disparo);
		disparo.start();
	}

	public void run() {
		int i=0;
		execute=true;
		Random rand = new Random();
		int timerDisp=(rand.nextInt(1000)+200);
        while (execute) { 
			//System.out.println("Movent nau numero " + this.numero);
			try { Thread.sleep(this.v); } catch (Exception e) {}
			moure();
			destruirDisparos();
			if(i==timerDisp){
				disparar();
				i=0;
			}else{i++;}

		}
	}

	public void destruirDisparos(){
		for (int j = 0; j < disparos.size(); j++) {
			if(disparos.elementAt(j).getDestruido()){
				disparos.elementAt(j).interrupt();
				disparos.elementAt(j).setExecute(false);
				disparos.remove(j);
				System.gc();
				System.out.println("Destruido disparo enemigo");
			}
		}	
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getWidth(null)-20,image.getHeight(null)-20);
	}

	public int getNumero() {
		return numero;
	}

	public boolean isExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDsx() {
		return dsx;
	}

	public void setDsx(int dsx) {
		this.dsx = dsx;
	}

	public int getDsy() {
		return dsy;
	}

	public void setDsy(int dsy) {
		this.dsy = dsy;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getTx() {
		return tx;
	}

	public void setTx(int tx) {
		this.tx = tx;
	}

	public int getTy() {
		return ty;
	}

	public void setTy(int ty) {
		this.ty = ty;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setDestruido(boolean a){
		destruido=a;
	}
	public boolean getDestruido(){
		return destruido;
	}

}


