package bricker.gameobjects;

import bricker.BrickerGameManager;
import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents a heart falling from a brick.
 * Can add a life to the user.
 * @author Lihi
 * @see danogl.GameObject
 */
public class AddLifeHeart extends GameObject {
    private static final int HEART_SPEED = 150;
    private final BrickerGameManager gameManager;
    private final CollisionStrategy  collisionStrategy;

    /**
     * Constructor
     * @param topLeftCorner - position for the falling heart.
     * @param dimensions - dimensions of the heart.
     * @param renderable - heart image
     * @param collisionStrategy - collision strategy the is running when colliding
     * @param gameManager - bricker game manager.
     */
    public AddLifeHeart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                        CollisionStrategy collisionStrategy,  BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionStrategy = collisionStrategy;
        this.gameManager = gameManager;
        // make the heart fall down
        this.setVelocity(Vector2.DOWN.mult(HEART_SPEED));
    }

    /**
     * handles the collisions of the heart with other objects
     * @param other - object we collided with
     * @param collision - data about the collision
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        // add a life if collides with paddle
        if (other.getTag().equals("Paddle")) {
            // we need to add a life
            gameManager.addLife();
            collisionStrategy.onCollision(this, other); // remove the heart
        }
        // if it passes the bottom wall, remove it from the play
        if (other.getTag().equals("BottomWall")) {
            collisionStrategy.onCollision(this, other);
        }
    }
}
