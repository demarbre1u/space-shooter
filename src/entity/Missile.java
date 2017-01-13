package entity;

import org.lwjgl.opengl.GL11;

import data.Drawer;
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
		Drawer.drawRect(x, y, w, h);
	}

	@Override
	public void checkCollision(MovingEntity enemy) 
	{
		if(boundBox.intersects(enemy.boundBox))
		{
			if(enemy.getType() == EntityType.Enemy)
			{
				Main.toBeAdded.add(new Explosion(enemy.x, enemy.y, 8, 8, 2, EntityType.Explosion, .8f, .2f, .2f));
				Main.toBeDestroyed.add(this);
				Main.toBeDestroyed.add(enemy);
				Main.score += 20;
			}
			Main.toBeDestroyed.add(this);
		}
	}
}
