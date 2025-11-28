package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

/**
 * Represents a ball in the bricker game.
 * the ball is changing its direction upon collision.
 * The ball can initiate itself with a random direction.
 * @author lihi
 * @see danogl.GameObject
 */
public class Ball extends GameObject {
    private static final float BALL_SPEED = 250;  // constant ball speed
    private Sound collisionSound; // sound made upon collision
	private Renderable renderable; // image of the ball
    private int collisionCounter; // counts the number of times a ball collides with another object.

    /**
     * Constructor
     * @param topLeftCorner the position of the top left corner of the ball
     * @param dimensions - ball dimensions
     * @param renderable - image of the ball
     * @param collisionSound - sound made upon collision
     */
    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;
		this.renderable = renderable;
        this.initVelocity();
    }

    /**
     * Initiates the ball to start in a random direction
     */
    public void initVelocity(){
        Random rand = new Random();
        double angle = rand.nextDouble() * Math.PI;
        float velocityX = (float) Math.cos(angle) * BALL_SPEED;
        float velocityY = (float) Math.sin(angle) * BALL_SPEED;
        Vector2 velocity = new Vector2(velocityX, velocityY);
        super.setVelocity(velocity);
    }

    /**
     * Changes the ball direction upon collision.
     * @param other - Game object the ball collided with
     * @param collision - holds the data of the collision like norm
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        // update collision counter
        collisionCounter++;
        // change the ball velocity according to the collision norm.
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
        collisionSound.play();
    }

    /**
     * getter for number of collisions, as requested in the part 1 manual.
     * @return - number of collisions
     */
    public int getCollisionCounter() {
        return collisionCounter;
    }
}
