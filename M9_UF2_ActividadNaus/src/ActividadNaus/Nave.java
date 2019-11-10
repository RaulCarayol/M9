package ActividadNaus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.*;

import javax.swing.ImageIcon;


public class Nave implements Runnable{
		private int x,y;
		private int velocidad;
	    private String img = "/images/nau.jpg";
	    private Image image;
	    private int ancho,alto;

	    
		public Nave(int velocidad, int width,int height){
			this.velocidad=velocidad;
			image = new ImageIcon(Nau.class.getResource("/images/nau.png")).getImage();
			ancho = width;
			alto =height;
			x=width/2;
			y=height - image.getHeight(null)-40;
			
			
		}
		
	    public void moure (){
	    	//&& ((x < (ancho - image.getWidth(null))) && (x > 0) )
	    	if(PanelNau.rightPressed ){
	        	x=x+velocidad;
	        }else if(PanelNau.leftPressed ){
	        	  x= x-velocidad;
	        }
	    }

		@Override
		public void run() {
			// TODO Auto-generated method stub

	        while (true) { 
	            System.out.println("Movent Nave principal ");
	            try { Thread.sleep(100); } catch (Exception e) {}
	            moure();
	            }
		}

	    public void pinta (Graphics g) {
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.drawImage(image, x, y, null);
	        
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
	    
	    



}

