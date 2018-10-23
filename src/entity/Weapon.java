package entity;

import data.EntityType;
import main.Main;

public class Weapon 
{
	private int shotDelay;
	
	public Weapon(int delay)
	{
		shotDelay = delay;
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
