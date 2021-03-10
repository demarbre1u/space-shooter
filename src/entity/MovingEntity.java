package entity;

import java.awt.Rectangle;
import data.EntityType;

/**
 * MovingEntity abstract class
 */
public abstract class MovingEntity 
{
	/**
	 * X and Y positions of the entity, speed on the X and Y axis
	 */
	protected int x, y, w, h, speedX, speedY;

	/**
	 * The type of the entity
	 */
	protected EntityType type;

	/**
	 * The boundbox of the entity
	 */
	protected Rectangle boundBox;

	/**
	 * MovingEntity constructor
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
	public MovingEntity(int x, int y, int w, int h, int speedX, int speedY, EntityType type)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.speedX = speedX;
		this.speedY = speedY;
		boundBox = new Rectangle(x, y, w, h);
		this.type = type;
	}

	/**
	 * Renders the entity on the screen
	 */
	public abstract void render();

	/**
	 * Updates the entity data
	 */
	public abstract void update();

	/**
	 * Returns the type of the entity
	 *
	 * @return
	 * 		The type of the entity
	 */
	public EntityType getType() 
	{
		return type;
	}

	/**
	 * Updates the position of the bound box
	 */
	public void updateBoundBox()
	{
		boundBox = new Rectangle(x, y, w, h);
	}

	/**
	 * Checks for collisions with a given entity
	 *
	 * @param e
	 * 		The entity to check for collisions with
	 */
	public abstract void checkCollision(MovingEntity e);	
}
