package entity;

import org.lwjgl.opengl.GL11;

import data.Drawer;
import data.EntityType;
import main.Main;

public class Explosion extends MovingEntity
{
	private int distance;
	private float r, g, b;
	
	public Explosion(int x, int y, int w, int h, int speed, EntityType type, float r, float g, float b) 
	{
		super(x, y, w, h, speed, type);
		distance = 0;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public void render() 
	{
		drawParticles();
	}

	@Override
	public void update() 
	{
		distance++;
		
		if(distance >= 20)
			Main.toBeDestroyed.add(this);
	}

	@Override
	public void checkCollision(MovingEntity e) {}

	public void drawParticles()
	{
		GL11.glColor3f(r, g, b);
		Drawer.drawRect(x + distance, y + distance, w, h);
		Drawer.drawRect(x, y + distance*1.5f, w, h);
		Drawer.drawRect(x + distance*1.5f, y, w, h);
		Drawer.drawRect(x, y - distance*1.5f, w, h);
		Drawer.drawRect(x - distance, y - distance, w, h);
		Drawer.drawRect(x - distance*1.5f, y, w, h);
		Drawer.drawRect(x - distance, y + distance, w, h);
		Drawer.drawRect(x + distance, y - distance, w, h);
	}
}
