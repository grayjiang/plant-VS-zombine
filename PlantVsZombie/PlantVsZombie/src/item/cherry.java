package item;

import java.awt.Color;
import java.awt.Graphics;

import contast.ConstantData;
import contast.image;
import father.Plant;
import laungframe.GameMap;

public class cherry extends Plant implements ConstantData {
	
	/**
	 * initialize
	 * @param map the access to the GameMap class
	 */
	public cherry(GameMap map){
    	plantImage = image.getImage("cherry.png");
    	frequency = (int)(1.0 * DEFAULT_FPS);
    	counter = 0; 
    	maxhealth = 100;
    	cost = 1500;
    	health = 100;
    	CD = 90;
    	planted = false;
    	pointer = map;
	}
	/**
	 * rewrite the draw method and allow image to expand 
	 */
	@Override
	public void draw(Graphics g) {
		counter++;
		if(counter < frequency){
			g.setColor(Color.RED);
			g.drawRect(x+15, y-12, 50, 10);
			g.fillRect(x+15, y-12, ((int)(50.0/maxhealth*health)), 10);
		}else{
			if (counter==frequency) {
				plantImage = image.getImage("boom.png");
				pointer.removeallzombie();
			}else{
				if(counter==frequency+3){ 
					pointer.removeplant(x, y);
					return; 
				}
			}
		}
		g.drawImage(plantImage, x-counter,y-counter, x+PLANT_WIDTH+counter, y+PLANT_HEIGHT+counter, 
				0, 0, PLANT_WIDTH, PLANT_HEIGHT, null);
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		
	}
}
