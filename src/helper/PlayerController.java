package helper;

import org.lwjgl.input.Keyboard;

/**
 * PlayerControl class
 */
public class PlayerController
{
    /**
     * The key code for the UP key
     */
    public int keyUP;

    /**
     * The key code for the LEFT key
     */
    public int keyLEFT;

    /**
     * The key code for the RIGHT key
     */
    public int keyRIGHT;

    /**
     * The key code for the DOWN key
     */
    public int keyDOWN;

    /**
     * The key code for the ACTION key
     */
    public int keyACTION;

    /**
     * PlayerController constructor
     *
     * @param newLayout
     *      The keyboard layout used by the player
     */
    public PlayerController(String newLayout)
    {
        switch (newLayout) {
            default -> {
                keyUP = Keyboard.KEY_W;
                keyLEFT = Keyboard.KEY_A;
                keyRIGHT = Keyboard.KEY_D;
                keyDOWN = Keyboard.KEY_S;
            }
        }

        keyACTION = Keyboard.KEY_SPACE;
    }
}
