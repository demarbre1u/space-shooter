package entity;

import data.EntityType;
import interfaces.EffectTargetable;
import main.Main;
import weapon.WeaponTripleShot;

public class PowerUpTripleShot extends PowerUp
{

	public PowerUpTripleShot(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	@Override
	public void applyEffect(EffectTargetable target) 
	{
		((Player) target).setWeapon(new WeaponTripleShot());
		Main.score += 20;
		Main.toBeDestroyed.add(this);
	}

}
