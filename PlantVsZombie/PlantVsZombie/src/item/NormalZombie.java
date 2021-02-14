package item;
import java.awt.Image;
import contast.image;
import father.zombie;
import laungframe.GameMap;
import laungframe.GameStart;

public class NormalZombie extends zombie {
	
	/**
	 * initialize zombies
	 * @param x horizontal coordinates
	 * @param y vertical coordinates
	 * @param game the access of the GameStart
	 * @param pointer the access of the GameMap
	 */
	public NormalZombie(int x, int y, GameStart game, GameMap pointer){
		ZombieImage = new Image[]{image.getImage("Zombie.png"), 
				image.getImage("Zombie2.png"), 
				image.getImage("Zombie3.png"), 	
				image.getImage("Zombie4.png"), 	
		};
		damage = 1;
		maxhealth = 40;
		health = 40;
		speed = 2;
		step = 0;
		this.x = x; 
		this.y = y;
		this.game = game; 
		this.pointer = pointer; 
	}
}
