package entity;

import org.lwjgl.opengl.GL11;

import data.Drawer;
import data.EntityType;
import main.Main;

public class Star extends MovingEntity
{

	public Star(int x, int y, int w, int h, int speed, EntityType type) 
	{
		super(x, y, w, h, speed, type);
	}

	@Override
	public void render() 
	{
		drawStar();
	}

	@Override
	public void update() 
	{
		x -= speed;
		
		if(x < -100)
			Main.toBeDestroyed.add(this);
	}

	private void drawStar() 
	{
		GL11.glColor3f(.3f, .3f, .3f);
		Drawer.drawRect(x, y, w, h);
		Drawer.drawRect(x + w, y + h, w, h);
		Drawer.drawRect(x + w*2, y, w, h);
		Drawer.drawRect(x + w, y, w, -h);
	}

	
	@Override
	public void checkCollision(MovingEntity e) {}

}
