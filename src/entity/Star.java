package entity;

import org.lwjgl.opengl.GL11;
import data.EntityType;
import helper.Drawer;
import main.Main;

/**
 * Star class
 */
public class Star extends MovingEntity
{
	/**
	 * Star constructor
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
	public Star(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	/**
	 * Renders the entity on screen
	 */
	@Override
	public void render() 
	{
		drawStar();
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
	}

	/**
	 * Draws the entity on screen
	 */
	private void drawStar() 
	{
		GL11.glColor3f(.3f, .3f, .3f);
		Drawer.drawRect(x, y, w, h);
		Drawer.drawRect(x + w, y + h, w, h);
		Drawer.drawRect(x + w*2, y, w, h);
		Drawer.drawRect(x + w, y, w, -h);
	}

	/**
	 * Checks if this entity is colliding with an other entity
	 *
	 * @param e
	 * 		The entity to check collision with
	 */
	@Override
	public void checkCollision(MovingEntity e) {}
}
