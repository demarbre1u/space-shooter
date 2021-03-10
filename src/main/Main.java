package main;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import entity.Enemy;
import entity.Hazard;
import entity.MovingEntity;
import entity.Player;
import entity.PowerUpTripleShot;
import entity.Star;
import entity.WavingEnemy;
import helper.Drawer;
import helper.Text;
import data.EntityType;

/**
 * Main class handling the game logic
 *
 * @author Allan DEMARBRE <demarbreallan.dev@gmail.com>
 */
public class Main 
{
	/**
	 * Width and height of the game window
	 */
	public static int WIDTH = 600, HEIGHT = 400;

	/**
	 * Speed at which enemies are moving
	 */
	private int enemySpawnSpeed = 200;

	/**
	 * Speed at which stars are spawned in the background
	 */
	private int starSpawnSpeed = 100;

	/**
	 * The score of the player
	 */
	public static int score;

	/**
	 * The player data
	 */
	public static Player player;

	/**
	 * List of current entities
	 */
	public static List<MovingEntity> entity;

	/**
	 * List of entities to be destroyed at next update
	 */
	public static List<MovingEntity> toBeDestroyed;

	/**
	 * List of entities to be added to the current entities at next update
	 */
	public static List<MovingEntity> toBeAdded;

	/**
	 * Whether the game is over or not
	 */
	private static boolean gameOver;

	/**
	 * Creates the game display and the basic game loop
	 */
	public void createDisplay() 
	{
		try 
		{
			// Here we set the size of the Display then create it
			Display.setTitle("Java Space Shooter");
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.setSwapInterval(1);
			Display.create();
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
		}

		// init OpenGL
		GL11.glColor3f(.1f, .1f, .1f);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		while (!Display.isCloseRequested()) 
		{
			// render OpenGL here

			// Clear the screen and depth buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
		
			//////////////////////////////////////////////////////////////////
			update();
			render();
			//////////////////////////////////////////////////////////////////

			Display.sync(60);
			Display.update();
		}
		Display.destroy();
	}

	/**
	 * Inits the game data
	 */
	public static void init()
	{
		player = new Player(20, HEIGHT/2, 40, 30, 4, 3, EntityType.Player);
		entity = new ArrayList<>();
		toBeDestroyed = new ArrayList<>();
		toBeAdded = new ArrayList<>();
		gameOver = false;
		score = 0;
	}

	/**
	 * Renders graphics on the screen
	 */
	private void render() 
	{
		drawBackground();
		for(MovingEntity e : entity)
		{
			e.render();
		}
		
		GL11.glColor3f(1, 1, 1);
		Text.drawString("Score : " + score, 10, HEIGHT-20);
		
		if(! gameOver)
		{
			Text.drawString(player.getWeapon().toString(), 10, HEIGHT-60);
			player.render();
		}
		else
		{
			GL11.glColor3f(1, 1, 1);
			Text.drawString("GAME OVER", WIDTH/2 - 30, HEIGHT/2 + 10);
			Text.drawString("Press R to restart", WIDTH/2 - 60, HEIGHT/2 - 40);
		}
	}

	/**
	 * Updates the game data
	 */
	private void update() 
	{
		destroy();
		add();
		
		checkCollisions();
		spawningEntities();
		
		if(! gameOver)
		{
			player.update();
		}
		else
			checkForRestart();
	}

	/**
	 * Checks if the player wants to restart the game
	 */
	private void checkForRestart()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_R))
		{
			init();
		}
	}

	/**
	 * Checks if entities are colliding
	 */
	private void checkCollisions()
	{
		for(MovingEntity e : entity)
		{
			e.update();
			if(player == null)
				continue;
			
			if(e.getType() == EntityType.Enemy || e.getType() == EntityType.Hazard || e.getType() == EntityType.PowerUp)
				e.checkCollision(player);
			
			if(e.getType() == EntityType.Missile)
			{
				for(MovingEntity ent : entity)
				{
					if(ent.getType() == EntityType.Enemy || ent.getType() == EntityType.Hazard)
						e.checkCollision(ent);
				}
			}
		}
	}

	/**
	 * Spawns the various entities of the game
	 */
	private void spawningEntities()
	{
		// Spawns enemy
		if(enemySpawnSpeed >= 100 - Math.log( (score + 1) ) * 5)
		{
			int rand = (int) Math.round(Math.random() * 2);
			if(rand != 2)
				spawnEnemies();
			else
				spawnHazards();
			
			enemySpawnSpeed = 0;
		}
		else
			enemySpawnSpeed++;
		
		// Spawn power-up
		int randPowerUp = (int) Math.round(Math.random() * 1000);
		// System.out.println("Rand nb : " + randPowerUp);
		if(randPowerUp == 0)
			spawnPowerUp();
		
		// Spawn background star
		if(starSpawnSpeed >= 100)
		{
			spawnStar();
			starSpawnSpeed = 0;
		}
		else
			starSpawnSpeed++;
	}

	/**
	 * Spawns a power-up
	 */
	private void spawnPowerUp() 
	{
		int yAxis = (int) (50 + Math.random() * HEIGHT - 50);
		int size = 20;
		int speed = 4;
		entity.add(new PowerUpTripleShot(WIDTH+100, yAxis, size, size, speed, 0, EntityType.PowerUp));
	}

	/**
	 * Spawns a star
	 */
	private void spawnStar() 
	{
		int yAxis = (int) (50 + Math.random() * HEIGHT - 50);
		int size = (int) (1 + Math.random() * 19);
		int speed = (int) (1 + Math.random());
		entity.add(0, new Star(WIDTH+100, yAxis, size, size, speed, 0, EntityType.Star));
	}

	/**
	 * Spawns enemies
	 */
	private void spawnEnemies() 
	{
		int rand = (int) (50 + Math.random() * HEIGHT  - 50);
		int enemyType = (int) Math.round(Math.random());
		
		if(enemyType == 0)
			entity.add(new Enemy(WIDTH+100, rand, 40, 30, 4, 0, EntityType.Enemy));
		else
			entity.add(new WavingEnemy(WIDTH+100, rand, 40, 30, 4, 0, EntityType.Enemy));
	}

	/**
	 * Spawns hazards
	 */
	private void spawnHazards()
	{
		int yAxis = (int) (50 + Math.random() * HEIGHT - 50);
		int size = (int) (50 + Math.random() * 50);
		entity.add(new Hazard(WIDTH+100, yAxis, size, size, 4, 0, EntityType.Hazard));
	}

	/**
	 * Destroys the entities that need to be destroyed
	 */
	private void destroy() 
	{
		entity.removeAll(toBeDestroyed);
		toBeDestroyed.clear();
	}

	/**
	 * Adds the entities that need to be added to the current entities
	 */
	private void add()
	{
		entity.addAll(toBeAdded);
		toBeAdded.clear();
	}

	/**
	 * Changes game data when the game is over
	 */
	public static void gameOver() 
	{
		gameOver = true;
		player = null;
	}

	/**
	 * Draws the game background
	 */
	public void drawBackground()
	{
		GL11.glColor3f(0.2f, 0.2f, 0.2f);
		Drawer.drawRect(0, 0, WIDTH, HEIGHT);
	}

	/**
	 * Entry point
	 *
	 * @param args
	 * 		Arguments passed to the application
	 */
	public static void main(String[] args)
	{
		Main display = new Main();
		init();
		display.createDisplay();
	}
}
