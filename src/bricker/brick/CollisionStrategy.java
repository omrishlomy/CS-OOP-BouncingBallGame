package bricker.brick;

import danogl.GameObject;
import danogl.collisions.Collision;

public interface CollisionStrategy {
    public void onCollision(GameObject thisObj, GameObject otherObj);
}
