package ActividadNaus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.*;

import javax.swing.ImageIcon;

public class Nave implements Runnable{
		private int x=NauEspaial.width/2,y=NauEspaial.height;
		private int velocidad;
	    private String img = "/images/nau.jpg";
	    private Image image;
	    
		public Nave(int velocidad){
			this.velocidad=velocidad;
			image = new ImageIcon(Nau.class.getResource("/images/nau.png")).getImage();
		}

		private boolean leftPressed = false;
	
	    private boolean rightPressed = false;
	
	    private boolean firePressed = false;
	    
	    private final double velocidadMovimiento = 300;
	    
	    
	    private class KeyInputHandler extends KeyAdapter {
	
	        @Override
	        public void keyPressed(KeyEvent e) {
	
	
	            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	                leftPressed = true;
	            }
	            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	                rightPressed = true;
	            }
	            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	                firePressed = true;
	            }
	        }
	
	        @Override
	        public void keyReleased(KeyEvent e) {
	
	            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	                leftPressed = false;
	            }
	            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	                rightPressed = false;
	            }
	            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	                firePressed = false;
	            }
	        }
	    }
	    

	    public synchronized void moure (){
	        boolean dentro = ((x < NauEspaial.width-215) && (x > 0) );
	    	if(rightPressed //&& dentro
	    			){
	        	x=x+velocidad;
	        }else if(leftPressed){
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



}
