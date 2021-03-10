package entity;

import org.lwjgl.opengl.GL11;
import data.EntityType;
import main.Main;

/**
 * Enemy class
 */
public class Enemy extends MovingEntity
{
	/**
	 * Enemy constructor
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
	public Enemy(int x, int y, int w, int h, int speedX, int speedY, EntityType type)
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	/**
	 * Renders the entity on the screen
	 */
	@Override
	public void render() 
	{
		drawEnemy();
	}

	/**
	 * Updates the entity data
	 */
	@Override
	public void update() 
	{
		x -= speedX;
		y -= speedY;
		
		if(x < -50)
			Main.toBeDestroyed.add(this);
		else
			updateBoundBox();
	}

	/**
	 * Draws the entity on the screen
	 */
	public void drawEnemy()
	{
		GL11.glColor3f(.8f,.1f,.1f);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x, y + 13);
			GL11.glVertex2f(x + w, y);
			GL11.glVertex2f(x + w, y + h);
			GL11.glVertex2f(x, y + h - 13);
		}
		GL11.glEnd();
	}

	/**
	 * Checks for collisions with a given entity
	 *
	 * @param e
	 * 		The entity to check for collisions
	 */
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
