package helper;

import org.lwjgl.opengl.GL11;

/**
 * Helper class helping to draw shapes on the game screen
 */
public class Drawer 
{
	/**
	 * Draws a rectangle on screen
	 *
	 * @param posx
	 * 		The position X of the rectangle on the screen
	 * @param posy
	 * 		The position Y of the rectangle on the screen
	 * @param width
	 * 		The width of the rectangle
	 * @param height
	 * 		The height of the rectangle
	 */
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

	/**
	 * Draws a lozenge on the game screen
	 *
	 * @param posx
	 * 		The position X of the lozenge on the screen
	 * @param posy
	 * 		The position Y of the lozenge on the screen
	 * @param width
	 * 		The width of the lozenge
	 * @param height
	 * 		The height of the lozenge
	 */
	public static void drawLozenge(float posx, float posy, int width, int height)
	{
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2f(posx + (width >> 1), posy);
			GL11.glVertex2f(posx + width, posy + (height >> 1));
			GL11.glVertex2f(posx + (width >> 1), posy + height);
			GL11.glVertex2f(posx, posy + (height >> 1));
		}
		GL11.glEnd();
	}
}
