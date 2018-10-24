package weapon;

import data.EntityType;
import entity.Missile;
import main.Main;

public class Weapon 
{
	protected int shotDelay;
	
	public Weapon()
	{
		shotDelay = 15;
	}

	public void spawnMissile(int x, int y, int w, int h) 
	{
		Main.entity.add(new Missile(x+w, y+(h/2 - 2), 15, 4, 8, 0, EntityType.Missile));
	}

	public int getShotDelay() 
	{
		return shotDelay;
	}

	public void setShotDelay(int shotDelay) 
	{
		this.shotDelay = shotDelay;
	}
	
	public String toString()
	{
		return "normal";
	}
}
