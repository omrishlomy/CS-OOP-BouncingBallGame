package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

// responsible for the numeric and graphic representation of the players lives
public class LifeCounters extends GameObject {
    int numLives;
    GameObject heart1;
    GameObject heart2;
    GameObject heart3;
    GameObject text;

    public LifeCounters(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }
}
