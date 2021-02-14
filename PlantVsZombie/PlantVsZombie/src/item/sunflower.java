package item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import contast.ConstantData;
import contast.image;
import father.Plant;
import laungframe.GameMap;

public class sunflower extends Plant implements ConstantData {
   
	/**
	 * initialize
	 * @param pointer: the access of the GameMap
	 */
	public sunflower(GameMap pointer){
		plantImage = image.getImage("sunflower.png");
    	frequency = (int)(5.0 * DEFAULT_FPS);
    	health = 40;
    	maxhealth = 40;
    	cost = 50;
    	CD = 5;
    	planted = false;
    	this.pointer = pointer;
	}
	
	/**
	 * produce 25 sunlights in every 5 sec
	 */
	@Override
	public void fire() {
		if(planted){
			counter++; 
			if (counter==frequency) {
				pointer.addSunlight(25);
				plantImage = image.getImage("sunflower2.png");
				counter = 0;
			}
			if(counter == 3)plantImage = image.getImage("sunflower.png");
		}
		
	}
	
}
