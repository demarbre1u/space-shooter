package entity;

import org.lwjgl.opengl.GL11;

import data.EntityType;
import helper.Drawer;
import main.Main;

public class Hazard extends MovingEntity
{
	public Hazard(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	@Override
	public void render() 
	{
		drawHazard();
	}

	@Override
	public void update() 
	{
		x -= speedX;
		y -= speedY;
		
		if(x < -100)
			Main.toBeDestroyed.add(this);
		else
			updateBoundBox();
	}

	private void drawHazard()
	{
		GL11.glColor3f(.8f, .4f, .2f);
		Drawer.drawRect(x + 10, y, w - 20, h);
		Drawer.drawRect(x, y + 10, w, h - 20);
	}
	
	@Override
	public void checkCollision(MovingEntity e) 
	{	
		if(boundBox.intersects(e.boundBox))
		{
			Main.player.gotHit();
			Main.toBeAdded.add(new Explosion(x, y, 8, 8, 2, 0, EntityType.Explosion, .8f, .4f, .2f));
			Main.toBeDestroyed.add(this);
		}
	}	

}
