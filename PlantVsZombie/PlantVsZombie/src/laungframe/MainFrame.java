package laungframe;

import javax.swing.*;
import contast.ConstantData;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;

public class MainFrame extends JFrame{	
	
	MenuPanel menuPanel;
	JPanel panel;
	GameStart start;
	private CardLayout layout;

	/**
	 * creat mainframe()
	 * @throws FileNotFoundException if the file is not found 
	 */
	
	public MainFrame() throws FileNotFoundException{
		
		setTitle("This is a test");
		setSize(ConstantData.Screen_Width, ConstantData.Screen_Height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			
		panel = new JPanel();		
		menuPanel = new MenuPanel(this);
		start = new GameStart(); 
		layout = new CardLayout();
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel,BorderLayout.CENTER);
		
		panel.setLayout(layout);
		panel.setPreferredSize(new Dimension(ConstantData.Screen_Width, ConstantData.Screen_Height)); 
		panel.add(menuPanel,"menu");      
		panel.add(start,"start");	
		setVisible(true);
		
	}
	/**
	 * switch to game panel
	 */
	
	public void switchToGame(){
		layout.show(panel, "start");     
		start.startGame();
	}
	/**
	 * main: where the pro
	 * @param args did not require 
	 * @throws FileNotFoundException if the input file is not found 
	 */
	public static void main(String[] args) throws FileNotFoundException{
		new MainFrame();
	}
}
