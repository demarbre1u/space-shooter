package entity;

import org.lwjgl.opengl.GL11;
import data.EntityType;
import helper.Drawer;
import main.Main;

/**
 * Explosion class
 */
public class Explosion extends MovingEntity
{
	/**
	 * The distance reached by the explosion
	 */
	private int distance;

	/**
	 * The (red, green, blue) colors of the explosion
	 */
	final private float r, g, b;

	/**
	 * Explosion constructor
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
	public Explosion(int x, int y, int w, int h, int speedX, int speedY, EntityType type, float r, float g, float b) 
	{
		super(x, y, w, h, speedX, speedY, type);
		distance = 0;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * Renders the entity on the screen
	 */
	@Override
	public void render() 
	{
		drawParticles();
	}

	/**
	 * Update the entity data
	 */
	@Override
	public void update() 
	{
		distance++;
		
		if(distance >= 20)
			Main.toBeDestroyed.add(this);
	}

	/**
	 * Checks for collisions with a given entity
	 *
	 * @param e
	 * 		The entity to check for collisions
	 */
	@Override
	public void checkCollision(MovingEntity e) {}

	/**
	 * Draws the particles of the explosion on screen
	 */
	public void drawParticles()
	{
		GL11.glColor3f(r, g, b);
		Drawer.drawRect(x + distance, y + distance, w, h);
		Drawer.drawRect(x, y + distance*1.5f, w, h);
		Drawer.drawRect(x + distance*1.5f, y, w, h);
		Drawer.drawRect(x, y - distance*1.5f, w, h);
		Drawer.drawRect(x - distance, y - distance, w, h);
		Drawer.drawRect(x - distance*1.5f, y, w, h);
		Drawer.drawRect(x - distance, y + distance, w, h);
		Drawer.drawRect(x + distance, y - distance, w, h);
	}
}
