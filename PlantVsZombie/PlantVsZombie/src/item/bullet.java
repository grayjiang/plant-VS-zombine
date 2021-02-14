package item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import contast.ConstantData;
import father.zombie;
import laungframe.GameMap;


public class bullet {

	private Image bullet; 
	private int damage; 
	private int speed; 
	public int x, y; 
	public GameMap pointer;
	
	/**
	 * initialize
	 * @param bulletImg the image of the bullet 
	 * @param damage the damage value of the bullet 
	 * @param x horizontal  coordinates where it srart 
	 * @param y vertical coordinates where it start 
	 * @param pointer the access to the GameMap class
	 */
	public bullet(Image bulletImg, int damage, int x, int y,GameMap pointer) {
		bullet = bulletImg;
		this.damage = damage;
		this.x = x;
		this.y = y;
		speed = 30;
		this.pointer = pointer; 
	}
	/**
	 * draw the image
	 * @param g the graphics of the screen
	 */
	public void draw(Graphics g){	
		x += speed;
		g.drawImage(bullet, x+20, y+14, null);
	}
	/**
	 * what to do if it hit zombies or not 
	 * @param z the zombie which is being hit or not 
	 * @return true or false 
	 */
	public boolean hit(zombie z){
		if(this.getre().intersects(z.getre())){
			pointer.removebullet(this);
			z.behit(this.damage);
			return true; 
		}
		return false; 
	}
	/**
	 * determined whether bullets hit the zombies 
	 * @param ZS array of the zombies 
	 */
	public void hitthem(List<zombie> ZS){
		int size=ZS.size();
		for(int i=0; i<size; i++){
			if(hit(ZS.get(i))) return;
		}
		if(x > ConstantData.Screen_Width){
			pointer.removebullet(this);
		}
	}
	/**
	 * get its size 
	 * @return Rectangle
	 */
	private Rectangle getre() {
		return new Rectangle(x,y,28,28); 
	}
}
