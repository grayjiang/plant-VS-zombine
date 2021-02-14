package father;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import contast.ConstantData;
import laungframe.GameMap;


public abstract class Plant {
	
	public Image plantImage;
	public Image bulletImage;
	public GameMap pointer;
	public int health;
	public int maxhealth;
	public int cost;
	
	public int damage; 
	public int frequency; 
	public int counter; 
	public int CD;                  
	public boolean planted;
	public int x,y;
	
	/**
	 * each plant have their own function
	 */
	public abstract void fire();
	
	/**
	 * draw the plant
	 * @param g the Graphics of the screen 
	 */
	public void draw(Graphics g) {
		if(health <1){
			pointer.removeplant(x, y);
			return; 					
		}
		fire();
		g.setColor(Color.RED);
		g.drawRect(x+15, y-12, 50, 10);
		g.fillRect(x+15, y-12, ((int)(50.0/maxhealth*health)), 10);
		g.drawImage(plantImage, x, y, null);
	}
	/**
	 * get the plant's image
	 * @return plant's image
	 */
	public Image getimage() {
		return plantImage;
	}
	
	/**
	 * set it's position
	 * @param x horizontal coordinates 
	 * @param y vertical coordinates
	 */
	public void position(int x, int y) {
		this.x = x;
		this.y = y; 
	}
	
	/**
	 * get plant's cd
	 * @return Integer
	 */
	public int getcd(){
		return CD; 
	}
	
	/**
	 * a controller to decided whether the plant should start to function or not 
	 * @param planted whether it is planted successfully or not 
	 */
	public void setPlanted(boolean planted) {
		this.planted = planted;
	}
	
	/**
	 * what to do if it being eaten by zombies
	 * @param damage the damage of the zombies 
	 */
	public void beeaten(int damage){
		health -= damage; 
	}
	
	/**
	 * get it's size
	 * @return a Rectangle 
	 */
	public Rectangle getre() {
		return new Rectangle(x,y,ConstantData.PLANT_WIDTH,ConstantData.PLANT_HEIGHT); 

	}
	/**
	 * get it's cost 
	 * @return Integer
	 */
	public int getCost() {
		return cost;
	}
}


