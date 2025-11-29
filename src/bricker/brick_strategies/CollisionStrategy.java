package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;

/**
 * interface that represents a strategy for collision.
 * @author Lihi
 */
public interface CollisionStrategy {
    public void onCollision(GameObject thisObj, GameObject otherObj);
	default int countDecorators(){
	 return 0;
	}
}
