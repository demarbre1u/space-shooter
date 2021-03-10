package entity;

import helper.PlayerController;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import data.EntityType;
import interfaces.EffectTargetable;
import main.Main;
import weapon.Weapon;
import java.awt.im.InputContext;

/**
 * Player class
 */
public class Player extends MovingEntity implements EffectTargetable
{
	/**
	 * The current weapon of the player
	 */
	private Weapon weapon;

	/**
	 * The current number of life stock the player has
	 */
	private int lifeStock;

	/**
	 * The delay at which the player can fire projectiles
	 */
	private int delayBeforeNextShot;

	/**
	 * The class that handles the player controls
	 */
	final private PlayerController playerController;

	/**
	 * Player constructor
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
	public Player(int x, int y, int w, int h, int speedX, int speedY, EntityType type) 
	{
		super(x, y, w, h, speedX, speedY, type);
		weapon = new Weapon();
		lifeStock = 3;
		delayBeforeNextShot = 0;

		// Checks the keyboard layout of the player
		InputContext context = InputContext.getInstance();
		String language = context.getLocale().toLanguageTag();
		playerController = new PlayerController(language);
	}

	/**
	 * Renders the player and his life stocks on the screen
	 */
	@Override
	public void render() 
	{
		drawPlayer();
		drawLife();
	}

	/**
	 * Draws the player life stocks
	 */
	private void drawLife() 
	{
		for(int i = 0 ; i < lifeStock ; i++)
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

	/**
	 * Updates the player data
	 */
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

	/**
	 * Removes a life stock
	 */
	public void gotHit()
	{
		lifeStock--;
	}

	/**
	 * Checks for the player's input
	 */
	private void checkForInputs() 
	{
		if(Keyboard.isKeyDown(playerController.keyUP))
		{
			if(y+h + speedY <= Main.HEIGHT)
			{
				y += speedY;
			}
		}
		if(Keyboard.isKeyDown(playerController.keyLEFT))
		{
			if(x-speedX >= 0)
			{
				x -= speedX;
			}
		}
		if(Keyboard.isKeyDown(playerController.keyDOWN))
		{
			if(y-speedY >= 0)
			{
				y -= speedY;
			}
		}
		if(Keyboard.isKeyDown(playerController.keyRIGHT))
		{
			if(x+w + speedX <= Main.WIDTH)
			{
				x += speedX;
			}
		}
		if(Keyboard.isKeyDown(playerController.keyACTION))
		{
			if(weapon.getShotDelay() <= delayBeforeNextShot)
			{
				delayBeforeNextShot = 0;
				weapon.spawnMissile(x, y, w, h);
			}
		}
	}

	/**
	 * Draws the player on screen
	 */
	public void drawPlayer()
	{
		GL11.glColor3f(1,1,1);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + w, y + 13);
			GL11.glVertex2f(x + w, y + h - 13);
			GL11.glVertex2f(x, y + h );
		}
		GL11.glEnd();
	}

	/**
	 * Returns the current weapon the player has
	 *
	 * @return Weapon
	 * 		The weapon the player current has
	 */
	public Weapon getWeapon() 
	{
		return weapon;
	}

	/**
	 * Changes the weapon of the player
	 *
	 * @param weapon
	 * 		The new weapon
	 */
	public void setWeapon(Weapon weapon) 
	{
		this.weapon = weapon;
	}

	/**
	 * Checks if the player and an entity are colliding
	 *
	 * @param e
	 * 		The entity to check collision with
	 */
	@Override
	public void checkCollision(MovingEntity e) {}
}
