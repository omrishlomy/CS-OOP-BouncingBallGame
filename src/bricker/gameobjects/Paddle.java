package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.components.GameObjectPhysics;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

public class Paddle extends GameObject {
    private static final float MOVMENT_SPEED = 300;
    private static int numPaddles = 0;
    private final UserInputListener userInputListener;
    private boolean touchingLeftWall = false;
    private boolean touchingRightWall = false;


    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                  UserInputListener userInputListener) {
        super(topLeftCorner, dimensions, renderable);
        this.userInputListener = userInputListener;
        this.setTag("paddle");
        numPaddles++;
    }

    public static int getNumPaddles(){
        return numPaddles;
    }

    @Override
    public void onCollisionStay(GameObject other, Collision collision) {
        super.onCollisionStay(other, collision);
        if (other.getTag().equals("LeftWall")) {
            touchingLeftWall = true;
        }
        else if (other.getTag().equals("RightWall")) {
            touchingRightWall = true;
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;
        if (userInputListener.isKeyPressed(KeyEvent.VK_LEFT) && !touchingLeftWall) {
            movementDir = movementDir.add(Vector2.LEFT);
            touchingRightWall = false;
        }
        if (userInputListener.isKeyPressed(KeyEvent.VK_RIGHT) &&  !touchingRightWall) {
            movementDir = movementDir.add(Vector2.RIGHT);
            touchingLeftWall = false;
        }
        setVelocity(movementDir.mult(MOVMENT_SPEED));
    }
}
