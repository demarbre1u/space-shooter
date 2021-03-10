package weapon;

import data.EntityType;
import entity.Missile;
import main.Main;

/**
 * Weapon class
 */
public class Weapon 
{
	/**
	 * The minimum delay to wait before the next shot
	 */
	protected int shotDelay;

	/**
	 * Weapon constructor
	 */
	public Weapon()
	{
		shotDelay = 15;
	}

	/**
	 * Spawns a missile
	 *
	 * @param x
	 * 		The x position of the missile
	 * @param y
	 * 		The y position of the missile
	 * @param w
	 * 		The width of the missile
	 * @param h
	 * 		The height of the missile
	 */
	public void spawnMissile(int x, int y, int w, int h) 
	{
		Main.entity.add(new Missile(x+w, y+(h/2 - 2), 15, 4, 8, 0, EntityType.Missile));
	}

	/**
	 * Return the shot delay
	 *
	 * @return int
	 * 		The shot delay
	 */
	public int getShotDelay() 
	{
		return shotDelay;
	}

	/**
	 * Returns the string representation of the weapon
	 *
	 * @return String
	 * 		The string representation of the weapon
	 */
	public String toString()
	{
		return "normal";
	}
}
