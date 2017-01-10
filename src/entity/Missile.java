package entity;

import org.lwjgl.opengl.GL11;

import data.EntityType;
import main.Main;

public class Missile extends MovingEntity 
{

	public Missile(int x, int y, int w, int h, int speed, EntityType type) 
	{
		super(x, y, w, h, speed, type);
	}

	@Override
	public void render() 
	{
		drawMissile();
	}

	@Override
	public void update() 
	{
		x += speed;
		
		if(y > Main.WIDTH + 50)
			Main.toBeDestroyed.add(this);
		else
			updateBoundBox();
	}

	public void drawMissile()
	{
		GL11.glColor3f(1,1,1);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + w, y);
			GL11.glVertex2f(x + w, y + h);
			GL11.glVertex2f(x, y + h);
		}
		GL11.glEnd();
	}

	@Override
	public void checkCollision(MovingEntity enemy) 
	{
		if(boundBox.intersects(enemy.boundBox))
		{
			Main.toBeDestroyed.add(this);
			Main.toBeDestroyed.add(enemy);
			
			Main.score += 20;
		}
	}
}
