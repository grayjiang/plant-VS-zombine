package father;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import contast.ConstantData;
import laungframe.GameMap;
import laungframe.GameStart;

public abstract class zombie implements ConstantData{
	public Image[] ZombieImage;
	public int health;
	public int maxhealth;
	public int damage; 
	public int speed;
	public int x, y; 
	public int step;
	public GameStart game;
	public GameMap pointer;
	
	/**
	 * draw the zombies
	 * @param g the graphics of the screen
	 * @param near whether the zombies are near the plant or not 
	 */
	public void draw(Graphics g, Boolean near) {
		if(health <1){
			pointer.removezombie(this);
			pointer.addKill();
			return; 					
		} 
		if(!near)x -=speed;
		g.setColor(Color.RED);
		g.drawRect(x, y-12, 50, 10);
		g.fillRect(x, y-12, ((int)(50.0/maxhealth*health)), 10);
		g.drawImage(ZombieImage[step], x, y, null);
		step++;
		if(step == ZombieImage.length) step =0; 
		if(x<0) game.lose();
	}
	/**
	 * get its size
	 * @return a Rectangle
	 */
	public Rectangle getre() {
		return new Rectangle(x,y,ConstantData.Normal_Zombie_HEIGHT,ConstantData.Normal_Zombie_WIDTH); 
	}
	/**
	 * what to do if being hit by bullet 
	 * @param damage the damage vlaue of the bullet
	 */
	public void behit(int damage) {
		health -= damage; 
	}
	/**
	 * what to do if zombies hit plants
	 * @param p the plant which is selected 
	 * @return true or false 
	 */
	public boolean attackplant(Plant p) {
		if(this.getre().intersects(p.getre())){
			p.beeaten(damage);
			return true; 
		}else return false; 
	}
	
	/**
	 * determined whether zombies is near the plant
	 * @param plantsArray the array of the plant 
	 * @return true of false 
	 */
	public boolean nearplant(Plant[][] plantsArray){
		for (int i = 0; i < MAP_COL ; ++i) {
			for (int j = 0; j < MAP_ROW; ++j) {
				if (plantsArray[i][j] != null) {
					if(attackplant(plantsArray[i][j]))return true; 
				}
			}
		}
		return false; 
	}
	
}
