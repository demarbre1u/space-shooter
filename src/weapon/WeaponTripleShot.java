package weapon;

import data.EntityType;
import entity.Missile;
import main.Main;

public class WeaponTripleShot extends Weapon
{
	public WeaponTripleShot(int delay) 
	{
		super(delay);
	}

	public void spawnMissile(int x, int y, int w, int h) 
	{
		int scatterY = 1;
		
		Main.entity.add(new Missile(x+w, y+(h/2 - 2), 15, 4, 8, scatterY, EntityType.Missile));
		Main.entity.add(new Missile(x+w, y+(h/2 - 2), 15, 4, 8, 0, EntityType.Missile));
		Main.entity.add(new Missile(x+w, y+(h/2 - 2), 15, 4, 8, -scatterY, EntityType.Missile));
	} 
	
	public String toString()
	{
		return "triple shot";
	}
}
