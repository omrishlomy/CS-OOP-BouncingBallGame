package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

// package private - not part of the API
class ExtraPaddle extends Paddle {
    int numCollisionsBeforeDisapear;
    // indicatinf if the paddle is active, if it's false we need to remove it from the game objects and not
    // update it.
    boolean active = true;

    // constructor - added a number of collision before disapearing parameter
    public ExtraPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                       UserInputListener userInputListener, int numCollisionsBeforeDisapear) {
        super(topLeftCorner, dimensions, renderable, userInputListener);
        this.numCollisionsBeforeDisapear = numCollisionsBeforeDisapear;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        this.numCollisionsBeforeDisapear--;
        if (numCollisionsBeforeDisapear == 0){
            active = false;
            return;
        }
        super.onCollisionEnter(other, collision);
    }

    public boolean isActive() {
        return active;
    }
}
