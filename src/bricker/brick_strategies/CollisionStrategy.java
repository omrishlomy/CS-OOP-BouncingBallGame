package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;

/**
 * interface that represents a strategy for collision.
 * @author Lihi
 */
public interface CollisionStrategy {
 /**
  * onCollison method to be implemet by the classes implementing the interface
   * @param thisObj
  * @param otherObj
  */
    public void onCollision(GameObject thisObj, GameObject otherObj);

 /**
  *
  * @return the current strategies chain length
  */
	default int countDecorators(){
	 return 0;
	}
}
