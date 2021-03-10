package weapon;

import data.EntityType;
import entity.Missile;
import main.Main;

/**
 * WeaponTripleShot class
 */
public class WeaponTripleShot extends Weapon
{
	/**
	 * WeaponTripleShot constructor
	 */
	public WeaponTripleShot() 
	{
		shotDelay = 25;
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
		int scatterY = 1;
		
		Main.entity.add(new Missile(x+w, y+(h/2 - 2), 15, 4, 8, scatterY, EntityType.Missile));
		Main.entity.add(new Missile(x+w, y+(h/2 - 2), 15, 4, 8, 0, EntityType.Missile));
		Main.entity.add(new Missile(x+w, y+(h/2 - 2), 15, 4, 8, -scatterY, EntityType.Missile));
	}

	/**
	 * Returns the string representation of the weapon
	 *
	 * @return String
	 * 		The string representation of the weapon
	 */
	public String toString()
	{
		return "triple shot";
	}
}
