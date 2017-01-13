package entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import data.EntityType;
import main.Main;

public class Player extends MovingEntity 
{
	private int shotDelay;
	private int lifeStock;
	
	public Player(int x, int y, int w, int h, int speed, EntityType type) 
	{
		super(x, y, w, h, speed, type);
		shotDelay = 30;
		lifeStock = 3;
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
		if(shotDelay < 30)
			shotDelay++;
		
		updateBoundBox();
		
		if(lifeStock <= 0)
		{
			Main.gameOver();
			Main.toBeAdded.add(new Explosion(x, y, 8, 8, 2, EntityType.Explosion, 1, 1, 1));
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
			if(y+h + speed <= Main.HEIGHT)
			{
				y += speed;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			if(x-speed >= 0)
			{
				x -= speed;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			if(y-speed >= 0)
			{
				y -= speed;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			if(x+w + speed <= Main.WIDTH)
			{
				x += speed;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			if(shotDelay >= 30)
			{
				shotDelay = 0;
				Main.entity.add(new Missile(x+w, y+(h/2 - 2), 15, 4, 5, EntityType.Missile));
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
	
	@Override
	public void checkCollision(MovingEntity e) {}
}
