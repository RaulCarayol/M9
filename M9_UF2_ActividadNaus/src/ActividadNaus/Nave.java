package ActividadNaus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.*;

import javax.swing.ImageIcon;


public class Nave extends Thread{
	private int x,y;
	private int velocidad,dx,dy;
	private String img = "/images/nau.jpg";
	private Image image;
	private int ancho,alto;
	private boolean execute;


	public Nave(int velocidad, int width,int height){
		this.velocidad=velocidad;
		image = new ImageIcon(Nau.class.getResource("/images/nauvisual1.png")).getImage();
		ancho = width;
		alto =height;
		x=width/2;
		y=height - image.getHeight(null)-40;
		dy=0;
		dx=0;
		Thread t = new Thread(this);
		t.setPriority(8);
		t.start();
	}

	public void moure (){
		if(((x+dx*velocidad) > (0)) && ((x+dx*velocidad) < (ancho-image.getWidth(null)))){
			x=x+dx*velocidad;}
		if(((y+dy*velocidad) > 0) &&((y+dy*velocidad) < alto-image.getHeight(null)-10)){
			y=y+dy*velocidad;
		}
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public void moverIzquierda (){
		if((x-velocidad) > -image.getWidth(null)/8){
			x=x-velocidad;}
	}
	public void moverDerecha (){
		if((x+velocidad) < ancho - image.getWidth(null)*7/8){
			x=x+velocidad;}
	}
	public void moverArriba (){
		if((y-velocidad) > -image.getHeight(null)/4){
			y=y-velocidad;}
	}
	public void moverAbajo (){
		if((y+velocidad) < (alto - image.getHeight(null))){
			y=y+velocidad;}
	}

	@Override
	public void run() {
		execute=true;
		while (execute) { 
			//System.out.println("Movent Nave principal ");
			try { Thread.sleep(60); } catch (Exception e) {}
			moure();
		}
	}

	public void pinta (Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, x, y, null);

	}

	public boolean isExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
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

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
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

	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getWidth(null)-20,image.getHeight(null)-20);
	}
}

