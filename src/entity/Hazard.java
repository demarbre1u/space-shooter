package entity;

import org.lwjgl.opengl.GL11;
import data.EntityType;
import helper.Drawer;
import main.Main;

/**
 * Hazard class
 */
public class Hazard extends MovingEntity
{
	/**
	 * Hazard constructor
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
	public Hazard(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	/**
	 * Renders the entity on the screen
	 */
	@Override
	public void render() 
	{
		drawHazard();
	}

	/**
	 * Updates the entity data
	 */
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

	/**
	 * Draws this entity to the screen
	 */
	private void drawHazard()
	{
		GL11.glColor3f(.8f, .4f, .2f);
		Drawer.drawRect(x + 10, y, w - 20, h);
		Drawer.drawRect(x, y + 10, w, h - 20);
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
			Main.toBeAdded.add(new Explosion(x, y, 8, 8, 2, 0, EntityType.Explosion, .8f, .4f, .2f));
			Main.toBeDestroyed.add(this);
		}
	}
}
