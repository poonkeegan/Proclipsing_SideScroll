package main;

import processing.core.PApplet;

public class Box extends PApplet{

	private int xPos, yPos, xSize, ySize, xOld, yOld;
	PApplet p;
	Box(int x, int y, int w, int h, PApplet app){
		xPos = x;
		yPos = y;
		xSize = w;
		ySize = h;
		xOld = x;
		yOld = y;
		p = app;
	}
	Box(PApplet app){
		xPos = 0;
		yPos = 0;
		xSize = 0;
		ySize = 0;
		xOld = 0;
		yOld = 0;
		p = app;
	}
	public void display(){
		p.rect(xPos,yPos,xSize,ySize);
	}
	public void display(int parallax){
		p.rect(xPos - parallax,yPos,xSize,ySize);
	}
	//checks where the Box B hits this box
	public Direction colliding(Box b){
		//vertical check
		if(	b.getRight() >= this.getLeft() && 
			b.getLeft() <= this.getRight()){
			
			if(		b.getOldBot() <= this.getTop() && 
					b.getOldTop() < b.getTop() && 
					b.getBot() >= this.getTop())
				return Direction.UP;
			
			else if(b.getOldTop() >= this.getBot() && 
					b.getOldTop() > b.getTop() && 
					b.getTop() <= this.getBot())
				return Direction.DOWN;
		}
		
		//horizontal check
		if(	b.getTop() <= this.getBot() && 
			b.getBot() >= this.getTop()){
			
			if(		b.getOldRight() <= this.getLeft() && 
					b.getOldRight() < b.getRight() && 
					b.getRight() >= this.getLeft())
				return Direction.LEFT;
			
			else if(b.getOldLeft() >= b.getRight() && 
					b.getOldLeft() > b.getLeft() && 
					b.getLeft() <= this.getRight())
				return Direction.RIGHT;
		}
		return Direction.NONE;
	}
	public int width(){
		return xSize;
	}
	public int height(){
		return ySize;
	}
	public int getTop(){
		return yPos;
	}
	public int getBot(){
		return yPos + ySize;
	}
	public int getLeft(){
		return xPos;
	}
	public int getRight(){
		return xPos + xSize;
	}
	public int getOldTop(){
		return yOld;
	}
	public int getOldBot(){
		return yOld + ySize;
	}
	public int getOldLeft(){
		return xOld;
	}
	public int getOldRight(){
		return xOld + xSize;
	}
	public int radius(){
		return (int)sqrt(sq(xSize/2) + sq(ySize/2));
	}
	public void setX(int x){
		xOld = xPos;
		xPos = x;
	}
	public void setY(int y){
		yOld = yPos;
		yPos = y;
	}
	public void setRight(int x){
		xOld = x;
		xPos = x - width();
	}
	public void setBot(int y){
		yOld = y;
		yPos = y - height();
	}
	public void setWidth(int w){
		xSize = w;
	}
	public void setHeight(int h){
		ySize = h;
	}
	
}

