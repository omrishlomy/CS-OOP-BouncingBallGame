package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

public class Ball extends GameObject {
    private static final float BALL_SPEED = 250;
    private Sound collisionSound;
	private Renderable renderable;
    private int collisionCounter; // counts the number of times a ball collides with another object.

    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;
		this.renderable = renderable;
        this.initVelocity();
    }

    public void initVelocity(){
        Random rand = new Random();
        double angle = rand.nextDouble() * Math.PI;
        float velocityX = (float) Math.cos(angle) * BALL_SPEED;
        float velocityY = (float) Math.sin(angle) * BALL_SPEED;
        Vector2 velocity = new Vector2(velocityX, velocityY);
        super.setVelocity(velocity);
    }

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

    public int getCollisionCounter() {
        return collisionCounter;
    } //this is not part of the API, may need to remove
}
