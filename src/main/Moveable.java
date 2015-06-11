package main;

import processing.core.PApplet;

public class Moveable extends Box{
	
	private boolean inAir;
	private int xSpeed, ySpeed;
	private int gravity;
	Moveable(int x, int y, int w, int h, PApplet app) {
		super(x, y, w, h, app);
		xSpeed = 0;
		ySpeed = 1;
		gravity = 2;
		inAir = true;
		// TODO Auto-generated constructor stub
	}
	public boolean airborne(){
		return inAir;
	}
	public int getXspeed(){
		return xSpeed;
	}
	public int getYspeed(){
		return ySpeed;
	}
	public void setXSpeed(int s){
		xSpeed = s;
	}
	public void setYSpeed(int s){
		ySpeed = s;	
	}
	public void setFloating(boolean a){
		inAir = a;
	}
	public void move(){
		setX(getLeft() + xSpeed);
		setY(getTop() + ySpeed);
	}
	public void fall(){
		setYSpeed(getYspeed()+gravity);
	}
}
