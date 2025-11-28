package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;

/**
 * interface that represents a strategy for collision.
 */
public interface CollisionStrategy {
    public void onCollision(GameObject thisObj, GameObject otherObj);
}
