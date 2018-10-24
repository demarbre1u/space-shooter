package helper;

import org.lwjgl.opengl.GL11;

public class Drawer 
{
	public static void drawRect(float posx, float posy, int width, int height)
	{
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(posx, posy);
			GL11.glVertex2f(posx + width, posy);
			GL11.glVertex2f(posx + width, posy + height);
			GL11.glVertex2f(posx, posy + height);
		}
		GL11.glEnd();
	}
	
	public static void drawLozenge(float posx, float posy, int width, int height)
	{
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(posx + width/2, posy);
			GL11.glVertex2f(posx + width, posy + height/2);
			GL11.glVertex2f(posx + width/2, posy + height);
			GL11.glVertex2f(posx, posy + height/2);
		}
		GL11.glEnd();
	}
}
