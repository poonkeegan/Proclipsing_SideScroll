package main;

import processing.core.PApplet;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main extends PApplet {

	/*
	6/3/2015
	Keegan Poon
	Testing collision detection
	 */
	int windowHeight, windowWidth, parallax;
	boolean up, down, right, left;
	Moveable character;
	Box [] platform;
	Background [] bg;

	public Box[] loadPlatforms(){
		Box [] platforms;
		try{
			File initFile = new File("Initialization\\Boxes.kelp");
			Scanner sc = new Scanner(initFile);
			int size = 0;
			while(sc.hasNextLine()){
				size++;
				sc.nextLine();
			}
			sc.close();
			sc = new Scanner(initFile);
			platforms = new Box [size];
			for(int i = 0; i < platforms.length; i++){
				int x = sc.nextInt();
				int y = sc.nextInt();
				int w = sc.nextInt();
				int h = sc.nextInt();
				platforms[i] = new Box(x,y,w,h,this);
			}
			sc.close();
		}catch(Exception e){
			e.printStackTrace();
			platforms = new Box [1];
			platforms[0] = new Box (0,0,windowWidth, windowHeight, this);
		}
		return platforms;
	}

	public Background[] loadBg(){
		Background [] background;
		try{
			File initFile = new File("Initialization\\Background.kelp");
			Scanner sc = new Scanner(initFile);
			int size = 0;
			while(sc.hasNextLine()){
				size++;
				sc.nextLine();
			}
			sc.close();
			sc = new Scanner(initFile);
			background = new Background [size];
			for(int i = 0; i < background.length; i++){
				int backgroundLevel = (i-(background.length - 2))*-1;
				String path = sc.nextLine();
				background[i] = new Background(
						windowHeight - 40*backgroundLevel,
						this, 
						loadImage("Backgrounds\\"+path));
			}
			sc.close();
		}catch(Exception e){
			e.printStackTrace();
			background = new Background [1];
			background[0] = new Background (this ,createImage(0, 0, RGB));
		}
		return background;
	}

	public void setup() {
		windowWidth = 3000;
		windowHeight = 1024;
		size(windowWidth, windowHeight);
		frameRate(60);
		character = new Moveable(400,0,20,10,this);
		platform = loadPlatforms();
		parallax = 0;
		bg = loadBg();
	}



	//infinite ground -10000 500 20000 20
	public void draw(){
		background(135, 206, 235); 
		renderBg();
		fill(255);
		character.move();
		keyCheck();
		character = updateMoveable(character);
		for(int i = 0; i < platform.length; i++){
			platform[i].display(parallax);
		}
		character.display(parallax);
		parallaxCheck();

	}

	public void renderBg(){
		int i = 5;
		//for(int i = 0; i < bg.length; i++)
			bg[i].display(parallax/(1+ Math.abs(i-bg.length)/2));
	}

	public void parallaxCheck(){
		if(character.getRight() > (windowWidth*4/5 + parallax))
			parallax = character.getRight() - windowWidth*4/5 ;
		else if(character.getLeft() < (windowWidth/5 + parallax))
			parallax = character.getLeft() - windowWidth/5 ;

	}

	public void keyCheck(){
		if(up && !character.airborne()){
			character.setYSpeed(-20);
			character.setFloating(true);
		}
		if(!character.airborne()){
			if(left)
				character.setXSpeed(-10);
			else if(right)
				character.setXSpeed(10);
			else
				character.setXSpeed(0);
		}
	}

	public Moveable updateMoveable(Moveable m){
		m.setFloating(true);
		for(int i = 0; i < platform.length; i++){
			switch(platform[i].colliding(m)){
			case UP:
				m.setBot(platform[i].getTop());
				m.setYSpeed(0);
				m.setFloating(false);
				break;
			case DOWN:
				m.setY(platform[i].getBot());
				m.setYSpeed(0);
				break;
			case LEFT:
				m.setRight(platform[i].getLeft());
				m.setXSpeed(0);
				break;
			case RIGHT:
				m.setX(platform[i].getRight());
				m.setXSpeed(0);
				break;
			default:
				break;
			}
		}
		if(m.airborne())
			m.fall();
		return m;
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { main.Main.class.getName() });
	}

	public void keyPressed()
	{
		if (key == CODED) {
			switch (keyCode) {
			case UP:
				up = true;
				break;
			case DOWN:
				down = true;
				break;
			case LEFT:
				left = true;
				break;
			case RIGHT:
				right = true;
				break;
			}
		}
	}
	public void keyReleased() 
	{
		if (key == CODED) {
			switch (keyCode) {
			case UP:
				up = false;
				break;
			case DOWN:
				down = false;
				break;
			case LEFT:
				left = false;
				break;
			case RIGHT:
				right = false;
				break;
			}

		}
	}

}
