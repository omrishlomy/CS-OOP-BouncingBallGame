package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Ball extends GameObject {
    private int collisionCounter; // counts the number of times a ball collides with another object.

    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        // update collision counter
        collisionCounter++;
        // change the ball velocity according to the collision norm.
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
    }

    public int getCollisionCounter() {
        return collisionCounter;
    }
}
