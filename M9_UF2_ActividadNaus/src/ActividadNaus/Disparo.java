package ActividadNaus;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.*;

import javax.swing.ImageIcon;
public class Disparo extends Thread{
			private int x,y;
			private int velocidad;
		    private String img = "/images/disparo.jpg";
		    private Image image;
		    private int ancho,alto;
		    private boolean destruido;
		    
			public Disparo(int velocidad, int width,int height,int x, int y){
				this.velocidad=velocidad;
				image = new ImageIcon(Nau.class.getResource("/images/disparo.png")).getImage();
				ancho = width;
				alto =height;
				this.x=x;
				this.y=y;
				destruido=false;
			}
			
		    public void moure (){
		        	  y=y-velocidad;
		        	  if(y<10){
		        		  //this.interrupt();
		        		  destruido=true; 
		        	  }
		    }

			public boolean getDestruido() {
				return destruido;
			}

			public void setDestruido(boolean destruido) {
				this.destruido = destruido;
			}
			

			@Override
			public void run() {
				// TODO Auto-generated method stub
		        while (true) { 
		            System.out.println("Moviendo Disparo Nave principal ");
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
