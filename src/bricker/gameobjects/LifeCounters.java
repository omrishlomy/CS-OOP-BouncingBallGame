package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/** responsible for the numeric and graphic representation of the players lives
 * can add or remove life.
 * @author Lihi
 * @see danogl.GameObject
 */
public class LifeCounters extends GameObject {
    private static final int SPACING = 30;  // constant spacing between the hearts
    private static final Vector2 HEART_DIMENSIONS = new Vector2(30, 30);  //dimensions of a heart.
    private static final Vector2 ADD_FOR_TEXT_POSITION = new Vector2(0, 40);  // position for text
    private static final Vector2 TEXT_DIMENSIONS = new Vector2(100, 20); // dimension for text
    private int numLives;  // represents the user current number of lives
    private  final int maxLives; // represents the maximum lives a user can have
    private final GameObject[] heartIcons; // an array containing the heart icons.
    private final TextRenderable textRenderable;
    private final GameObject textObject;
    private final BrickerGameManager gameManager;

    /**
     * Constructor
     * @param topLeftCorner- position for the counters on the screen
     * @param dimensions - dimensions
     * @param renderable - heart image
     * @param numLives- number of maximum lives aa user can have.
     * @param gameManager - game manager
     */
    public LifeCounters(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, int numLives,int maxNumLives,
                        BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, renderable);
        this.numLives = numLives;
        this.maxLives = maxNumLives;
        this.gameManager = gameManager;
        this.heartIcons = new GameObject[maxNumLives];

        // build hearts and add to game and to array
        for (int startLives = 0; startLives < maxLives; startLives++) {
		 GameObject heart = new GameObject(topLeftCorner.add(Vector2.RIGHT.mult(startLives * SPACING)),
				 HEART_DIMENSIONS, renderable);
		 heartIcons[startLives] = heart;
		}
		for(int optionalLives=0;optionalLives<maxLives;optionalLives++) {
            gameManager.addGameObject(heartIcons[optionalLives], Layer.UI);
		}

        // initialize text
        textRenderable = new TextRenderable("" + numLives);
        textRenderable.setColor(Color.GREEN);
        textObject = new GameObject(topLeftCorner.add(ADD_FOR_TEXT_POSITION), TEXT_DIMENSIONS,
                textRenderable);
        gameManager.addGameObject(textObject, Layer.UI);
    }

    /**
     * update that the user lost a life
     */
    public void loseLife() {
        // if there is a life to lose
        if (numLives > 0) {
            // update the counter
            numLives--;
            // remove a heart
            gameManager.removeGameObject(heartIcons[numLives], Layer.UI);
            // update text
            updateText();
        }
        // if the user lost all the lives, call the end game methode with indication that the user lost.
        if  (numLives == 0) {
            boolean lose = true;
            gameManager.endGame(lose);
        }
    }

    /**
     * updates the text
     */
    private void updateText() {
        if (textObject != null) {
            textRenderable.setString(""+ numLives);
        }
        // set the text color according to the number of lives.
        switch (numLives) {
            case 1:
                textRenderable.setColor(Color.RED);
                break;
            case 2:
                textRenderable.setColor(Color.YELLOW);
                break;
            default:
                textRenderable.setColor(Color.GREEN);
        }
    }

    /**
     * updates that the user gained a life.
     */
    public void addLife(){
        if (numLives < maxLives){
            // add heart
            gameManager.addGameObject(heartIcons[numLives], Layer.UI);
            // update counter
            numLives++;
            // update text
            updateText();
        }
    }
}
