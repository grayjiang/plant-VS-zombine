package laungframe;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class killRank extends JFrame implements Runnable{
	private JPanel panel;
	private JLabel[][] list;
	private int position; 
	private File theFile;
	private GameMap map; 
	private String name;
	private boolean gameover; 
	/**
	 * initialize the killRank  
	 * @param map the access to the GameMap class
	 * @throws FileNotFoundException if the file is not found 
	 */
	public killRank(GameMap map) throws FileNotFoundException{
		name = " ";
		this.map = map; 
		gameover = false; 
		theFile = new File("list.txt");
		position = 9; 
		setSize(100, 300);
		setLocation(821,0);
		setTitle("kill ranking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		list = new JLabel[10][2];
			for(int i=0; i<10; i++){
				for(int j=0; j<2; j++){
					list[i][j] = new JLabel();
				}
			}
				
		panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		readfile();
		this.add(panel);
		setVisible(true);
	}
	
	/**
	 * stop the thread
	 */
	public void endGame(){
		gameover = true; 
	}
	
	/**
	 * read data from file 
	 * @throws FileNotFoundException if the file is not found 
	 */
	public void readfile() throws FileNotFoundException{
		int counter = 0;
		if(theFile.exists()){
			Scanner in = new Scanner(theFile);
			while (in.hasNext())                                                
	        {
	            String line = in.nextLine();
	            String[] parts = line.split(" ");
	            list[counter][0].setText(parts[0]);
	            list[counter][1].setText(parts[1]);
	            panel.add(list[counter][0]);
	            panel.add(list[counter][1]);
	            counter++; 
	        }
			list[9][0].setText("you");
            list[9][1].setText("0");
            panel.add(list[9][0]);
            panel.add(list[9][1]);
		}else{
			System.out.println("not found");
		}
	}
	
	/**
	 * save data in txt
	 * @throws IOException if the output file is not found 
	 */
	public void writefile() throws IOException{
		String text = "";
		FileWriter out = new FileWriter(theFile);
		for(int i=0; i<9; i++){
			text = list[i][0].getText()+" "+list[i][1].getText();
			out.write(text+"\r\n");
		}
		out.close();
	}
	/**
	 * get previous one's score and compare with yours, if you are higher, you can rank before him
	 */
	public void update(){
		list[position][1].setText(map.getkill()+"");
		if(position !=0){
	       int a = Integer.parseInt(list[position][1].getText());
	       int b = Integer.parseInt(list[position-1][1].getText());
	       if(a>b){
	    	   JLabel temp = new JLabel();
	    	   temp.setText(list[position-1][0].getText()); 
	    	   list[position-1][0].setText(list[position][0].getText());
	    	   list[position][0].setText(temp.getText());
	    	   
	    	   temp.setText(list[position-1][1].getText()); 
	    	   list[position-1][1].setText(list[position][1].getText());
	    	   list[position][1].setText(temp.getText());
	    	   position--;
	    	  
	       }
		}
	}
	/**
	 * show final result and save the data
	 */
	public void finalResult(){
		if(name.equals(" ")){
  			name = JOptionPane.showInputDialog("Please input your name");
  			list[position][0].setText(name);
  			for(int i=0; i<9; i++){
  				if(name.equals(list[i][0].getText()) && i != position){
  					if(position < i){
  						for(int j= i; j<9; j++){
  							 list[j][0].setText(list[j+1][0].getText());
  							 list[j][1].setText(list[j+1][1].getText());
  							 
  						}
  					}else {
  						for(int j= position; j<9; j++){
 							 list[j][0].setText(list[j+1][0].getText());
 							 list[j][1].setText(list[j+1][1].getText());
 						}
  					}
  				}
  			}
  			list[9][0].setText("");
			list[9][1].setText("");
			try {
				writefile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			
  		}
	}
	/**
	 * Override the thread's run method  
	 */
	@Override
	public void run() {
		while(gameover == false){
			try{
				update();
				Thread.sleep(100);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		finalResult();
	}
}
