package bricker.gameobjects;

import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

// package private not a part of the API
public class AddLifeHeart extends GameObject {
    private boolean addLife = false;
    private final CollisionStrategy  collisionStrategy;
    public AddLifeHeart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                        CollisionStrategy collisionStrategy) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionStrategy = collisionStrategy;
    }



    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        // add a life if collides with paddle
        if (other.getTag().equals("Paddle")) {
            addLife = true;  // we need to add a life
            collisionStrategy.onCollision(this, other); // remove the heart
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

    }

    public boolean isAddLife() {
        return addLife;
    }

}
