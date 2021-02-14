package laungframe;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JPanel;
import contast.ConstantData;
import contast.image;
import father.Plant;
import item.seed;


public class GameStart extends JPanel implements ConstantData, MouseListener, MouseMotionListener, Runnable{

	private Image grasslandImage;
	private Thread gameThread;
	private killRank socre; 
	private Thread killrankThread; 
    private Image Offscreen;
	private GameMap map;
	private ArrayList<seed> cards; 
    private int mouseX;
    private int mouseY;
    private Plant p; 
    private int which; 
    private boolean gameover;
    /**
     * initialize the panel
     * @throws FileNotFoundException if the file is not found 
     */
	public GameStart() throws FileNotFoundException {
		which = 0; 
		map = new GameMap(this);
		grasslandImage = image.getImage("background.png");
		gameThread = new Thread(this);
		socre = new killRank(map); 
		killrankThread = new Thread(socre);
		cards = new ArrayList<seed>();
		seed seed1 =  new seed(image.getImage("singleA.png"), image.getImage("singleB.png"),planttype.single, map);
		seed seed2 =  new seed(image.getImage("sunA.png"), image.getImage("sunB.png"),planttype.sunflower, map);
		seed seed3 =  new seed(image.getImage("nutwallA.png"), image.getImage("nutwallB.png"),planttype.nutwall, map);
		seed seed4 =  new seed(image.getImage("cherryA.png"), image.getImage("cherryB.png"),planttype.cherry, map);
		cards.add(seed1);
		cards.add(seed2);
		cards.add(seed3);
		cards.add(seed4);
		gameover = false;
		p = null; 
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	/**
	 * draw the images in the screen
	 */
	@Override
	public void paint(Graphics g){
		g.drawImage(grasslandImage, 0, 0, null);
		if (p != null) {			
			g.drawImage(p.getimage(),mouseX - PLANT_WIDTH/2, mouseY - PLANT_HEIGHT/2 , null);
		}
		map.draw(g);
		int cardnum  = cards.size(); 
		for(int i=0; i<cardnum; i++){
			cards.get(i).position(WEST_OFFSET+i*(CARD_WIDTH + CARD_GAP_W), TOP_OFFSET);
			cards.get(i).draw(g);
		}
		
		if(gameover == true) g.drawImage(image.getImage("lose.png"), 250, 250, null);
	}
	/**
	 *  use double buffering to draw image 
	 */
	@Override
	public void update(Graphics g) {
		if(Offscreen == null){
			Offscreen = this.createImage(Screen_Width, Screen_Height);
		}
		if(Offscreen == null){
			return;
		}
		Graphics gOffscreen = Offscreen.getGraphics();
		gOffscreen.drawImage(grasslandImage, 0, 0, null);
		paint(gOffscreen);
		g.drawImage(Offscreen, 0, 0, null);	
	}

	/**
	 * every 100ms, repaint
	 */
	@Override
	public void repaint() {
		update(getGraphics());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * detected where the mouse is and draw plant image
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
	    mouseY = e.getY();	
	}

	/**
	 * while click, detected user is selecting plant or placeing plant
	 * if button is right button, revoke last action 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			p = null;
			return; 
		}
	
		int x = e.getX(); 
		int y = e.getY(); 
		int cardnum  = cards.size(); 
		for(int i=0; i<cardnum; i++){
			if(cards.get(i).mouseIn(x, y)){
				p = cards.get(i).getplant();
				which = i; 
				break; 
			}
		}
		if(map.inTheMap(x,y)){
			if(p != null)
			{
				map.setplant(x,y,p);
				if(p.planted)cards.get(which).planted();
			}
			p = null;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Use mulitThread to run the game
	 */
	public void startGame(){
		gameThread.start();
		killrankThread.start();
	}
	/**
	 * let game end with user is lose 
	 */
	public void lose(){
		gameover =true; 
		socre.endGame();
	}
	
	/**
	 * use thread to repaint
	 */
	@Override
	public void run() {
		while(gameover == false){
			repaint();
			try{
				Thread.sleep(100);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
