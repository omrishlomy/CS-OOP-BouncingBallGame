package bricker.brick;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;

public interface CollisionStrategy {
    public void onCollision(GameObject thisObj, GameObject otherObj);
}
