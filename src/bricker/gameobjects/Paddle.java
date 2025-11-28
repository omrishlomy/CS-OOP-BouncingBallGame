package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.components.GameObjectPhysics;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

/**
 * Represents a paddle in the bricker game.
 * moves according to user input.
 * @author Lihi
 * @see danogl.GameObject
 */
public class Paddle extends GameObject {
    private static final float MOVMENT_SPEED = 300; //constant for the paddle speed
    private final UserInputListener userInputListener;
    private boolean touchingLeftWall = false; // boolean indicating if touching the left wall
    private boolean touchingRightWall = false; // boolean indicating if touching the right wall


    /**
     * Constructor
     * @param topLeftCorner - position of tp left corner of the paddle on the screen
     * @param dimensions - dimensions of the paddle
     * @param renderable - image of the paddle
     * @param userInputListener - listener for users key press
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                  UserInputListener userInputListener) {
        super(topLeftCorner, dimensions, renderable);
        this.userInputListener = userInputListener;
        this.setTag("Paddle"); // set tag as paddle
    }

    /**
     * handles paddle collision with other objects
     * @param other - object the paddle collided with
     * @param collision - data on the collision
     */
    @Override
    public void onCollisionStay(GameObject other, Collision collision) {
        super.onCollisionStay(other, collision);
        // if touching one of the walls, update booleans accordingly
        if (other.getTag().equals("LeftWall")) {
            touchingLeftWall = true;
        }
        else if (other.getTag().equals("RightWall")) {
            touchingRightWall = true;
        }
    }

    /**
     * updates the internal state of the paddle in every frame
     * @param deltaTime - time for updating the frame
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;
        // add a movement to the left if the user pressed left key and not touching the left wall
        if (userInputListener.isKeyPressed(KeyEvent.VK_LEFT) && !touchingLeftWall) {
            movementDir = movementDir.add(Vector2.LEFT);
            // we moved left, so certainly not touching the right wall. update boolean
            touchingRightWall = false;
        }
        // add a movement to the right if the user pressed right key and not touching the right wall
        if (userInputListener.isKeyPressed(KeyEvent.VK_RIGHT) &&  !touchingRightWall) {
            movementDir = movementDir.add(Vector2.RIGHT);
            // we moved right, so certainly not touching the left wall. update boolean
            touchingLeftWall = false;
        }
        // update the new velocity, take into account the speed.
        setVelocity(movementDir.mult(MOVMENT_SPEED));
    }
}
