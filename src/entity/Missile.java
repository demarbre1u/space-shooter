package entity;

import org.lwjgl.opengl.GL11;
import data.EntityType;
import helper.Drawer;
import main.Main;

/**
 * Missile class
 */
public class Missile extends MovingEntity 
{
	/**
	 * Missile constructor
	 *
	 * @param x
	 * 		The X position of the entity on the screen
	 * @param y
	 * 		The Y position of the entity on the screen
	 * @param w
	 * 		The width of the entity
	 * @param h
	 * 		The height of the entity
	 * @param speedX
	 *		The speed of the entity on the X axis
	 * @param speedY
	 * 		The speed of the entity on the Y axis
	 * @param type
	 * 		The type of the entity
	 */
	public Missile(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	/**
	 * Renders the entity on the screen
	 */
	@Override
	public void render() 
	{
		drawMissile();
	}

	/**
	 * Updates the entity data
	 */
	@Override
	public void update() 
	{
		x += speedX;
		y += speedY;
		
		if(x > Main.WIDTH + 50)
			Main.toBeDestroyed.add(this);
		else
			updateBoundBox();
	}

	/**
	 * Draws the missile on the screen
	 */
	public void drawMissile()
	{
		GL11.glColor3f(1,1,1);
		Drawer.drawRect(x, y, w, h);
	}

	/**
	 * Checks for collisions with a given entity
	 *
	 * @param enemy
	 * 		The entity to check for collisions
	 */
	@Override
	public void checkCollision(MovingEntity enemy) 
	{
		if(boundBox.intersects(enemy.boundBox))
		{
			if(enemy.getType() == EntityType.Enemy)
			{
				Main.toBeAdded.add(new Explosion(enemy.x, enemy.y, 8, 8, 2, 0, EntityType.Explosion, .8f, .2f, .2f));
				Main.toBeDestroyed.add(this);
				Main.toBeDestroyed.add(enemy);
				Main.score += 20;
			}
			Main.toBeDestroyed.add(this);
		}
	}
}
