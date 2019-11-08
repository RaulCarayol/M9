package ActividadNaus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Nau extends Thread {
    private int numero;
    private int x,y;
    private int dsx,dsy,v;
    private int tx = 215;
    private int ty = 190;

    private String img = "/images/nau.jpg";
    private Image image;

    public Nau(int i, int posX, int posY, int dX, int dY, int velocitat) {
        this.numero = i;
        this.x=x;
        this.y=y;
        this.dsx=dsx;
        this.dsy=dsy;
        this.v=velocitat;
        image = new ImageIcon(Nau.class.getResource("/images/nau.png")).getImage();
        Thread t = new Thread(this); 
        t.start();
        
    }
    
  

	 {
		// TODO Auto-generated constructor stub
	}



	public int velocitat (){ 
        return v;
        }
    
    public synchronized void moure (){
        x=x + dsx;
        y=y + dsy;
        // si arriva als marges ...
        if ( x>=NauEspaial.width - tx || x<= 0) dsx = - dsx;
        if ( y >= NauEspaial.height - ty || y<=0 ) dsy = - dsy;
        }
    
    public void pinta (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.image, x, y, null);
        }
    

    public void run() {
        while (true) { 
            System.out.println("Movent nau numero " + this.numero);
            try { Thread.sleep(this.v); } catch (Exception e) {}
            moure();
            }
        }



    }


