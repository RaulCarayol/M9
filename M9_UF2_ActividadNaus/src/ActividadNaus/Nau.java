package ActividadNaus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Nau extends Thread {
    private int numero;
    private int x,y;
    private int dsx,dsy,v;
    private int tx;
    private int ty;
    private int ancho,alto;
    private boolean destruido;
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
        }
    
    public void pinta (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.image, x, y, null);
        }
//    	public void mirarColision(){
//    		if ( instanceof Disparo) {
//    		}
//    	}
    

    public void run() {
        while (true) { 
            System.out.println("Movent nau numero " + this.numero);
            try { Thread.sleep(this.v); } catch (Exception e) {}
            moure();
            }
        }
    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null),image.getHeight(null));
    }

	public int getNumero() {
		return numero;
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


