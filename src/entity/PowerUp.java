package entity;

import org.lwjgl.opengl.GL11;
import data.EntityType;
import helper.Drawer;
import interfaces.EffectTargetable;
import main.Main;

/**
 * PowerUp abstract class
 */
public abstract class PowerUp extends MovingEntity
{
	/**
	 * Power-up constructor
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
	public PowerUp(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
	}

	/**
	 * Renders the power-up on the screen
	 */
	@Override
	public void render() 
	{
		drawPowerUp();
	}

	/**
	 * Updates the power-up data
	 */
	@Override
	public void update() 
	{
		x -= speedX;
		y -= speedY;
		
		if(x < -100)
			Main.toBeDestroyed.add(this);
		else
			updateBoundBox();
	}

	/**
	 * Draws the power-up on the screen
	 */
	public void drawPowerUp()
	{
		GL11.glColor3f(.1f, .6f, .6f);
		Drawer.drawLozenge(x, y, w, h);
	}

	/**
	 * Checks if this power-up is colliding with the player
	 *
	 * @param player
	 * 		The player to check collision with
	 */
	@Override
	public void checkCollision(MovingEntity player) 
	{
		if(boundBox.intersects(player.boundBox))
		{
			if(player.getType() == EntityType.Player)
			{
				applyEffect((EffectTargetable) player);
				Main.toBeDestroyed.add(this);
				Main.score += 20;
			}
		}
	}

	/**
	 * Applys the power-up to the given target
	 *
	 * @param target
	 * 		The target to apply the power-up to
	 */
	public abstract void applyEffect(EffectTargetable target);
}
