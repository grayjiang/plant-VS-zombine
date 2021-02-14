package laungframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import contast.ConstantData;
import father.Plant;
import father.zombie;
import item.NormalZombie;
import item.bullet;

public class GameMap implements ConstantData{
	
	private Plant[][] plantsArray;
	private List<zombie> zombieArray;
	private List<bullet> bullets;
	private Random r; 
	private int timer; 
	private int kill; 
	private int sunlight;
	private int zombienum; 

	private GameStart game;

	/**
	 * initialize 
	 * @param game the access to the GameStart class
	 */
	public GameMap(GameStart game) {
		sunlight = 50;
		r = new Random();
		plantsArray = new Plant[MAP_COL][MAP_ROW];
		zombieArray = new ArrayList<zombie>();
		bullets = new ArrayList<bullet>();
		timer = -200;
		zombienum = 0;
		kill = 0; 
		this.game = game;
	}
	/**
	 * draw zombies, plants, bullets, sunlight number, kill number   
	 * @param g the Graphics of screen
	 */
	public void draw(Graphics g){
		drawPlant(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Romans",Font.BOLD,15));
		g.drawString("" + sunlight, 40, 78); 	
		drawZombie(g);
		drawbullet(g);
	}
	/**
	 * place the plants in the map 
	 * @param x horizontal coordinate
	 * @param y vertical coordinates
	 * @param p type of plant 
	 */
	public void setplant(int x, int y, Plant p){
		int i = (x - MAP_WEST_OFFSET)/MAP_RECT_WIDTH;
		int j = (y - MAP_TOP_OFFSET)/MAP_RECT_HEIGHT;

		if(plantsArray[i][j] == null){
			sunlight -= p.getCost();
			p.setPlanted(true);
			Plant add = p; 
			plantsArray[i][j] = add;
		}	
	}
	/**
	 * place the plants in the map 
	 * @param x horizontal coordinate
	 * @param y vertical coordinates
	 */
	public void removeplant(int x, int y){
		int i = (x - MAP_WEST_OFFSET)/MAP_RECT_WIDTH;
		int j = (y - MAP_TOP_OFFSET)/MAP_RECT_HEIGHT;
		plantsArray[i][j] = null;
	}
	/**
	 * whether mouse is inside map
	 * @param posX vertical coordinates of mouse 
	 * @param posY vertical coordinates of mouse 
	 * @return true or false
	 */
	public boolean inTheMap(int posX, int posY) {
		
		if ((posX > MAP_WEST_OFFSET) 
				&& (posX < MAP_WEST_OFFSET + MAP_COL * MAP_RECT_WIDTH)
				&& (posY > MAP_TOP_OFFSET)
				&& (posY < MAP_TOP_OFFSET + MAP_ROW * MAP_RECT_HEIGHT)) {
			return true;
		}
		return false;
	}
	/**
	 * draw plant
	 * @param g the Graphics of the screen
	 */
	private void drawPlant(Graphics g) {		
		for (int i = 0; i < MAP_COL ; ++i) {
			for (int j = 0; j < MAP_ROW; ++j) {
				int x = MAP_WEST_OFFSET + i * MAP_RECT_WIDTH;
				int y = MAP_TOP_OFFSET + j * MAP_RECT_HEIGHT;
				Plant p = plantsArray[i][j];
				if (p != null) {
					p.position(x, y);
					p.draw(g);
				}
			}
		}
	}
	
	/**
	 * get sunlight
	 * @return int
	 */
	
	public int getSunlight() {
		return sunlight;
	}
	/**
	 * sunlight which is produced by sunflower is added to total sunlight 
	 * @param ProducedSunlight the sunlight produced by sunflower 
	 */
	public void addSunlight(int ProducedSunlight) {
		sunlight += ProducedSunlight;
	}
	
	/**
	 * create zombies periodly
	 */
	private void createzombie(){
		timer++;
		int random = r.nextInt(100);
		if(random >90 && zombienum < timer/90){
			int y = r.nextInt(370)+80;
			NormalZombie z = new NormalZombie(Screen_Width, y,game, this);	
			zombieArray.add(z);
			zombienum++;
		}
	}
	/**
	 * create zombies and draw them
	 * @param g the Graphics of the screen
	 */
	private void drawZombie(Graphics g){
		createzombie();
		int size = zombieArray.size();
		for(int i=size-1; i>-1; i--){
			zombieArray.get(i).draw(g, zombieArray.get(i).nearplant(plantsArray));
			
		} 
	}
	/**
	 * add zombies into ZombiesArray
	 * @param z the zombies need to be added 
	 */
	public void addzombie(zombie z){
		zombieArray.add(z);
	}
	/**
	 * remove zombies from ZombiesArray
	 * @param z the zombies need to be removed
	 */
	public void removezombie(zombie z){
		zombieArray.remove(z);
	}
	/**
	 * when cherry explode, kill all zombies
	 */
	public void removeallzombie(){
		kill += zombienum;
		zombienum = 0; 
		zombieArray.clear();
	}
	/**
	 * add bullet into bulletArray
	 * @param b the bullet need to be added
	 */
	public void addbullet(bullet b){
		bullets.add(b);
	}
	/**
	 * remove bullet from bulletArray
	 * @param b the bullet need to be removed
	 */
	public void removebullet(bullet b){
		bullets.remove(b);
	}
	/**
	 * draw bullet
	 * @param g the Graphics of the screen
	 */
	public void drawbullet(Graphics g){
		int size = bullets.size();
		for(int i=size-1; i>-1; i--){
			bullets.get(i).draw(g);
			bullets.get(i).hitthem(zombieArray);
			
		}
	}
	/**
	 * add kill number when zombies are killed 
	 */
	public void addKill() {
		kill ++;
		zombienum--;
	}
	
	/**
	 * The access to get the kill number
	 * @return Integer 
	 */
	public int getkill(){
		return kill; 
	}

}
