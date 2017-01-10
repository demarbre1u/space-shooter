package main;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import data.EntityType;
import data.Text;
import entity.Enemy;
import entity.MovingEntity;
import entity.Player;
import entity.Star;

public class Main 
{
	public static int WIDTH = 600, HEIGHT = 400;
	private int enemySpawnSpeed = 200;
	private int starSpawnSpeed = 100;
	public static int score = 0;
	public static Player player;
	public static List<MovingEntity> entity;
	public static List<MovingEntity> toBeDestroyed;
		
	public void createDisplay() 
	{
		try 
		{
			//Here we set the size of the Display then create it
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
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
			
			Display.update();
		}
		Display.destroy();
	}
	
	public static void init()
	{
		player = new Player(20, HEIGHT/2, 40, 10, 3, EntityType.Player);
		entity = new ArrayList<MovingEntity>();
		toBeDestroyed = new ArrayList<MovingEntity>();
	}
	
	private void render() 
	{
		drawBackground();
		for(MovingEntity e : entity)
		{
			e.render();
		}
		
		GL11.glColor3f(1, 1, 1);
		player.render();
		Text.drawString("Score : " + score, 10, HEIGHT-20);
	}

	private void update() 
	{
		destroy();
		
		player.update();
		for(MovingEntity e : entity)
		{
			e.update();
			
			if(e.getType() == EntityType.Enemy)
				e.checkCollision(player);
			
			if(e.getType() == EntityType.Missile)
			{
				for(MovingEntity ent : entity)
				{
					if(ent.getType() == EntityType.Enemy)
						e.checkCollision(ent);					
				}
			}
		}
		
		if(enemySpawnSpeed >= 100 - score/30)
		{
			spawnEnemies();
			enemySpawnSpeed = 0;
		}
		else
			enemySpawnSpeed++;
		
		if(starSpawnSpeed >= 100)
		{
			spawnStar();
			starSpawnSpeed  = 0;
		}
		else
			starSpawnSpeed++;
	}
	
	private void spawnStar() 
	{
		int yAxis = (int) (50 + Math.random() * HEIGHT-100);
		int size = (int) (1 + Math.random() * 19);
		int speed = (int) (1 + Math.random());
		entity.add(0, new Star(WIDTH+100, yAxis, size, size, speed, EntityType.Star));
	}

	private void spawnEnemies() 
	{
		int rand = (int) (50 + Math.random() * HEIGHT-100);
		entity.add(new Enemy(WIDTH+100, rand, 40, 30, 2, EntityType.Enemy));
	}

	private void destroy() 
	{
		for(MovingEntity e : toBeDestroyed)
		{
			entity.remove(e);
		}
		
		toBeDestroyed.clear();
	}

	public static void main(String args[])
	{
		Main display = new Main();
		init();
		display.createDisplay();
	}

	public static void gameOver() 
	{
		System.out.print("You lost.\nGame is over!\nScore : " + score);
		System.exit(0);
	}
	
	public void drawBackground()
	{
		GL11.glColor3f(0.2f, 0.2f, 0.2f);
		
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(0, 0);
			GL11.glVertex2f(0 + WIDTH, 0);
			GL11.glVertex2f(0 + WIDTH, 0 + HEIGHT);
			GL11.glVertex2f(0, 0 + HEIGHT);
		}
		GL11.glEnd();
	
	}
}
