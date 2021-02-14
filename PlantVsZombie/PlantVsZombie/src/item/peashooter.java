package item;

import contast.ConstantData;
import contast.image;
import father.Plant;
import laungframe.GameMap;

public class peashooter extends Plant implements ConstantData{
	/**
	 * initialize
	 * @param map the access to the GameMap class
	 */
	public peashooter(GameMap map){
    	plantImage = image.getImage("single.png");
    	bulletImage = image.getImage("bullet.png");
    	damage = 6; 
    	frequency = (int)(2.4 * DEFAULT_FPS);
    	counter = 0; 
    	maxhealth = 50;
    	cost = 100;
    	health = 50;
    	CD = 7;
    	planted = false;
    	pointer = map;
	}
	
	/**
	 * produce bullet to defend zombies
	 */
	@Override
	public void fire() {
		if(planted){
			counter++; 
			if (counter==frequency) {
				bullet b = new bullet(bulletImage, damage, x, y, pointer); 
				pointer.addbullet(b);
				counter = 0;
			}
		}
	}
}
