package entity;

import java.awt.Rectangle;

import data.EntityType;
import main.Main;

public abstract class MovingEntity 
{
	protected int x, y, w, h, speedX, speedY;
	protected EntityType type; 
	protected Rectangle boundBox;
	
	public MovingEntity(int x, int y, int w, int h, int speedX, int speedY, EntityType type)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.speedX = speedX;
		this.speedY = speedY;
		boundBox = new Rectangle(x, y, w, h);
		this.type = type;
	}
	
	public abstract void render();
	
	public abstract void update();

	public EntityType getType() 
	{
		return type;
	}

	public void updateBoundBox()
	{
		boundBox = new Rectangle(x, y, w, h);
	}
	
	public abstract void checkCollision(MovingEntity e);	
}
