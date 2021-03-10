package helper;

import org.lwjgl.input.Keyboard;

/**
 * PlayerControl class
 */
public class PlayerController
{
    /**
     * The key code for the UP control
     */
    public int keyUP;

    /**
     * The key code for the LEFT control
     */
    public int keyLEFT;

    /**
     * The key code for the RIGHT control
     */
    public int keyRIGHT;

    /**
     * The key code for the DOWN control
     */
    public int keyDOWN;

    /**
     * The key code for the ACTION control
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
