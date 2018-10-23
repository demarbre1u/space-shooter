package entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import data.EntityType;
import main.Main;

public class Player extends MovingEntity 
{
	private Weapon weapon;
	private int lifeStock;
	private int delayBeforeNextShot;
	
	public Player(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
		weapon = new Weapon(15);
		lifeStock = 3;
		delayBeforeNextShot = 0;
	}

	@Override
	public void render() 
	{
		drawPlayer();
		drawLife();
	}

	private void drawLife() 
	{
		for(int i = 0 ; i< lifeStock ; i++)
		{
			GL11.glColor3f(.5f,1,.5f);
			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glVertex2f(10 + (20*i), Main.HEIGHT - 40);
				GL11.glVertex2f(10 + 10 + (20*i), Main.HEIGHT - 40);
				GL11.glVertex2f(10 + 10 + (20*i), Main.HEIGHT - 40 + 10);
				GL11.glVertex2f(10 + (20*i), Main.HEIGHT - 40 + 10);
			}
			GL11.glEnd();
		}
	}

	@Override
	public void update() 
	{
		checkForInputs();
		if(weapon.getShotDelay() > delayBeforeNextShot)
			delayBeforeNextShot++;
		
		updateBoundBox();
		
		if(lifeStock <= 0)
		{
			Main.gameOver();
			Main.toBeAdded.add(new Explosion(x, y, 8, 8, 2, 0, EntityType.Explosion, 1, 1, 1));
		}
	}
	
	public void gotHit()
	{
		lifeStock--;
	}

	private void checkForInputs() 
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_Z))
		{
			if(y+h + speedY <= Main.HEIGHT)
			{
				y += speedY;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			if(x-speedX >= 0)
			{
				x -= speedX;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			if(y-speedY >= 0)
			{
				y -= speedY;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			if(x+w + speedX <= Main.WIDTH)
			{
				x += speedX;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			if(weapon.getShotDelay() <= delayBeforeNextShot)
			{
				delayBeforeNextShot = 0;
				weapon.spawnMissile(x, y, w, h);
			}
		}
	}

	public void drawPlayer()
	{
		GL11.glColor3f(1,1,1);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + w, y+10);
			GL11.glVertex2f(x + w, y + h-10);
			GL11.glVertex2f(x, y + h );
		}
		GL11.glEnd();
	}
	
	public Weapon getWeapon() 
	{
		return weapon;
	}

	public void setWeapon(Weapon weapon) 
	{
		this.weapon = weapon;
	}

	public int getLifeStock() 
	{
		return lifeStock;
	}

	public void setLifeStock(int lifeStock) 
	{
		this.lifeStock = lifeStock;
	}
	
	@Override
	public void checkCollision(MovingEntity e) {}
}
