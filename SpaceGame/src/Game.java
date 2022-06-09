import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
class Ates{
	private int x;
	private int y;
	public Ates(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
}
public class Game extends JPanel implements KeyListener,ActionListener{
	public boolean kontrolEt() {
		for(Ates ates:atesler) {
			if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,20,20))) {
				return true;
			}
		}
		return false;
	}
	Timer timer=new Timer(5,this);
	private int atesSayisi=0;
	private int gecenSure=0;
	private BufferedImage image;
	private ArrayList<Ates>atesler=new ArrayList<Ates>();
	private int topX=0;
	private int topdirX=6;
	private int uzayGemisiX=0;
	private int dirUzayGemisiX=20;
	private int diratesY=2;
	public Game() {
		super();
		// TODO Auto-generated constructor stub
		try {
			image=ImageIO.read(new FileInputStream(new File("uzaygemisi.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBackground(Color.black);
		timer.start();
		
		
	}

	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		gecenSure+=5;
		g.setColor(Color.red);
		g.fillOval(topX,0,20,20);
		g.drawImage(image, uzayGemisiX,490,image.getHeight()/10,image.getWidth()/10+20,this);
		for(Ates ates:atesler) {
			if(ates.getY()<0) {
				atesler.remove(ates);
			}
		}
		g.setColor(Color.blue);
		for(Ates ates:atesler) {
			g.fillRect(ates.getX(), ates.getY(),10,20);
		}
		if(kontrolEt()) {
			timer.stop();
			String message="Kazandýnýz...\n"
					       +"Harcanan Ateþ Sayýsý:"+atesSayisi
			               +"\nGeçen Süre:"+gecenSure/ 1000.0+"Saniye";
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
		}
	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(Ates ates:atesler) {
			ates.setY(ates.getY()-diratesY);
		}
		topX+=topdirX;
		if(topX>=665) {
			topdirX=-topdirX;
		}
		if(topX<=0) {
			topdirX=-topdirX;
		}
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int c=e.getKeyCode();
		if(c==KeyEvent.VK_LEFT) {
			if(uzayGemisiX<=0) {
				uzayGemisiX=0;
			}
			else {
				uzayGemisiX-=dirUzayGemisiX;
			}
		}
		else if(c==KeyEvent.VK_RIGHT) {
			if(uzayGemisiX>=600) {
				uzayGemisiX=600;
			}
			else {
				uzayGemisiX+=dirUzayGemisiX;
			}
		}
		else {
			if(c==KeyEvent.VK_CONTROL) {
				atesler.add(new Ates(uzayGemisiX+30,470));
				atesSayisi++;
			}
		}
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
