package com.ruffles.galaga;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.sun.javafx.geom.Point2D;


/*
 * Abstract class, which models enemies in general
 */
public abstract class Enemy {

	int hitpoints;
	
	
	Rectangle bounds;

	int posX = 0;
	int posY = 0;
	boolean hit = false;
	
	ShapeRenderer shaperenderer;
	
	public enum State {FLYINGLEFT, FLYINGRIGHT, IDLE, ARRIVING};
	State currentState = State.ARRIVING;
	
	GameScreen gameScreen;
	

	public Enemy(int xSpawn, int ySpawn, GameScreen gameScreen){
		bounds = new Rectangle(xSpawn, ySpawn, 20, 20);
		
		posX = xSpawn;
		posY = ySpawn;
		
		shaperenderer = new ShapeRenderer();
		shaperenderer.setAutoShapeType(true);
		shaperenderer.begin();
		shaperenderer.setColor(Color.RED);
		shaperenderer.end();	
		
		this.gameScreen = gameScreen;
	}
	
	float timer = 0;


	private ArrayList<Point2D> currentPath = new ArrayList<Point2D>();
	int pathStep = 0;
	public void update(float delta){
		
		timer += delta;
		if(timer > 0.03){
			
			if(currentState == State.FLYINGLEFT)
				setPosX(getPosX() - 10);
			if(currentState == State.FLYINGRIGHT)
				setPosX(getPosX() + 10);
			
			if(currentState == State.ARRIVING && pathStep < currentPath.size()){
				posX = (int) currentPath.get(pathStep).x;
				posY = (int) currentPath.get(pathStep).y;
				pathStep++;
			}
			
			timer = 0;
		}
		
		bounds.setPosition(getPosX(), getPosY());
	}
	
	
	public State getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
	public ShapeRenderer getShaperenderer() {
		return shaperenderer;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setHit(boolean b) {
		hit = b;
	}


	public void startFlyInLeft(int offset) {
		currentPath = gameScreen.getPathLeft();
		currentPath.add(new Point2D((currentPath.get(currentPath.size()-1).x + (offset * 30)), currentPath.get(currentPath.size()-1).y));
		System.out.println(currentPath.get(currentPath.size()-1).x);
		//TODO fix this^
	}


	public void startFlyInRight() {
		
	}
	
}
