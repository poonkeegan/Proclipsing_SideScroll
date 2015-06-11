package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Background extends Box{
	
	PImage sprite;
	private int renderPos;
	Background(PApplet app, PImage img) {
		super(app);
		sprite = img;
		setWidth(img.width);
		setHeight(img.height);
		setX(p.width/2 - width()/2);
		setBot(p.height);
		renderPos = 0;
	}
	
	Background(int midY, PApplet app, PImage img) {
		super(app);
		sprite = img;
		setWidth(img.width);
		setHeight(img.height);
		setX(p.width/2 - width()/2);
		setBot(midY);
		renderPos = 0;
	}
	public void display(int parallax){
		/*System.out.print((getLeft()- width()*2/3) + width()*2/3*renderPos);
		System.out.print(", ");
		System.out.print((getLeft())+ width()*renderPos);
		System.out.print(", ");
		System.out.print((getLeft()  + width()*2/3)+ width()*2/3*renderPos);
		System.out.println();*/
		
		if(((getLeft() - parallax)+ width()*renderPos) + width() < width()/3){
			renderPos++;
		}else if((getLeft() - parallax)+ width()*renderPos > width()*(2/3.0)){
			renderPos--;
		}
		int parallaxLeft = getLeft() - parallax;
		int renderAdjust = (width()*renderPos);
		p.image(sprite,
				(parallaxLeft - width()*2/3) + 	renderAdjust,
				getTop());
		p.image(sprite,
				(parallaxLeft) +				renderAdjust,
				getTop());
		p.image(sprite,
				(parallaxLeft  + width()*2/3)+ 	renderAdjust,
				getTop());
	}

}
