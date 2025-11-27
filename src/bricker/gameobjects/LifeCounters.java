package bricker.gameobjects;

import bricker.BrickerGameManager;
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

// responsible for the numeric and graphic representation of the players lives
public class LifeCounters extends GameObject {
    private static final int SPACING = 30;
    private static final Vector2 HEART_DIMENSIONS = new Vector2(30, 30);
    private static final Vector2 ADD_FOR_TEXT_POSITION = new Vector2(0, 40);
    private static final Vector2 TEXT_DIMENSIONS = new Vector2(100, 20);
    private int numLives;
    private GameObject[] heartIcons;
    private TextRenderable textRenderable;
    private GameObject textObject;
    private BrickerGameManager gameManager;

    public LifeCounters(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, int numLives,
                        BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, renderable);
        this.numLives = numLives;
        this.gameManager = gameManager;
        this.heartIcons = new GameObject[numLives];

        // build hearts and add to game
        for (int i = 0; i < numLives; i++) {
            GameObject heart = new GameObject(topLeftCorner.add(Vector2.RIGHT.mult(i * SPACING)),
                    HEART_DIMENSIONS, renderable);
            heartIcons[i] = heart;
            gameManager.addGameObject(heart, Layer.UI);
        }
        // initalize text
        textRenderable = new TextRenderable("" + numLives);
        textRenderable.setColor(Color.GREEN);
        textObject = new GameObject(topLeftCorner.add(ADD_FOR_TEXT_POSITION), TEXT_DIMENSIONS,
                textRenderable);
        gameManager.addGameObject(textObject, Layer.UI);
    }

    public void loseLife() {
        if (numLives > 0) {
            // update the counter
            numLives--;
            // remove a heart
            gameManager.removeGameObject(heartIcons[numLives], Layer.UI);
            // update text
            updateText();
        }
        if  (numLives == 0) {
            boolean lose = true;
            gameManager.endGame(lose);
        }
    }

    private void updateText() {
        if (textObject != null) {
            textRenderable.setString(""+ numLives);
        }
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

    public void addLife(){
        if (numLives < 3){
            // add heart
            gameManager.addGameObject(heartIcons[numLives], Layer.UI);
            // update counter
            numLives++;
            // update text
            updateText();
        }
    }
}
