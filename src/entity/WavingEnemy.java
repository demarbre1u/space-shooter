package entity;

import data.EntityType;

public class WavingEnemy extends Enemy
{
	public WavingEnemy(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
	}
	
	public void update() 
	{
		speedY = (int) Math.round(Math.sin(x/50) * 2);
		
		super.update();
	}
}
