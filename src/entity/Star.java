package entity;

import org.lwjgl.opengl.GL11;

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
		
		if(x < 0 -100)
			Main.toBeDestroyed.add(this);
	}

	private void drawStar() 
	{
		GL11.glColor3f(.3f, .3f, .3f);
	
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + w, y);
			GL11.glVertex2f(x + w, y + h);
			GL11.glVertex2f(x, y + h);
			
			GL11.glVertex2f(x+w, y+h);
			GL11.glVertex2f(x + w*2, y+h);
			GL11.glVertex2f(x + w*2, y + h*2);
			GL11.glVertex2f(x+w, y + h*2);
			
			GL11.glVertex2f(x + w*2, y+h);
			GL11.glVertex2f(x + w*3, y+h);
			GL11.glVertex2f(x + w*3, y);
			GL11.glVertex2f(x + w*2, y);
			
			GL11.glVertex2f(x + w, y);
			GL11.glVertex2f(x + w*2, y);
			GL11.glVertex2f(x + w*2, y-h);
			GL11.glVertex2f(x + w, y-h);
			
			
		}
		GL11.glEnd();
	}

	
	@Override
	public void checkCollision(MovingEntity e) {}

}
