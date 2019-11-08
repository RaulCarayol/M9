import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NauEspaial extends javax.swing.JFrame {    
    static int width;
    static int height;
	    public NauEspaial() {
	        initComponents();
	        }

	    @SuppressWarnings("unchecked")
	    private void initComponents() {
	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        setBackground(new java.awt.Color(255, 255, 255));
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGap(0, 400, Short.MAX_VALUE));
	        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGap(0, 300, Short.MAX_VALUE));
	        pack();
	        }
	    
	    public static void main(String args[]) {
	        try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                    }
	                }
	            } 
	        catch (Exception ex) {
	            java.util.logging.Logger.getLogger(NauEspaial.class.getName()).log(
	                java.util.logging.Level.SEVERE, null, ex);
	            }       
	        NauEspaial f = new NauEspaial();
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setTitle("Naus Espaials");
	        f.setContentPane(new PanelNau());
	        
	       //Para mirar el maximo de la pantalla
	        final GraphicsConfiguration config = f.getGraphicsConfiguration();
	        final int left = Toolkit.getDefaultToolkit().getScreenInsets(config).left;
	        final int right = Toolkit.getDefaultToolkit().getScreenInsets(config).right;
	        final int top = Toolkit.getDefaultToolkit().getScreenInsets(config).top;
	        final int bottom = Toolkit.getDefaultToolkit().getScreenInsets(config).bottom;

	        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	         width = screenSize.width - left - right;
	         height = screenSize.height - top;
	        
	        f.setSize(width, height);
	        f.setVisible(true);
	        }
	    }
	class PanelNau extends JPanel implements Runnable{
	    private int numNaus=200;    
	    Nau[] nau;

	    public PanelNau(){        
	        nau = new Nau[numNaus];
	        for (int i=0;i<nau.length;i++) {
	            Random rand = new Random();
	            int velocitat=(rand.nextInt(3)+5)*1;
	            int posX=rand.nextInt(100)+30;
	            int posY=rand.nextInt(100)+30;
	            int dX=rand.nextInt(3)+1;
	            int dY=rand.nextInt(3)+1;
	            nau[i]= new Nau(i,posX,posY,dX,dY,velocitat);
	            }
	        Thread n = new Thread(this);
	        n.start();   
	        }

	    public void run() {
	        System.out.println("Inici fil repintar");
	        while(true) {
	            try { Thread.sleep(10);} catch(Exception e) {} // espero 0,1 segons
	            System.out.println("Repintant");
	            repaint();            
	            }                   
	        }

	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        for(int i=0; i<nau.length;++i) nau[i].pinta(g);
	        }
	    }


	class Nau extends Thread {
	    private int numero;
	    private int x,y;
	    private int dsx,dsy,v;
	    private int tx = 200;
	    private int ty = 200;

	    private String img = "/images/nau.jpg";
	    private Image image;

	    public Nau(int numero, int x, int y, int dsx, int dsy, int v ) {
	        this.numero = numero;
	        this.x=x;
	        this.y=y;
	        this.dsx=dsx;
	        this.dsy=dsy;
	        this.v=v;
	        image = new ImageIcon(Nau.class.getResource("/images/nau.png")).getImage();
	        Thread t = new Thread(this); 
	        t.start();
	        }
	    
	    public int velocitat (){ 
	        return v;
	        }
	    
	    public void moure (){
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

