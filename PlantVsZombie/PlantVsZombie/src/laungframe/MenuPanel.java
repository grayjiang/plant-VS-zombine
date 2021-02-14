package laungframe;

import javax.swing.*;

import contast.image;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel{	
	
	private Image EnteringImg;
	private Image ButtonImg1;                     //image of button when on, click, press  
	private Image ButtonImg2;                     //image of button original 
	
	private MainFrame mainFrame;
	/**
	 * initialize
	 * @param mainFrame the access to the MainFrame class
	 */
	public MenuPanel(MainFrame mainFrame){
		this.mainFrame = mainFrame;		
		EnteringImg = image.getImage("EnteringImg.png");
		ButtonImg1 = image.getImage("ButtonImg1.png");
		ButtonImg2 = image.getImage("ButtonImg2.png");	
		setLayout(null);

		JButton startBtn = new JButton();
		
		startBtn.setBorder(null);
		startBtn.setIcon(new ImageIcon(ButtonImg2));
		startBtn.setRolloverIcon(new ImageIcon(ButtonImg1));   
		startBtn.setPressedIcon(new ImageIcon(ButtonImg1));    
		startBtn.addActionListener(new ActionListener() {       
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToGame();
			}
		});
		add(startBtn);
		startBtn.setBounds(162, 483, ButtonImg1.getWidth(null), ButtonImg1.getHeight(null));
	}
	/**
	 * when button click, switch to game 
	 */
	public void switchToGame() {
		mainFrame.switchToGame();
	}
	
	/**
	 * draw entering image 
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(EnteringImg, 0, 0, getWidth(), getHeight(), null);
	}
}
