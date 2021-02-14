package item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import contast.ConstantData;
import father.Plant;
import laungframe.GameMap;

public class seed{
	
	private Image imageA; 
	private Image imageB;
	private Plant p;
	private ConstantData.planttype type; 
	private int cd; 
	private int count; 
	private boolean enable; 
	private float percent;
	private GameMap pointer; 
	
	private int x; 
	private int y;
	
	/**
	 * initialize
	 * @param A image's A side
	 * @param B Image's B side
	 * @param type plant type
	 * @param pointer the access of the GameMap
	 */
	public seed(Image A, Image B, ConstantData.planttype type, GameMap pointer){
		imageA = A;
		imageB = B;
		this.type = type;
		cd = getcd();
		cd = cd* ConstantData.DEFAULT_FPS;
		count = 0;
		enable = false; 
		percent = 0.5f;
		this.pointer = pointer;

		
	}
	/**
	 * set its position
	 * @param x horizontal coordinates
	 * @param y vertical coordinates
	 */
	public void position(int x, int y){
		this.x = x; 
		this.y = y; 
	}
	/**
	 * while user place the plants correct, set IDcard as used 
	 */
	public void planted(){
		enable = false; 
		count = 0; 
	}
	
	/**
	 * draw the IDcard
	 * @param g The Grahics of the screen 
	 */
	public void draw(Graphics g) {
		g.drawImage(imageA, x, y, null);
		DrawBSide(g);
	}
	/**
	 * update the IDcard 
	 */
	private void update(){		
		if (count >= cd) {
			enable = true; 
			return;
		}else{
			count++;
			percent = ((float)count) / (cd);
		}
	}
	/**
	 * draw Bside of the IDcard while IDcard is used 
	 */
	private void DrawBSide(Graphics g){
		update();
		if(enable == true){
			return; 
		}
		int topH = (int) (ConstantData.CARD_HEIGHT * percent);
		g.drawImage(imageB, x,y, x+ConstantData.CARD_WIDTH, y+ConstantData.CARD_HEIGHT-topH, 
			0, 0, ConstantData.CARD_WIDTH, ConstantData.CARD_HEIGHT-topH, null);
	}
	/**
	 * detect whether the mouse is inside or not
	 * @param x1 horizontal coordinates of mouse
	 * @param y1 vertical coordinates of mouse 
	 * @return true or false 
	 */
	public boolean mouseIn(int x1, int y1) {
		if ((x1 > x) && (x1 <x + ConstantData.CARD_WIDTH) && (y1 > y) && (y1 < y + ConstantData.CARD_HEIGHT)){
			if(enable == true) return true;
			else{
				Toolkit.getDefaultToolkit().beep();
			}
		}
		return false;
	}
	/**
	 * set CD depends on the type of  plants 
	 * @return
	 */
	private int getcd(){
		switch(type){
		case single: return new peashooter(pointer).getcd();
		case sunflower:return new sunflower(pointer).getcd();
		case nutwall: return new nutwall(pointer).getcd();
		case cherry:  return new cherry(pointer).getcd();
		}
		return 0;
	}
	/**
	 * give user the plant they want
	 * @return plant
	 */
	public Plant getplant(){
		switch(type){
		case single: p = new peashooter(pointer);
			break;
		case sunflower: p = new sunflower(pointer);
			break;
		case nutwall: p = new nutwall(pointer);
			break;
		case cherry:  p = new cherry(pointer);
			break;
		}
		if(p.getCost() > pointer.getSunlight()){
			Toolkit.getDefaultToolkit().beep();
			return null;
		}else {
			return p;
		}
	}
}
	