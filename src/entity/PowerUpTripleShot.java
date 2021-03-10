package entity;

import data.EntityType;
import interfaces.EffectTargetable;
import main.Main;
import weapon.WeaponTripleShot;

/**
 * PowerUpTripleShort class
 */
public class PowerUpTripleShot extends PowerUp
{
	/**
	 * Power-up Triple Shot constructor
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
	public PowerUpTripleShot(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	/**
	 * Apply this power-up to a target
	 *
	 * @param target
	 * 		The target this power-up should be applied to
	 */
	@Override
	public void applyEffect(EffectTargetable target) 
	{
		((Player) target).setWeapon(new WeaponTripleShot());
		Main.score += 20;
		Main.toBeDestroyed.add(this);
	}
}
