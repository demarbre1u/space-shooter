package entity;

import data.EntityType;

/**
 * WavingEnemy class
 */
public class WavingEnemy extends Enemy
{
	/**
	 * Waving enemy constructor
	 *
	 * @param x
	 * 		The X position of the entity on the screen
	 * @param y
	 * 		The y position of the entity on the screen
	 * @param w
	 * 		The width of the entity
	 * @param h
	 * 		The height of the entity
	 * @param speedX
	 * 		The speed of the entity on the X axis
	 * @param speedY
	 * 		The speed of the entity on the Y axis
	 * @param type
	 * 		The type of the entity
	 */
	public WavingEnemy(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	/**
	 * Updates the entity data
	 */
	public void update() 
	{
		speedY = (int) Math.round(Math.sin(x/50.0) * 2);
		
		super.update();
	}
}
