package entity;

import org.lwjgl.opengl.GL11;

import data.Drawer;
import data.EntityType;
import main.Main;

public class PowerUp extends MovingEntity
{
	public static Weapon[] possibleWeapons = {new WeaponTripleShot(20)};
	protected Weapon weapon;
	
	public PowerUp(int x, int y, int w, int h, int speed, EntityType type) 
	{
		super(x, y, w, h, speed, type);
		
		int weaponType = (int) (Math.random() * possibleWeapons.length);
		weapon = possibleWeapons[weaponType];
	}

	@Override
	public void render() 
	{
		drawPowerUp();
	}

	@Override
	public void update() 
	{
		x -= speed;
		
		if(x < -100)
			Main.toBeDestroyed.add(this);
		else
			updateBoundBox();
	}
	
	public void drawPowerUp()
	{
		GL11.glColor3f(.1f, .6f, .6f);
		Drawer.drawLozenge(x, y, w, h);
	}

	@Override
	public void checkCollision(MovingEntity player) 
	{
		if(boundBox.intersects(player.boundBox))
		{
			if(player.getType() == EntityType.Player)
			{
				((Player) player).setWeapon(weapon);
				Main.toBeDestroyed.add(this);
				Main.score += 20;
			}
		}
	}

}
