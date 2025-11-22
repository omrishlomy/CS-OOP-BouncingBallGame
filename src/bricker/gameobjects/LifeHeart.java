package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

// package private not a part of the API
class LifeHeart extends GameObject {
    private boolean active = true;
    private boolean addLife = false;
    public LifeHeart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        active = false;
        // add a life if collides with paddle
        if (other instanceof Paddle) {
            addLife = true;
        }
    }

    public boolean isAddLife() {
        return addLife;
    }

    public boolean isActive() {
        return active;
    }
}
