package ActividadNaus;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NauEspaial extends javax.swing.JFrame {    
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
	        
		       //Para mirar el maximo de la pantalla
	         GraphicsConfiguration config = f.getGraphicsConfiguration();
	         int left = Toolkit.getDefaultToolkit().getScreenInsets(config).left;
	         int right = Toolkit.getDefaultToolkit().getScreenInsets(config).right;
	         int top = Toolkit.getDefaultToolkit().getScreenInsets(config).top;
	         int bottom = Toolkit.getDefaultToolkit().getScreenInsets(config).bottom;

	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	         final int width = screenSize.width - left - right;
	         final int height = screenSize.height - top - bottom;
	         
	        f.setContentPane(new PanelNau(width,height));
	        
	        f.setSize(width, height);
	        f.setVisible(true);
	        }
	    }
	class PanelNau extends JPanel implements Runnable{
	    private int numNaus=2;    
	    Vector <Nau> nau = new Vector<Nau>(numNaus);
	    int width,height;
	    Nave navePrincipal;
	    Vector<Disparo> disparos= new Vector<Disparo>();
	    boolean finJuego=false;
	    public PanelNau(int ancho,int alto){
	    	setBackground(Color.BLACK);
	    	width=ancho;
	    	height=alto;
	        //nau = new Nau[numNaus];
	        for (int i=0;i<nau.capacity();i++) {
	            //Random rand = new Random();
	            //int velocitat=(rand.nextInt(3)+5)*2;
	            //int posX=rand.nextInt(100)+30;
	            //int posY=rand.nextInt(100)+30;
	            //int dX=rand.nextInt(3)+10;
	            //int dY=rand.nextInt(3)+10;
	            int velocitat =(int) (((Math.random() * ((3 - 1) + 1)) + 1));
	            int posX =(int) (((Math.random() * ((100 - 10) + 1)) + 10));
	            int posY =(int) (((Math.random() * ((100 - 10) + 1)) + 10));
	            int dX =(int) (((Math.random() * ((2 - 1) + 1)) + 1));
	            int dY =(int) (((Math.random() * ((2 - 1) + 1)) + 1));

	            nau.add( new Nau(i,posX,posY,dX,dY,velocitat,width,height));
	            }
	        addKeyListener(new KeyInputHandler());
			setFocusable(true);
	        navePrincipal= new Nave(11,width,height); 
	        //Thread hiloNavePrincipal = new Thread(navePrincipal);
	        navePrincipal.start();
	        Thread n = new Thread(this);
	        n.start();
	        }

	    public void run() {
	        System.out.println("Inici fil repintar");
	        while(true) {
	            try { Thread.sleep(10);} catch(Exception e) {} // espero 0,01 segons
	            	if(!finJuego && numNaus != 0){
	            		System.out.println("Repintant");
	            		repaint();
					     try {
							mirarColisiones();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            		destruirDisparos();
	            		destruirNaves();
	            	}else{
	            		drawGameOver(this.getGraphics());
	            		Thread.interrupted();
	            		for (int i = 0; i < nau.size(); i++) {
							nau.elementAt(i).interrupt();
						}
	            		navePrincipal.interrupt();
	            		System.gc();
	            	}
	            }                   
	        }


		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        for(int i=0; i<nau.size();++i) {
	        		nau.elementAt(i).pinta(g);
	        		navePrincipal.pinta(g);
	        	}
	        if(!disparos.isEmpty()){
				for (int j = 0; j < disparos.size(); j++) {
					disparos.elementAt(j).pinta(g);
				}
				} 	
	        }
	    public void destruirDisparos(){
	    	 if(!disparos.isEmpty()){
					for (int j = 0; j < disparos.size(); j++) {
						if(disparos.elementAt(j).getDestruido()){
							disparos.elementAt(j).interrupt();
							disparos.remove(j);
							System.gc();
							System.out.println("Destruido disparo");
						}
					}
					} 	
	    }
	    public void destruirNaves(){
					for (int j = 0; j < nau.size(); j++) {
						if(nau.elementAt(j).getDestruido()){
							nau.elementAt(j).interrupt();
							nau.remove(j);
							System.gc();
							System.out.println("Destruido nave");
						}
					}
					} 	

	    private void drawGameOver(Graphics g) {

	        String msg = "Game Over";
	        Font small = new Font("Helvetica", Font.BOLD, 140);
	        FontMetrics fm = getFontMetrics(small);

	        g.setColor(Color.WHITE);
	        g.setFont(small);
	        g.drawString(msg, (width - fm.stringWidth(msg)) / 2,
	                height / 2);
	    }
	    
	    	public void mirarColisiones() throws InterruptedException{
	            Rectangle r3 = navePrincipal.getBounds();
	            
	            for (Nau alien : nau) {
	                Rectangle r2 = alien.getBounds();
	                if (r3.intersects(r2)) {
	                	alien.setDsx(0);
	                	alien.setDsy(0);
	                	alien.interrupt();
	                	finJuego=true;
	                }
	            }

	            for (Disparo m : disparos) {

	                Rectangle r1 = m.getBounds();

	                for (Nau alien : nau) {

	                    Rectangle r2 = alien.getBounds();

	                    if (r1.intersects(r2)) {
	                        
	                        
	                    	alien.setDsx(0);
		                	alien.setDsy(0);
		                	alien.setDestruido(true);
	                        //m.setImage(new ImageIcon(Nau.class.getResource("/images/explosion1.png")).getImage());
	                        m.colision(getGraphics());
	                        numNaus--;
	                        //alien.setImage(null);
	                     
	                    }
	                }
	            }
	        }
	    
	    class KeyInputHandler extends KeyAdapter {
		    @Override
		    public void keyPressed(KeyEvent e) {
		    	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						//navePrincipal.moverIzquierda();
		    		navePrincipal.setDx(-10);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					navePrincipal.setDx(+10);
					//navePrincipal.moverDerecha();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					navePrincipal.setDy(-10);
					//navePrincipal.moverArriba();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					navePrincipal.setDy(+10);
					//navePrincipal.moverAbajo();
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					Disparo disparo=new Disparo(40, width,height,navePrincipal.getX() + (navePrincipal.getImage().getWidth(null) /2), navePrincipal.getY());
					disparos.add(disparo);
					disparo.start();	
				}
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		        	navePrincipal.setDx(0);
		        }
		        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		        	navePrincipal.setDx(0);
		        }
		        if (e.getKeyCode() == KeyEvent.VK_UP) {
		        	navePrincipal.setDy(0);
		        }
		        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		        	navePrincipal.setDy(0);
		        }
		        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		           
		        }
		    }
		}
	    
	    }


	

