package ActividadNaus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Nau extends Thread {
    private int numero;
    private int x,y;
    private int dsx,dsy,v;
    private int tx;
    private int ty;
    private int ancho,alto;

    private String img = "/images/nau.jpg";
    private Image image;

    public Nau(int i, int posX, int posY, int dX, int dY, int velocitat, int ancho,int alto) {
        this.numero = i;
        this.x=x;
        this.y=y;
        this.dsx=dX;
        this.dsy=dY;
        this.v=velocitat;
        this.ancho=ancho;
        this.alto=alto;
        image = new ImageIcon(Nau.class.getResource("/images/nau.png")).getImage();
        ty = image.getHeight(null);
        tx = image.getWidth(null);
        Thread t = new Thread(this); 

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


