package item;

import contast.ConstantData;
import contast.image;
import father.Plant;
import laungframe.GameMap;

public class nutwall extends Plant implements ConstantData{
	/**
	 * initialize
	 * @param pointer the access of the GameMap
	 */
	public nutwall(GameMap pointer){
		plantImage = image.getImage("nutwall1.png");
    	health = 800;
    	maxhealth = 800;
    	cost = 50;
    	CD = 15;
    	planted = false;
    	this.pointer = pointer;
	}
	
	/**
	 * change image when health change
	 */
	@Override
	public void fire() {
		if(400<health && health <600) plantImage = image.getImage("nutwall2.png");
		if(health < 400)  plantImage = image.getImage("nutwall3.png");
	}
}
