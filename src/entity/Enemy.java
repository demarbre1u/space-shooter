package entity;

import org.lwjgl.opengl.GL11;

import data.EntityType;
import main.Main;

public class Enemy extends MovingEntity
{
	public Enemy(int x, int y, int w, int h, int speedX, int speedY, EntityType type)
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	@Override
	public void render() 
	{
		drawEnemy();
	}

	@Override
	public void update() 
	{
		x -= speedX;
		y -= speedY;
		
		if(x < 0 - 50)
			Main.toBeDestroyed.add(this);
		else
			updateBoundBox();
	}
	
	public void drawEnemy()
	{
		GL11.glColor3f(.8f,.1f,.1f);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x, y+10);
			GL11.glVertex2f(x + w, y);
			GL11.glVertex2f(x + w, y + h);
			GL11.glVertex2f(x, y + h-10);
		}
		GL11.glEnd();
	}

	@Override
	public void checkCollision(MovingEntity e) 
	{
		if(boundBox.intersects(e.boundBox))
		{
			Main.player.gotHit();
			Main.toBeDestroyed.add(this);
			Main.toBeAdded.add(new Explosion(x, y, 8, 8, 2, 0, EntityType.Explosion, .8f, .2f, .2f));
		}
	}
}
